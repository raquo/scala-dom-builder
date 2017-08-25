package com.raquo.dom.builder.generic.syntax

import com.raquo.dom.builder.generic.modifiers.{StringStyleSetter, StyleSetter}
import com.raquo.dom.types.generic.keys.Style

class StyleSyntax[V](val style: Style[V]) extends AnyVal {

  def := (value: V): StyleSetter[V] = {
    new StyleSetter(style, value)
  }

  def := (value: String): StringStyleSetter[V] = {
    new StringStyleSetter(style, value)
  }
}
