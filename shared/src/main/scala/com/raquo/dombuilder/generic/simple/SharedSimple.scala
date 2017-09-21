package com.raquo.dombuilder.generic.simple

import com.raquo.domtypes.generic.builders.{AttrBuilder, PropBuilder}
import com.raquo.domtypes.generic.defs.attrs.{Attrs, GlobalAttrs, InputAttrs}
import com.raquo.domtypes.generic.defs.props.{NodeProps, Props}
import com.raquo.domtypes.generic.keys.{Attr, Prop}

trait SharedSimple {

  object attrs
    extends Attrs[Attr]
    with InputAttrs[Attr]
    with GlobalAttrs[Attr]
    with AttrBuilder

  object props
    extends Props[Prop]
    with NodeProps[Prop]
    with PropBuilder
}
