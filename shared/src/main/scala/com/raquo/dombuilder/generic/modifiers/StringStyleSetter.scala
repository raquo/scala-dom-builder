package com.raquo.dombuilder.generic.modifiers

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.Style

class StringStyleSetter[V](
  val key: Style[V],
  val value: String
) extends Modifier[Element] {

  override def applyTo(element: Element): Unit = {
    element.setStyle(key.jsName, value)
  }
}
