package com.raquo.dombuilder.jsdom.simple

import com.raquo.domtypes
import org.scalajs.dom

trait SimpleSvgTagBuilder extends domtypes.generic.builders.SvgTagBuilder[SimpleSvgTag, dom.svg.Element] {

  override def svgTag[Ref <: dom.svg.Element](tagName: String, void: Boolean): SimpleSvgTag[Ref] = {
    new SimpleSvgTag(tagName, void)
  }
}
