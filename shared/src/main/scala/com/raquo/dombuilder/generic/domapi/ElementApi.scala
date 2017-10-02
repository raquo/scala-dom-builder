package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

trait ElementApi[N, BaseElementRef <: BaseRef, BaseRef] {

  type BaseElement = N with Element[N, BaseElementRef, BaseRef]

  def createNode[Ref <: BaseElementRef](element: N with Element[N, Ref, BaseRef]): Ref

  def setAttribute[V](element: BaseElement, attr: Attr[V], value: V): Unit

  def removeAttribute(element: BaseElement, attr: Attr[_]): Unit

  def setProperty[V](element: BaseElement, prop: Prop[V], value: V): Unit

  def setStyle[V](element: BaseElement, style: Style[V], value: V): Unit

  def setStringStyle(element: BaseElement, style: Style[_], value: String): Unit
}
