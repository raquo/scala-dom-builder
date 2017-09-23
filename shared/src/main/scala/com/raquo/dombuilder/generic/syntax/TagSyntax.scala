package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.Tag

class TagSyntax[N](val tag: Tag[N]) extends AnyVal {

  def apply(modifiers: Modifier[N]*): N = {
    val element = tag.build()
    modifiers.foreach(_(element))
    element
  }
}
