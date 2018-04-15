package com.raquo.dombuilder.generic

import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.domtypes.generic.keys.{HtmlAttr, Prop, Style, SvgAttr}

/** These implicits power [[com.raquo.dombuilder.generic.syntax.KeySyntax]],
  * allowing you to use the `key := value` syntax
  */
trait KeyImplicits[N, BaseHtmlElementRef <: BaseRef, BaseSvgElementRef <: BaseRef, BaseRef] { this: SetterBuilders[N, BaseHtmlElementRef, BaseSvgElementRef, BaseRef] =>

  @inline implicit def defaultHtmlAttrSetterBuilder[V]: (HtmlAttr[V], V) => Setter[HtmlAttr[V], V, BaseHtmlElement] = buildHtmlAttrSetter

  @inline implicit def defaultPropSetterBuilder[V, DomV]: (Prop[V, DomV], V) => Setter[Prop[V, DomV], V, BaseHtmlElement] = buildPropSetter

  implicit val defaultIntStyleSetterBuilder: (Style[Int], Int) => Setter[Style[Int], Int, BaseHtmlElement] = buildIntStyleSetter

  implicit val defaultDoubleStyleSetterBuilder: (Style[Double], Double) => Setter[Style[Double], Double, BaseHtmlElement] = buildDoubleStyleSetter

  implicit val defaultStringStyleSetterBuilder: (Style[_], String) => Setter[Style[_], String, BaseHtmlElement] = buildStringStyleSetter

  @inline implicit def defaultSvgAttrSetterBuilder[V]: (SvgAttr[V], V) => Setter[SvgAttr[V], V, BaseSvgElement] = buildSvgAttrSetter
}
