package com.raquo.dombuilder.jsdom.nodes

import com.raquo.dombuilder.generic.domapi.{TextApi, TreeApi}
import com.raquo.dombuilder.generic.nodes.{ChildNode, Text}
import org.scalajs.dom

// @TODO[IDE] IntelliJ 2017.2 highlights the next line as an error, but Scala compiles just fine.
class JsText[N](
  val text: String,
  val textApi: TextApi[N, dom.Text, dom.Node],
  override val treeApi: TreeApi[N, dom.Node]
) extends Text[N, dom.Text, dom.Node]
  with ChildNode[N, dom.Text, dom.Node]{ this: N =>

  override val ref: dom.Text = textApi.createNode

  setText(text)(textApi)
}
