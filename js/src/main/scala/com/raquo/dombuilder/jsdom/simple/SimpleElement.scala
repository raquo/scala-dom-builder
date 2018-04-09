package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.nodes.{ChildNode, Element, EventfulNode, ParentNode}
import com.raquo.dombuilder.jsdom.JsCallback
import com.raquo.dombuilder.jsdom.domapi.JsTreeApi
import org.scalajs.dom

trait SimpleElement[+Ref <: dom.Element]
  extends Element[SimpleN, Ref, dom.Node]
  with EventfulNode[SimpleN, Ref, dom.Element, dom.Node, dom.Event, JsCallback]
  with ParentNode[SimpleN, Ref, dom.Node]
  with ChildNode[SimpleN, Ref, dom.Node]
  with SimpleN {

  override val treeApi: JsTreeApi[SimpleN] = SimpleDomApi.treeApi
}
