package com.raquo.dom.builder.generic.modifiers

import com.raquo.dom.types.generic.keys.Attr
import com.raquo.dom.builder.generic.nodes.Element

class AttrSetter[V](
  val key: Attr[V],
  val value: V
) extends Modifier[Element] {

  override def applyTo(element: Element): Unit = {
    element.setAttribute(key.name, value)
  }
}
