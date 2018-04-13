package com.raquo.dombuilder.jsdom.simple

import org.scalajs.dom

class SimpleSvgElement[+Ref <: dom.svg.Element](
  override val tag: SimpleSvgTag[Ref]
) extends SimpleElement[Ref] {

  override val ref: Ref = SimpleDomApi.svgElementApi.createSvgElement(this)
}
