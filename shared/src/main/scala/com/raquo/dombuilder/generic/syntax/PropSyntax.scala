package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.modifiers.PropSetter
import com.raquo.domtypes.generic.keys.Prop

class PropSyntax[V](val prop: Prop[V]) extends AnyVal {

  def := (value: V): PropSetter[V] = {
    new PropSetter[V](prop, value)
  }
}
