package com.raquo.dom.builder.generic.modifiers

import com.raquo.dom.types.generic.keys.Prop
import com.raquo.dom.builder.generic.nodes.Element

class PropSetter[V](
  val key: Prop[V],
  val value: V
) extends Modifier[Element] {

  override def applyTo(element: Element): Unit = {
    element.setProperty(key.name, value)
  }
}
