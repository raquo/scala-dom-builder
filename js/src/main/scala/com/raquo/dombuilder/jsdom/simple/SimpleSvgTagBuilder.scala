package com.raquo.dombuilder.jsdom.simple

import com.raquo.domtypes
import org.scalajs.dom

trait SimpleSvgTagBuilder extends domtypes.generic.builders.TagBuilder[SimpleSvgTag, dom.svg.Element] {

  override def tag[Ref <: dom.svg.Element](tagName: String, void: Boolean): SimpleSvgTag[Ref] = {
    new SimpleSvgTag(tagName, void)
  }
}
