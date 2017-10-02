package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.CommentApi
import com.raquo.domtypes

trait Comment[N, CommentRef <: BaseRef, BaseRef]
  extends Node[N, CommentRef, BaseRef]
  with domtypes.generic.nodes.Comment { this: N =>

  // @TODO do we even need these methods here? They don't contain any special logic. Just for convenience?

  @inline def setText(text: String)(implicit commentApi: CommentApi[N, CommentRef, BaseRef]): Unit = {
    commentApi.setText(this, text)
  }
}
