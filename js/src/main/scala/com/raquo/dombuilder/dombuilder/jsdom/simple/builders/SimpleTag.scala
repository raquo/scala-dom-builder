package com.raquo.dombuilder.dombuilder.jsdom.simple.builders

import com.raquo.dombuilder.dombuilder.generic
import com.raquo.dombuilder.dombuilder.jsdom.simple.nodes.SimpleElement
import org.scalajs.dom

class SimpleTag[+Ref <: dom.Element] (
  override val tagName: String,
  override val void: Boolean = false
) extends generic.builders.Tag[SimpleElement[Ref]] {

  override def build(): SimpleElement[Ref] = {
    new SimpleElement[Ref](tagName, void)
  }
}
