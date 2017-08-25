package com.raquo.dom.builder.generic.nodes

import com.raquo.dom.types

trait Comment extends types.generic.nodes.Comment {

  protected[this] var _text: String

  setText(_text)

  @inline override def text: String = _text

  def setText(newText: String): Unit
}
