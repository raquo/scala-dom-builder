package com.raquo.dombuilder.generic

import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style, SvgAttr}

/** These implicits power [[com.raquo.dombuilder.generic.syntax.KeySyntax]],
  * allowing you to use the `key := value` syntax
  */
trait KeyImplicits[N, BaseHtmlElementRef <: BaseRef, BaseSvgElementRef <: BaseRef, BaseRef] { this: SetterBuilders[N, BaseHtmlElementRef, BaseSvgElementRef, BaseRef] =>

  @inline implicit def defaultAttrSetterBuilder[V]: (Attr[V], V) => Setter[Attr[V], V, BaseHtmlElement] = buildAttrSetter

  @inline implicit def defaultPropSetterBuilder[V, DomV]: (Prop[V, DomV], V) => Setter[Prop[V, DomV], V, BaseHtmlElement] = buildPropSetter

  implicit val defaultIntStyleSetterBuilder: (Style[Int], Int) => Setter[Style[Int], Int, BaseHtmlElement] = buildIntStyleSetter

  implicit val defaultDoubleStyleSetterBuilder: (Style[Double], Double) => Setter[Style[Double], Double, BaseHtmlElement] = buildDoubleStyleSetter

  implicit val defaultStringStyleSetterBuilder: (Style[_], String) => Setter[Style[_], String, BaseHtmlElement] = buildStringStyleSetter

  @inline implicit def defaultSvgAttrSetterBuilder[V]: (SvgAttr[V], V) => Setter[SvgAttr[V], V, BaseSvgElement] = buildSvgAttrSetter
}
