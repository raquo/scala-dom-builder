package com.raquo.dombuilder.generic.builders

import com.raquo.dombuilder.generic.modifiers
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.Style

trait StyleBuilder
  extends domtypes.generic.builders.StyleBuilder[Modifier[Element]]
{
  @inline override def buildSetter(style: Style[Int], value: Int): Modifier[Element] = {
    modifiers.buildStyleSetter(style, value)
  }

  @inline override def buildSetter(style: Style[Double], value: Double): Modifier[Element] = {
    modifiers.buildStyleSetter(style, value)
  }

  @inline override def buildSetter(style: Style[_], value: String): Modifier[Element] = {
    modifiers.buildStringStyleSetter(style, value)
  }
}
