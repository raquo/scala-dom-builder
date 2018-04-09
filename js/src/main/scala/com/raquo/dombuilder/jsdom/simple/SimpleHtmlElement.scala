package com.raquo.dombuilder.jsdom.simple

import org.scalajs.dom

class SimpleHtmlElement[+Ref <: dom.html.Element](
  override val tag: SimpleHtmlTag[Ref]
) extends SimpleElement[Ref] {

  override val ref: Ref = SimpleDomApi.htmlElementApi.createHtmlElement(this)
}
