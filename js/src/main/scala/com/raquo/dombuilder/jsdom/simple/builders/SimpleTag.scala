package com.raquo.dombuilder.jsdom.simple.builders

import com.raquo.dombuilder.jsdom.simple.nodes.SimpleElement
import com.raquo.domtypes.generic.builders.Tag
import org.scalajs.dom

class SimpleTag[+Ref <: dom.Element] (
  override val tagName: String,
  override val void: Boolean = false
) extends Tag[SimpleElement[Ref]] {

  override def build(): SimpleElement[Ref] = {
    new SimpleElement[Ref](tagName, void)
  }
}
