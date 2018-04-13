package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.TextApi
import org.scalajs.dom

trait JsTextApi[N] extends TextApi[N, dom.Text, dom.Node] {

  override def createNode(text: String): dom.Text = dom.document.createTextNode(text)

  override def setText(node: TextNode, text: String): Unit = {
    node.ref.textContent = text
  }
}
