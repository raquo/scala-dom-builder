package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

trait Text extends domtypes.generic.nodes.Text {

  protected[this] var _text: String

  setText(_text)

  @inline override def text: String = _text

  def setText(newText: String): Unit
}
