package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.syntax.TagSyntax
import com.raquo.domtypes.generic.builders.Tag
import org.scalajs.dom

class SimpleTag[+Ref <: dom.Element] (
  override val tagName: String,
  override val void: Boolean = false
) extends Tag[SimpleElement[Ref]](tagName, void)
  with TagSyntax[SimpleElement[Ref]]
{
  override def build(): SimpleElement[Ref] = new SimpleElement(this)
}
