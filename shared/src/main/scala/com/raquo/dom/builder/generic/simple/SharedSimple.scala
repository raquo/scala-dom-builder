package com.raquo.dom.builder.generic.simple

import com.raquo.dom.builder.generic.syntax
import com.raquo.dom.types.generic.builders.SpecializedBuilder
import com.raquo.dom.types.generic.defs.attrs.{Attrs, GlobalAttrs, InputAttrs}
import com.raquo.dom.types.generic.defs.props.{NodeProps, Props}
import com.raquo.dom.types.generic.keys.{Attr, Prop}

trait SharedSimple {

  object attrs
    extends Attrs[Attr]
    with InputAttrs[Attr]
    with GlobalAttrs[Attr]
    with SpecializedBuilder[Attr]
    with syntax.Implicits
  {
    override def build[V](key: String): Attr[V] = {
      new Attr[V](key)
    }
  }

  object props
    extends Props[Prop]
    with NodeProps[Prop]
    with SpecializedBuilder[Prop]
    with syntax.Implicits
  {
    override def build[V](key: String): Prop[V] = {
      new Prop(key)
    }
  }

}
