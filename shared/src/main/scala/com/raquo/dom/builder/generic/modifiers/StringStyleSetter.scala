package com.raquo.dom.builder.generic.modifiers

import com.raquo.dom.builder.generic.nodes.Element
import com.raquo.dom.types.generic.keys.Style

class StringStyleSetter[V](
  val key: Style[V],
  val value: String
) extends Modifier[Element] {

  override def applyTo(element: Element): Unit = {
    element.setStyle(key.jsName, value)
  }
}
