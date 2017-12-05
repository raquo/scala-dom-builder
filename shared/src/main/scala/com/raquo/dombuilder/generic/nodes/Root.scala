package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi

/** This trait represents the root of your Scala DOM Builder tree. It represents the root of the DOM
  * subtree that you manage with Scala DOM Builder, not the whole DOM Document.
  *
  * Scala DOM Builder assumes that you do not manipulate DOM nodes represented by this subtree outside
  * of Scala DOM Builder (e.g. by using other libraries or calling native JS DOM functions directly)
  * in ways that would have affected the state of your Scala DOM Builder tree if those changes were
  * made using Scala DOM Builder.
  *
  * So for example, moving a real DOM node that is represented in your Scala DOM Builder to a different
  * parent would cause the real DOM hierarchy to become inconsistent with the parent-children links that
  * are recorded in your Scala DOM Builder tree. If you then do anything that relies on those links to be
  * correct, your code would produce unexpected results.
  *
  * On the other hand, if you say update an attribute of some real DOM node without going through Scala
  * DOM Builder, that is completely fine because Scala DOM Builder by itself does not keep track of the
  * attributes that exist on DOM nodes. Your application code might, but after `attr := value` setter is
  * executed, it is normally discarded.
  *
  * Note: Scala DOM Builder doesn't actively police its DOM tree for inconsistencies with the real DOM.
  */
trait Root[N, +ParentRef <: BaseRef, BaseRef] extends ParentNode[N, ParentRef, BaseRef] { this: N =>

  val container: ParentRef

  val child: BaseChildNode

  /** When we create a Root, we don't want to create a new HTML Element, we want to
    * use a reference to an existing element, the container.
    */
  override val ref: ParentRef = container

  /** @return Whether child was successfully unmounted */
  def unmount()(implicit treeApi: TreeApi[N, BaseRef]): Boolean = {
    this.removeChild(child)
  }
}
