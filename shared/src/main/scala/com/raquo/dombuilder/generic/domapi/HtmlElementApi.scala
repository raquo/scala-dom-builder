package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

trait HtmlElementApi[N, BaseHtmlElementRef <: BaseRef, BaseRef] {

  type BaseHtmlElement = N with Element[N, BaseHtmlElementRef, BaseRef]

  def createHtmlElement[Ref <: BaseHtmlElementRef](element: N with Element[N, Ref, BaseRef]): Ref

  def setAttribute[V](element: BaseHtmlElement, attr: Attr[V], value: V): Unit

  def removeAttribute(element: BaseHtmlElement, attr: Attr[_]): Unit

  def setProperty[V, DomV](element: BaseHtmlElement, prop: Prop[V, DomV], value: V): Unit

  def setStyle[V](element: BaseHtmlElement, style: Style[V], value: V): Unit

  def setStringStyle(element: BaseHtmlElement, style: Style[_], value: String): Unit
}
