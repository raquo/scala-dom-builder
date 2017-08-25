package com.raquo.dom.builder.jsdom.simple.builders

import com.raquo.dom.types
import org.scalajs.dom

trait SimpleTagBuilder extends types.generic.builders.TagBuilder[SimpleTag, dom.Element]
{
  override def build[Ref <: dom.Element](tagName: String, void: Boolean): SimpleTag[Ref] = {
    new SimpleTag[Ref](tagName, void)
  }
}
