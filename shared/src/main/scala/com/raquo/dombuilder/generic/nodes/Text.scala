package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

trait Text[N, TextRef <: BaseRef, BaseRef]
  extends Node[N, TextRef, BaseRef]
  with domtypes.generic.nodes.Text { this: N => }
