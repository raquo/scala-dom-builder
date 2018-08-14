package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.nodes.{ChildNode, Text}
import com.raquo.dombuilder.jsdom.domapi.JsTreeApi
import org.scalajs.dom

class SimpleText(initialText: String)
  extends Text[SimpleN, dom.Text, dom.Node]
  with ChildNode[SimpleN, dom.Text, dom.Node]
  with SimpleN {

  override val treeApi: JsTreeApi[SimpleN] = SimpleDomApi.treeApi

  override val ref: dom.Text = SimpleDomApi.textApi.createNode(initialText)

  @inline override def text: String = ref.data
}
