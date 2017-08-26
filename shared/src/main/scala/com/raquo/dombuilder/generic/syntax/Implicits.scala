package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.builders.Tag
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

/** See also: [[com.raquo.dombuilder.jsdom.syntax.Implicits]] (int he JS sub-project) */
trait Implicits {

  implicit def tagSyntax[N](tag: Tag[N]): TagSyntax[N] = new TagSyntax(tag)

  implicit def attrSyntax[V](attr: Attr[V]): AttrSyntax[V] = new AttrSyntax(attr)

  implicit def propSyntax[Ev](prop: Prop[Ev]): PropSyntax[Ev] = new PropSyntax(prop)

  implicit def intStyleSyntax(style: Style[Int]): StyleSyntax[Int] = new StyleSyntax(style)

  implicit def doubleStyleSyntax(style: Style[Double]): StyleSyntax[Double] = new StyleSyntax(style)

  implicit def stringStyleSyntax(style: Style[String]): StringStyleSyntax = new StringStyleSyntax(style)
}
