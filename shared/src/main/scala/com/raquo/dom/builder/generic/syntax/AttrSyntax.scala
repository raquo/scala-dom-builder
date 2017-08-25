package com.raquo.dom.builder.generic.syntax

import com.raquo.dom.builder.generic.modifiers.AttrSetter
import com.raquo.dom.types.generic.keys.Attr

class AttrSyntax[V](val attr: Attr[V]) extends AnyVal {

  def := (value: V): AttrSetter[V] = {
    new AttrSetter[V](attr, value)
  }
}
