package com.raquo.dombuilder.generic.modifiers

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.Prop

class PropSetter[V](
  val key: Prop[V],
  val value: V
) extends Modifier[Element] {

  override def apply(element: Element): Unit = {
    element.setProperty(key, value)
  }
}
