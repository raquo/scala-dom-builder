package com.raquo.dombuilder.jsdom.nodes

import org.scalajs.dom
import com.raquo.dombuilder.generic

import scala.scalajs.js
import scala.scalajs.js.|

trait Element[+Ref <: dom.Element]
  extends generic.nodes.Element
  with generic.nodes.RefNode[Ref]
{

  override protected[this] def createRef(): Ref = {
    // @TODO[Integrity] Seems ok to me, as long as tagname matches R
    dom.document.createElement(tagName).asInstanceOf[Ref]
  }

  override def setAttribute[V](attrName: String, value: V): Unit = {
    value match {
      case true => ref.setAttribute(attrName, "")
      case false => removeAttribute(attrName)
      case _ => ref.setAttribute(attrName, value.toString)
    }
  }

  override def removeAttribute(attrName: String): Unit = {
    ref.removeAttribute(attrName)
  }

  override def setProperty[V](propName: String, value: V): Unit = {
    ref.asInstanceOf[js.Dynamic].updateDynamic(propName)(value.asInstanceOf[js.Any])
  }

  override def setStyle[V](propName: String, value: V): Unit = {
    // @TODO[Integrity] Sort out the difference between Element and HTMLElement once and for all.
    val cssValue: js.Any = value match {
      case str: String => str
      case int: Int => int
      case double: Double => double
      case null => null // @TODO[API] Setting a style to null unsets it. Maybe have a better API for this?
      case _ => value.toString
    }
    ref.asInstanceOf[dom.html.Element].style.asInstanceOf[js.Dynamic].updateDynamic(propName)(cssValue)
  }
}
