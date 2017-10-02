package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.Tag

/** This class is created implicitly to provide `tag(...modifiers)` syntax */
class TagSyntax[Element](val tag: Tag[Element]) extends AnyVal {

  def apply(modifiers: Modifier[Element]*): Element = {
    val element = tag.build()
    modifiers.foreach(modifier => modifier(element))
    element
  }
}
