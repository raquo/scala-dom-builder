package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.modifiers.{StringStyleSetter, StyleSetter}
import com.raquo.domtypes.generic.keys.Style

class StyleSyntax[V](val style: Style[V]) extends AnyVal {

  def := (value: V): StyleSetter[V] = {
    new StyleSetter(style, value)
  }

  def := (value: String): StringStyleSetter[V] = {
    new StringStyleSetter(style, value)
  }
}
