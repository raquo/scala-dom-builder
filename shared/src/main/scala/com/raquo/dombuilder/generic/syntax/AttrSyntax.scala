package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.modifiers.AttrSetter
import com.raquo.domtypes.generic.keys.Attr

class AttrSyntax[V](val attr: Attr[V]) extends AnyVal {

  def := (value: V): AttrSetter[V] = {
    new AttrSetter[V](attr, value)
  }
}
