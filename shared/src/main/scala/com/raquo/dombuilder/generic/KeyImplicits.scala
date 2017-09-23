package com.raquo.dombuilder.generic

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.Style
import com.raquo.domtypes.generic.{Modifier, SetterBuilder}

/** These implicits power [[com.raquo.dombuilder.generic.syntax.KeySyntax]],
  * allowing you to use the `key := value` syntax
  */
trait KeyImplicits {

  @inline implicit def defaultStyleSetterBuilder[V]: SetterBuilder[Style[V], V, Modifier[Element]] = modifiers.buildStyleSetter

  implicit val defaultStringStyleSetterBuilder: SetterBuilder[Style[_], String, Modifier[Element]] = modifiers.buildStringStyleSetter
}
