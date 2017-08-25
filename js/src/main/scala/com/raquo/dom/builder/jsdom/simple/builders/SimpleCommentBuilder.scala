package com.raquo.dom.builder.jsdom.simple.builders

import com.raquo.dom.builder.jsdom.simple.nodes.SimpleComment
import com.raquo.dom.types.generic.builders.Builder

object SimpleCommentBuilder extends Builder[SimpleComment] {

  override def build(): SimpleComment = {
    new SimpleComment("")
  }
}
