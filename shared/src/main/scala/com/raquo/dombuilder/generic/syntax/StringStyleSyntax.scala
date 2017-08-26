package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.modifiers.StyleSetter
import com.raquo.domtypes.generic.keys.Style

// @TODO[Elegance] Try to get rid of this class. It only exists because two := methods conflict in StyleSyntax[String]
class StringStyleSyntax(val style: Style[String]) extends AnyVal {

  def := (value: String): StyleSetter[String] = {
    new StyleSetter(style, value)
  }
}
