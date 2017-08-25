package com.raquo.dom.builder.generic.syntax

import com.raquo.dom.builder.generic.modifiers.Modifier
import com.raquo.dom.types.generic.builders.Tag

class TagSyntax[N](val tag: Tag[N]) extends AnyVal {

  def apply(modifiers: Modifier[N]*): N = {
    val element = tag.build()
    modifiers.foreach(_.applyTo(element))
    element
  }
}
