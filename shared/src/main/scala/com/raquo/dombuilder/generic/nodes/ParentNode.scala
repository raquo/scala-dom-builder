package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi

import scala.collection.mutable

/** This trait represents a [[Node]] that can be a parent to other nodes.
  *
  * For example, [[Comment]] and [[Text]] are leaf nodes and can not be parent nodes.
  */
trait ParentNode[N, +Ref <: BaseRef, BaseRef] extends Node[N, Ref, BaseRef] { this: N =>

  private var _maybeChildren: Option[mutable.Buffer[BaseChildNode]] = None

  @inline def maybeChildren: Option[mutable.Buffer[BaseChildNode]] = _maybeChildren

  /** @return Whether child was successfully appended */
  def appendChild(child: BaseChildNode)(implicit treeApi: TreeApi[N, BaseRef]): Boolean = {
    val nextParent = Some(this)
    child.willSetParent(nextParent)

    // 1. Update DOM
    val appended = treeApi.appendChild(parent = this, child = child)
    if (appended) {

      // 2A. Update child's current parent node
      child.maybeParent.foreach { childParent =>
        childParent._maybeChildren.foreach(childParentChildren => childParentChildren -= child)
      }

      // 2B. Update this node
      if (_maybeChildren.isEmpty) {
        _maybeChildren = Some(mutable.Buffer(child))
      } else {
        _maybeChildren.foreach(children => children += child)
      }

      // 3. Update child
      child.setParent(nextParent)
    }
    appended
  }

  /** @return Whether child was successfully removed */
  def removeChild(child: BaseChildNode)(implicit treeApi: TreeApi[N, BaseRef]): Boolean = {
    var removed = false
    _maybeChildren.foreach { children =>

      // 0. Check precondition required for consistency of our own Tree vs real DOM
      val indexOfChild = children.indexOf(child)
      if (indexOfChild != -1) {
        child.willSetParent(None)

        // 1. Update DOM
        removed = treeApi.removeChild(parent = this, child = child)
        if (removed) {

          // 2. Update this node
          children.remove(indexOfChild, count = 1)

          // 3. Update child
          child.setParent(None)
        }
      }
    }
    removed
  }

  /** Note: can also be used to move children, even within the same parent
    *
    * @return Whether child was successfully inserted */
  def insertChild(
    child: BaseChildNode,
    atIndex: Int
  )(
    implicit treeApi: TreeApi[N, BaseRef]
  ): Boolean = {
    var inserted = false

    // 0. Prep this node
    if (_maybeChildren.isEmpty) {
      _maybeChildren = Some(mutable.Buffer())
    }

    _maybeChildren.foreach { children =>
      val nextParent = Some(this)
      child.willSetParent(nextParent)

      // 1. Update DOM
      if (atIndex < children.length) {
        val nextChild = children.apply(atIndex)
        inserted = treeApi.insertBefore(
          parent = this,
          newChild = child,
          referenceChild = nextChild
        )
      } else if (atIndex == children.length) {
        inserted = treeApi.appendChild(parent = this, child = child)
      }

      if (inserted) {
        // 2A. Update child's current parent node
        child.maybeParent.foreach { childParent =>
          childParent._maybeChildren.foreach(childParentChildren => childParentChildren -= child)
        }

        // 2B. Update this node
        children.insert(atIndex, child)

        // 3. Update child
        child.setParent(nextParent)
      }
    }
    inserted
  }

  /** Note: Does nothing if `oldChild` was not found in parent's children, or if `oldChild==newChild`
    *
    * @return Whether child was replaced
    */
  def replaceChild(
    oldChild: BaseChildNode,
    newChild: BaseChildNode
  )(
    implicit treeApi: TreeApi[N, BaseRef]
  ): Boolean = {
    var replaced = false
    _maybeChildren.foreach { children =>

      // 0. Check precondition required for consistency of our own Tree vs real DOM
      if (oldChild != newChild) {
        val indexOfChild = children.indexOf(oldChild)
        if (indexOfChild != -1) {
          val newChildNextParent = Some(this)
          oldChild.willSetParent(None)
          newChild.willSetParent(newChildNextParent)

          // 1. Update DOM
          replaced = treeApi.replaceChild(
            parent = this,
            newChild = newChild,
            oldChild = oldChild
          )

          // 2. Update this node
          children.update(indexOfChild, newChild)

          // 3. Update children
          oldChild.setParent(None)
          newChild.setParent(newChildNextParent)
        }
      }
    }
    replaced
  }

  /** Note: Does nothing if `fromIndex` or `toIndex` are out of bounds or if `fromIndex>toIndex`
    *
    * @return Whether children were replaced
    */
  def replaceChildren(
    fromIndex: Int,
    toIndex: Int,
    newChildren: Iterable[BaseChildNode]
  )(
    implicit treeApi: TreeApi[N, BaseRef]
  ): Boolean = {
    // A note on efficiency of this method:
    //
    // Scala DOM Builder is not a virtual DOM, it has no concept of "child reconciliation" because
    // there are no virtual children to compare. If you're building a virtual DOM library on top
    // of Scala DOM builder, it's up to you to design a reconciliation algorithm that would call
    // more specific methods like insertChild / replaceChild / removeChild.
    //
    // Note that this method does not create any new DOM nodes. All the nodes that should
    // exist are already provided to it. All it does it detach from the DOM the old children
    // (within the given index bounds), and attach new children where the old children used to be.
    //
    // It is likely that a more refined algorithm could reduce the amount of DOM operations needed
    // for certain popular use cases, e.g. for reordering of the same items. And we should look
    // into that eventually, but not before it is proven with benchmarks that
    //
    // That said, we can probably improve this method's performance. Even though it doesn't create
    // any HTML elements unnecessarily (or at all) because they are provided to it

    // @TODO[Performance] introduce a reorderChildren() method to support efficient sorting?
    // @TODO[Integrity] This does not properly report failures like other methods do

    // 0. Prep this node
    if (_maybeChildren.isEmpty) {
      _maybeChildren = Some(mutable.Buffer())
    }

    var replaced = false
    _maybeChildren.foreach { children =>
      if (
        newChildren != children
          && fromIndex >= 0 && fromIndex < children.length
          && toIndex >= 0 && toIndex < children.length
          && fromIndex <= toIndex
      ) {
        replaced = true

        // A. Remove existing children
        var numRemovedNodes = 0
        val numNodesToRemove = toIndex - fromIndex + 1
        while (numRemovedNodes < numNodesToRemove) {
          removeChild(children(fromIndex))
          numRemovedNodes += 1
        }

        // B. Insert new children
        var insertedCount = 0
        newChildren.foreach { newChild =>
          insertChild(
            newChild,
            atIndex = fromIndex + insertedCount
          )
          insertedCount += 1
        }
      }
    }
    replaced
  }

  def replaceAllChildren(newChildren: Iterable[BaseChildNode])(implicit treeApi: TreeApi[N, BaseRef]): Unit = {
    // @TODO[Performance] This could be optimized
    // @TODO[Integrity] This does not properly report failures like other methods do

    // A. Remove existing children
    _maybeChildren.foreach { children =>
      children.foreach(removeChild)
    }

    // B. Add new children
    newChildren.foreach(appendChild)
  }

  def indexOfChild(child: BaseChildNode): Int = {
    _maybeChildren.map(children => children.indexOf(child)).getOrElse(-1)
  }
}
