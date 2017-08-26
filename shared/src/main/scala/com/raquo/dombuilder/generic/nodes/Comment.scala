package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

trait Comment extends domtypes.generic.nodes.Comment {

  protected[this] var _text: String

  setText(_text)

  @inline override def text: String = _text

  def setText(newText: String): Unit
}
