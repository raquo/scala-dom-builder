package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi
import com.raquo.domtypes.generic.Modifier

import scala.annotation.tailrec

/** This trait represents a [[Node]] that can have a parent. All nodes except [[Root]] extend this trait. */
trait ChildNode[N, +Ref <: BaseRef, BaseRef]
  extends Node[N, Ref, BaseRef]
  with Modifier[N with ParentNode[N, BaseRef, BaseRef]]
{ this: N =>

  val treeApi: TreeApi[N, BaseRef]

  private var _maybeParent: Option[BaseParentNode] = None

  def maybeParent: Option[BaseParentNode] = _maybeParent

  @inline def isDescendantOf(parent: BaseParentNode): Boolean = {
    ChildNode.isDescendantOf(child = this, parent)
  }

  /** Note: Make sure to call [[willSetParent]] before calling this method manually */
  def setParent(maybeNextParent: Option[BaseParentNode]): Unit = {
    _maybeParent = maybeNextParent
  }

  /** This is called as a notification, BEFORE changes to the real DOM or to the Scala DOM tree are applied.
    * - Corollary: When this is called, this node's maybeParent reference has not been updated yet.
    *
    * Default implementation is a noop. You can override this to implement DOM lifecycle hooks similar to
    * React's `componentWillUnmount`.
    *
    * Note: This method is NOT automatically called inside [[setParent]] because [[setParent]] is called
    *       AFTER the real DOM was modified. Therefore, IF you call [[setParent]] directly, you need to
    *       also call [[willSetParent]] before that, if you plan to implement that method. However, if you
    *       only call [[setParent]] indirectly, via the methods defined in [[ParentNode]], those methods
    *       take care of calling [[willSetParent]] for you.
    *
    * @param maybeNextParent  `None` means this node is about to be detached form its parent
    */
  @inline def willSetParent(maybeNextParent: Option[BaseParentNode]): Unit = {}

  override def apply(parentNode: BaseParentNode): Unit = {
    // @TODO[Performance] This requires carrying a reference to JsTreeApi on every node instance.
    // @TODO[Performance] Consider making ChildNode -> Modifier conversion implicit instead (but watch compile times)
    // @TODO[Performance] Also, check that treeApi even exists in fullOpt. Is it possible to optimize it away?
    parentNode.appendChild(this)(treeApi)
  }
}

object ChildNode {

  @tailrec final def isDescendantOf[N, BaseRef](
    child: ChildNode[N, BaseRef, BaseRef],
    parent: ParentNode[N, BaseRef, BaseRef]
  ): Boolean = {
    child.maybeParent match {
      case Some(`parent`) => true
      case Some(intermediateParent: ChildNode[N, BaseRef, BaseRef]) => isDescendantOf(intermediateParent, parent)
      case _ => false
    }
  }
}
