package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

trait Comment[N, CommentRef <: BaseRef, BaseRef]
  extends Node[N, CommentRef, BaseRef]
  with domtypes.generic.nodes.Comment { this: N => }
