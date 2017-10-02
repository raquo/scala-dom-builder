package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.modifiers.EventPropSetter
import com.raquo.dombuilder.generic.nodes.Element

trait EventApi[N, BaseElementRef <: BaseRef, BaseRef, BaseEvent, Callback[-_]] {

  type BaseElement = N with Element[N, BaseElementRef, BaseRef]
  type _EventPropSetter[Ev <: BaseEvent] = EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback]

  /** To make sure that you remove the event listener successfully in JS DOM, you need to
    * provide the same Javascript callback function that was originally added as a listener.
    * However, the implicit conversion from a Scala function to a JS function creates a new
    * JS function every time, so we would never get referentially equal JS functions if we
    * used the Scala-to-JS conversion more than once. Therefore, we need to perform that
    * conversion only once and save the result. This method encapsulates such conversion.
    */
  def callback[Ev <: BaseEvent](originalCallback: Ev => Unit): Callback[Ev]

  def addEventListener[Ev <: BaseEvent](
    element: BaseElement,
    eventPropSetter: _EventPropSetter[Ev]
  ): Unit

  def removeEventListener[Ev <: BaseEvent](
    element: BaseElement,
    eventPropSetter: _EventPropSetter[Ev]
  ): Unit
}
