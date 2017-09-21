package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.builders.Tag
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

/** See also: [[com.raquo.dombuilder.jsdom.syntax.Implicits]] (int the JS sub-project) */
trait Implicits {

  implicit def tagToSyntax[N](tag: Tag[N]): TagSyntax[N] = new TagSyntax(tag)

  implicit def attrToSyntax[V](attr: Attr[V]): AttrSyntax[V] = new AttrSyntax(attr)

  implicit def propToSyntax[Ev](prop: Prop[Ev]): PropSyntax[Ev] = new PropSyntax(prop)

  implicit def intStyleToSyntax(style: Style[Int]): StyleSyntax[Int] = new StyleSyntax(style)

  implicit def doubleStyleToSyntax(style: Style[Double]): StyleSyntax[Double] = new StyleSyntax(style)

  implicit def stringStyleToSyntax(style: Style[String]): StringStyleSyntax = new StringStyleSyntax(style)
}
