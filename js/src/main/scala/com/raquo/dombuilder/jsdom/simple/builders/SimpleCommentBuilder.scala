package com.raquo.dombuilder.jsdom.simple.builders

import com.raquo.dombuilder.jsdom.simple.nodes.SimpleComment
import com.raquo.domtypes.generic.builders.Builder

object SimpleCommentBuilder extends Builder[SimpleComment] {

  override def build(): SimpleComment = {
    new SimpleComment("")
  }
}
