package com.raquo.dombuilder.jsdom.simple.builders

import com.raquo.dombuilder.jsdom.simple.nodes.SimpleText
import com.raquo.domtypes

object SimpleTextBuilder extends domtypes.generic.builders.Builder[SimpleText] {

  override def build(): SimpleText = {
    new SimpleText("")
  }
}
