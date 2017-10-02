package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

/** This trait represents a DOM Element (as opposed to a Comment node or a Text node) */
trait Element[N, +Ref <: BaseRef, BaseRef]
  extends domtypes.generic.nodes.Element
  with Node[N, Ref, BaseRef]
{ this: N =>

}
