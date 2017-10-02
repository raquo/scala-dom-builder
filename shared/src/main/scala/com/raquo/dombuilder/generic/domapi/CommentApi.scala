package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.Comment

trait CommentApi[N, CommentRef <: BaseRef, BaseRef] {

  type CommentNode = N with Comment[N, CommentRef, BaseRef]

  def createNode: CommentRef

  def getText(node: CommentNode): String

  def setText(node: CommentNode, text: String)
}
