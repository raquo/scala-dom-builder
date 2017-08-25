package com.raquo.dom.builder.generic.modifiers

import com.raquo.dom.builder.generic.nodes.Element
import com.raquo.dom.types.generic.keys.Style

class StyleSetter[V](
  val key: Style[V],
  val value: V
) extends Modifier[Element] {

  override def applyTo(element: Element): Unit = {
    element.setStyle(key.jsName, value)
  }
}
