package com.raquo.dombuilder.jsdom.nodes

import com.raquo.dombuilder.generic.domapi.{ElementApi, EventApi, TreeApi}
import com.raquo.dombuilder.generic.nodes.{ChildNode, Element, EventfulNode, ParentNode}
import com.raquo.dombuilder.jsdom.JsCallback
import org.scalajs.dom

// @TODO[API] I don't like that JsElement does more than just bring generic Element into JS world. It also adds some new traits.
// @TODO[API] Maybe this should be a trait instead, or maybe Element should extend those other traits as well. Think about it...

class JsElement[N, +Ref <: dom.Element](
  override val tagName: String,
  override val void: Boolean,
  val elementApi: ElementApi[N, dom.Element, dom.Node],
  val eventApi: EventApi[N, dom.Element, dom.Node, dom.Event, JsCallback], // @TODO[API] Is this needed?
  override val treeApi: TreeApi[N, dom.Node]
)
  extends Element[N, Ref, dom.Node]
  with EventfulNode[N, Ref, dom.Element, dom.Node, dom.Event, JsCallback]
  with ParentNode[N, Ref, dom.Node]
  with ChildNode[N, Ref, dom.Node] { this: N =>

  override val ref: Ref = elementApi.createNode(this)
}
