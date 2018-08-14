package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.ElementApi
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}
import org.scalajs.dom

import scala.scalajs.js

// @TODO[API] Extract Dom API into a separate Scala DOM API package. Eventually.

trait JsElementApi[N] extends ElementApi[N, dom.Element, dom.Node] {

  override def createNode[Ref <: dom.Element](element: N with Element[N, Ref, dom.Node]): Ref = {
    dom.document.createElement(element.tagName).asInstanceOf[Ref]
  }

  override def setAttribute[V](element: BaseElement, attr: Attr[V], value: V): Unit = {
    val domValue = attr.codec.encode(value)
    if (domValue == null) { // End users should use `removeAttribute` instead. This is to support boolean attributes.
      removeAttribute(element, attr)
    } else {
      element.ref.setAttribute(attr.name, domValue.toString)
    }
  }

  override def removeAttribute(element: BaseElement, attr: Attr[_]): Unit = {
    element.ref.removeAttribute(attr.name)
  }

  override def setProperty[V, DomV](element: BaseElement, prop: Prop[V, DomV], value: V): Unit = {
    val newValue = prop.codec.encode(value).asInstanceOf[js.Any]
    element.ref.asInstanceOf[js.Dynamic].updateDynamic(prop.name)(newValue)
  }

  override def setStyle[V](element: BaseElement, style: Style[V], value: V): Unit = {
    setRefStyle(element.ref, style.name, cssValue(value))
  }

  override def setStringStyle(element: BaseElement, style: Style[_], value: String): Unit = {
    setRefStyle(element.ref, style.name, value)
  }

  @inline private def cssValue(value: Any): js.Any = {
    value match {
      case str: String => str
      case int: Int => int
      case double: Double => double
      case null => null // @TODO[API] Setting a style to null unsets it. Maybe have a better API for this?
      case _ => value.toString
    }
  }

  @inline private def setRefStyle(ref: dom.Element, stylePropName: String, styleValue: js.Any): Unit = {
    // @TODO[Integrity] Sort out the difference between Element and HTMLElement once and for all.
    ref.asInstanceOf[dom.html.Element].style.asInstanceOf[js.Dynamic].updateDynamic(stylePropName)(styleValue)
  }
}
