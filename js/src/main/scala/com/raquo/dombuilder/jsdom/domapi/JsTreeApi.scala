package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.TreeApi
import org.scalajs.dom
import org.scalajs.dom.DOMException

import scala.scalajs.js.JavaScriptException

trait JsTreeApi[N] extends TreeApi[N, dom.Node] {

  override def appendChild(
    parent: BaseParentNode,
    child: BaseChildNode
  ): Boolean = {
    try {
      parent.ref.appendChild(child.ref)
      true
    } catch {
      // @TODO[Integrity] Does this only catch DOM exceptions? (here and in other methods)
      case JavaScriptException(_: DOMException) => false
    }
  }

  override def removeChild(
    parent: BaseParentNode,
    child: BaseChildNode
  ): Boolean = {
    try {
      parent.ref.removeChild(child.ref)
      true
    } catch {
      case JavaScriptException(_: DOMException) => false
    }
  }

  override def insertBefore(
    parent: BaseParentNode,
    newChild: BaseChildNode,
    referenceChild: BaseChildNode
  ): Boolean = {
    try {
      parent.ref.insertBefore(newChild = newChild.ref, refChild = referenceChild.ref)
      true
    } catch {
      case JavaScriptException(_: DOMException) => false
    }
  }

  override def replaceChild(
    parent: BaseParentNode,
    newChild: BaseChildNode,
    oldChild: BaseChildNode
  ): Boolean = {
    try {
      parent.ref.replaceChild(newChild = newChild.ref, oldChild = oldChild.ref)
      true
    } catch {
      case JavaScriptException(_: DOMException) => false
    }
  }
}
