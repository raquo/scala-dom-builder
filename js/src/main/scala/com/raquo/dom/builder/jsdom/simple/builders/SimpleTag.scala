package com.raquo.dom.builder.jsdom.simple.builders

import com.raquo.dom.builder.jsdom.simple.nodes.SimpleElement
import com.raquo.dom.types.generic.builders.Tag
import org.scalajs.dom

class SimpleTag[+Ref <: dom.Element] (
  override val tagName: String,
  override val void: Boolean = false
) extends Tag[SimpleElement[Ref]] {

  override def build(): SimpleElement[Ref] = {
    new SimpleElement[Ref](tagName, void)
  }
}
