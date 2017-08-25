package com.raquo.dom.builder.generic.syntax

import com.raquo.dom.builder.generic.modifiers.PropSetter
import com.raquo.dom.types.generic.keys.Prop

class PropSyntax[V](val prop: Prop[V]) extends AnyVal {

  def := (value: V): PropSetter[V] = {
    new PropSetter[V](prop, value)
  }
}
