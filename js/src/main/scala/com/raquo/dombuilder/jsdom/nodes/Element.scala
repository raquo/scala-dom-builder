package com.raquo.dombuilder.jsdom.nodes

import org.scalajs.dom
import com.raquo.dombuilder.generic
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

import scala.scalajs.js

trait Element[+Ref <: dom.Element]
  extends generic.nodes.Element
  with generic.nodes.RefNode[Ref]
{

  override protected[this] def createRef(): Ref = {
    // @TODO[Integrity] Seems ok to me, as long as tagName matches Ref
    dom.document.createElement(tagName).asInstanceOf[Ref]
  }

  override def setAttribute[V](attr: Attr[V], value: V): Unit = {
    value match {
      case true => ref.setAttribute(attr.name, "")
      case false => removeAttribute(attr)
      case _ => ref.setAttribute(attr.name, value.toString)
    }
  }

  override def removeAttribute(attr: Attr[_]): Unit = {
    ref.removeAttribute(attr.name)
  }

  override def setProperty[V](prop: Prop[V], value: V): Unit = {
    ref.asInstanceOf[js.Dynamic].updateDynamic(prop.name)(value.asInstanceOf[js.Any])
  }

  override def setStyle[V](style: Style[V], value: V): Unit = {
    setRefStyle(style.name, cssValue(value))
  }

  override def setStringStyle(style: Style[_], value: String): Unit = {
    setRefStyle(style.name, value)
  }

  private def cssValue(value: Any): js.Any = {
    value match {
      case str: String => str
      case int: Int => int
      case double: Double => double
      case null => null // @TODO[API] Setting a style to null unsets it. Maybe have a better API for this?
      case _ => value.toString
    }
  }

  private def setRefStyle(stylePropName: String, styleValue: js.Any): Unit = {
    // @TODO[Integrity] Sort out the difference between Element and HTMLElement once and for all.
    ref.asInstanceOf[dom.html.Element].style.asInstanceOf[js.Dynamic].updateDynamic(stylePropName)(styleValue)
  }
}
