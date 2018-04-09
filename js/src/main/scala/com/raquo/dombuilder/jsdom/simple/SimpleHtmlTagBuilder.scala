package com.raquo.dombuilder.jsdom.simple

import com.raquo.domtypes
import org.scalajs.dom

trait SimpleHtmlTagBuilder extends domtypes.generic.builders.TagBuilder[SimpleHtmlTag, dom.html.Element] {

  override def tag[Ref <: dom.html.Element](tagName: String, void: Boolean): SimpleHtmlTag[Ref] = {
    new SimpleHtmlTag(tagName, void)
  }
}
