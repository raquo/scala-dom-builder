package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.Text

trait TextApi[N, TextRef <: BaseRef, BaseRef] {

  type TextNode = N with Text[N, TextRef, BaseRef]

  def createNode(text: String): TextRef

  def setText(node: TextNode, text: String)
}
