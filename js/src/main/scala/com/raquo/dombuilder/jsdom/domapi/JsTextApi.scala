package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.TextApi
import org.scalajs.dom

trait JsTextApi[N] extends TextApi[N, dom.Text, dom.Node] {

  override def createNode: dom.Text = dom.document.createTextNode("")

  override def getText(node: TextNode): String = node.ref.textContent

  override def setText(node: TextNode, text: String): Unit = {
    node.ref.textContent = text
  }
}
