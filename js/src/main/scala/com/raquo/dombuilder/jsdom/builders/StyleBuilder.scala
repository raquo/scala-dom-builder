package com.raquo.dombuilder.jsdom.builders

import com.raquo.dombuilder.generic.modifiers.{StringStyleSetter, StyleSetter}
import com.raquo.domtypes
import com.raquo.domtypes.generic.keys
import com.raquo.domtypes.generic.keys.Style

trait StyleBuilder extends domtypes.generic.builders.StyleBuilder[StyleSetter, StringStyleSetter] {

  @inline def build[V](jsKey: String, cssKey: String): Style[V] = {
    new Style[V](jsKey, cssKey)
  }

  override def buildSetter[V](style: keys.Style[V], value: V): StyleSetter[V] = {
    new StyleSetter[V](style, value)
  }

  override def buildStringSetter[V](style: keys.Style[V], value: String): StringStyleSetter[V] = {
    new StringStyleSetter[V](style, value)
  }
}
