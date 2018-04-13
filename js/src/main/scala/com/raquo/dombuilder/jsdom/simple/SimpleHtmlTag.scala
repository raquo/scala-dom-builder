package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.syntax.TagSyntax
import com.raquo.domtypes.generic.builders.Tag
import org.scalajs.dom

class SimpleHtmlTag[+Ref <: dom.html.Element] (
  override val name: String,
  override val void: Boolean = false
) extends Tag[SimpleHtmlElement[Ref]](name, void)
  with TagSyntax[SimpleHtmlElement[Ref]]
{
  override def build(): SimpleHtmlElement[Ref] = new SimpleHtmlElement(this)
}
