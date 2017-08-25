package com.raquo.dom.builder.jsdom.simple.builders

import com.raquo.dom.builder.jsdom.simple.nodes.SimpleText
import com.raquo.dom.types

object SimpleTextBuilder extends types.generic.builders.Builder[SimpleText] {

  override def build(): SimpleText = {
    new SimpleText("")
  }
}
