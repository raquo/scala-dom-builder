package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.builders.Tag
import com.raquo.domtypes.generic.keys.{Attr, Key, Prop}

/** See also: [[com.raquo.dombuilder.jsdom.syntax.Implicits]] (int the JS sub-project) */
trait SyntaxImplicits {

  implicit def keyToSyntax[K <: Key](key: K): KeySyntax[K] = new KeySyntax(key)

  implicit def tagToSyntax[N](tag: Tag[N]): TagSyntax[N] = new TagSyntax(tag)

  implicit def propToSyntax[Ev](prop: Prop[Ev]): PropSyntax[Ev] = new PropSyntax(prop)
}
