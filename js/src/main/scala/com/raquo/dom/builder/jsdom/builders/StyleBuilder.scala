package com.raquo.dom.builder.jsdom.builders

import com.raquo.dom.builder.generic.modifiers.{StringStyleSetter, StyleSetter}
import com.raquo.dom.types
import com.raquo.dom.types.generic.keys
import com.raquo.dom.types.generic.keys.Style

trait StyleBuilder extends types.generic.builders.StyleBuilder[StyleSetter, StringStyleSetter] {

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
