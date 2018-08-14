package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.keys.{HtmlAttr, Prop, Style, SvgAttr}

/** This trait defines implicit conversions that power Scala DOM Builder syntax,
  * namely the `key := value` setter creation, and `tag(...modifiers)` element creation.
  *
  * These conversions must be in scope to use conventional Scala DOM Builder syntax.
  *
  * Most likely you also want [[com.raquo.dombuilder.generic.KeyImplicits]] to be in scope
  * to use default [[com.raquo.dombuilder.generic.modifiers.Setter]] builders.
  */
trait SyntaxImplicits[N, BaseHtmlElementRef <: BaseRef, BaseSvgElementRef <: BaseRef, BaseRef, BaseEvent, Callback[- _]] {

  implicit def htmlAttrToKeySyntax[V](attr: HtmlAttr[V]): KeySyntax[HtmlAttr[V]] = {
    new KeySyntax(attr)
  }

  implicit def propToKeySyntax[V, DomV](prop: Prop[V, DomV]): KeySyntax[Prop[V, DomV]] = {
    new KeySyntax(prop)
  }

  implicit def intStyleToKeySyntax(style: Style[Int]): KeySyntax[Style[Int]] = {
    new KeySyntax(style)
  }

  implicit def doubleStyleToKeySyntax(style: Style[Double]): KeySyntax[Style[Double]] = {
    new KeySyntax(style)
  }

  implicit def stringStyleToKeySyntax(style: Style[_]): KeySyntax[Style[_]] = {
    new KeySyntax(style)
  }

  implicit def svgAttrToKeySyntax[V](attr: SvgAttr[V]): KeySyntax[SvgAttr[V]] = {
    new KeySyntax(attr)
  }

}
