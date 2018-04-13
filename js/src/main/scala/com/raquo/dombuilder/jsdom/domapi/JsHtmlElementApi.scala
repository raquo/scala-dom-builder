package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.HtmlElementApi
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}
import org.scalajs.dom

import scala.scalajs.js

// @TODO[API] Extract Dom API into a separate Scala DOM API package. Eventually.

trait JsHtmlElementApi[N] extends HtmlElementApi[N, dom.html.Element, dom.Node] {

  override def createHtmlElement[Ref <: dom.html.Element](element: N with Element[N, Ref, dom.Node]): Ref = {
    dom.document.createElement(element.tag.name).asInstanceOf[Ref]
  }

  override def setAttribute[V](element: BaseHtmlElement, attr: Attr[V], value: V): Unit = {
    val domValue = attr.codec.encode(value)
    if (domValue == null) { // End users should use `removeAttribute` instead. This is to support boolean attributes.
      removeAttribute(element, attr)
    } else {
      element.ref.setAttribute(attr.name, domValue.toString)
    }
  }

  override def removeAttribute(element: BaseHtmlElement, attr: Attr[_]): Unit = {
    element.ref.removeAttribute(attr.name)
  }

  override def setProperty[V, DomV](element: BaseHtmlElement, prop: Prop[V, DomV], value: V): Unit = {
    val newValue = prop.codec.encode(value).asInstanceOf[js.Any]
    element.ref.asInstanceOf[js.Dynamic].updateDynamic(prop.name)(newValue)
  }

  override def setStyle[V](element: BaseHtmlElement, style: Style[V], value: V): Unit = {
    setRefStyle(element.ref, style.name, cssValue(value))
  }

  override def setStringStyle(element: BaseHtmlElement, style: Style[_], value: String): Unit = {
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

  @inline private def setRefStyle(ref: dom.html.Element, stylePropName: String, styleValue: js.Any): Unit = {
    ref.style.asInstanceOf[js.Dynamic].updateDynamic(stylePropName)(styleValue)
  }
}
