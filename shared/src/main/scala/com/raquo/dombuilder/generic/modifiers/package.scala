package com.raquo.dombuilder.generic

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.Style
import com.raquo.domtypes.generic.{Modifier, SetterBuilder}

package object modifiers {

  @inline def buildStyleSetter[V]: SetterBuilder[Style[V], V, Modifier[Element]] =
    (key: Style[V], value: V) =>
      (element: Element) => element.setStyle(key, value)

  @inline def buildStringStyleSetter: SetterBuilder[Style[_], String, Modifier[Element]] =
    (key: Style[_], value: String) =>
      (element: Element) => element.setStringStyle(key, value)
}
