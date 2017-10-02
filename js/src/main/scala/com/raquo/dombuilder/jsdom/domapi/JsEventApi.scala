package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.EventApi
import com.raquo.dombuilder.jsdom.JsCallback
import org.scalajs.dom

trait JsEventApi[N] extends EventApi[N, dom.Element, dom.Node, dom.Event, JsCallback] {

  override def callback[Ev <: dom.Event](originalCallback: (Ev) => Unit): JsCallback[Ev] = {
    originalCallback
  }

  override def addEventListener[Ev <: dom.Event](
    element: BaseElement,
    eventPropSetter: _EventPropSetter[Ev]
  ): Unit = {
    element.ref.addEventListener(
      `type` = eventPropSetter.key.domName,
      listener = eventPropSetter.domValue,
      useCapture = eventPropSetter.useCapture
    )
  }

  override def removeEventListener[Ev <: dom.Event](
    element: BaseElement,
    eventPropSetter: _EventPropSetter[Ev]
  ): Unit = {
    element.ref.removeEventListener(
      `type` = eventPropSetter.key.domName,
      listener = eventPropSetter.domValue,
      useCapture = eventPropSetter.useCapture
    )
  }
}
