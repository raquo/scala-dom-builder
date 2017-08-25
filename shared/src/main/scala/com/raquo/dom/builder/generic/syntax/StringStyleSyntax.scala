package com.raquo.dom.builder.generic.syntax

import com.raquo.dom.builder.generic.modifiers.StyleSetter
import com.raquo.dom.types.generic.keys.Style

// @TODO[Elegance] Try to get rid of this class. It only exists because two := methods conflict in StyleSyntax[String]
class StringStyleSyntax(val style: Style[String]) extends AnyVal {

  def := (value: String): StyleSetter[String] = {
    new StyleSetter(style, value)
  }
}
