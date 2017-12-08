package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.Tag

/** This trait provides `tag(...modifiers)` syntax */
trait TagSyntax[+El] { this: Tag[El] =>

  def apply(modifiers: Modifier[El]*): El = {
    val element = build()
    modifiers.foreach(modifier => modifier(element))
    element
  }

  /** Create a Scala DOM Builder element from this Tag */
  protected def build(): El
}
