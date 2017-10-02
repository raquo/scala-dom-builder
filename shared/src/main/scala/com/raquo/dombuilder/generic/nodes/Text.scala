package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TextApi
import com.raquo.domtypes

trait Text[N, TextRef <: BaseRef, BaseRef]
  extends Node[N, TextRef, BaseRef]
  with domtypes.generic.nodes.Text { this: N =>

  // @TODO do we even need these methods here? They don't contain any special logic. Just for convenience?

  @inline def setText(text: String)(implicit textApi: TextApi[N, TextRef, BaseRef]): Unit = {
    textApi.setText(this, text)
  }
}
