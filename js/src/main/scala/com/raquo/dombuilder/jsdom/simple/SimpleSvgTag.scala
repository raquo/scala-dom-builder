package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.syntax.TagSyntax
import com.raquo.domtypes.generic.builders.Tag
import org.scalajs.dom

class SimpleSvgTag[+Ref <: dom.svg.Element] (
  override val name: String,
  override val void: Boolean = false
) extends Tag[SimpleSvgElement[Ref]](name, void)
  with TagSyntax[SimpleSvgElement[Ref]]
{
  override def build(): SimpleSvgElement[Ref] = new SimpleSvgElement(this)
}
