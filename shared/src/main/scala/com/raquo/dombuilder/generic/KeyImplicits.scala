package com.raquo.dombuilder.generic

import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

/** These implicits power [[com.raquo.dombuilder.generic.syntax.KeySyntax]],
  * allowing you to use the `key := value` syntax
  */
trait KeyImplicits[N, BaseElementRef <: BaseRef, BaseRef] { this: SetterBuilders[N, BaseElementRef, BaseRef] =>

  @inline implicit def defaultAttrSetterBuilder[V]: (Attr[V], V) => Setter[Attr[V], V, BaseElement] = buildAttrSetter

  @inline implicit def defaultPropSetterBuilder[V]: (Prop[V], V) => Setter[Prop[V], V, BaseElement] = buildPropSetter

  implicit val defaultIntStyleSetterBuilder: (Style[Int], Int) => Setter[Style[Int], Int, BaseElement] = buildIntStyleSetter

  implicit val defaultDoubleStyleSetterBuilder: (Style[Double], Double) => Setter[Style[Double], Double, BaseElement] = buildDoubleStyleSetter

  implicit val defaultStringStyleSetterBuilder: (Style[_], String) => Setter[Style[_], String, BaseElement] = buildStringStyleSetter
}
