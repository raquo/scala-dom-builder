package com.raquo.dombuilder.generic.modifiers

import com.raquo.domtypes.generic.keys.Attr
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier

class AttrSetter[V](
  val key: Attr[V],
  val value: V
) extends Modifier[Element] {

  override def apply(element: Element): Unit = {
    element.setAttribute(key, value)
  }
}
