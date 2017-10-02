package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.domapi.EventApi
import com.raquo.dombuilder.generic.modifiers.EventPropSetter
import com.raquo.domtypes.generic.keys.EventProp

/** This class is created implicitly to provide syntax like `onClick := doSomething` */
class EventPropSyntax[N, BaseElementRef <: BaseRef, BaseRef, Ev <: BaseEvent, BaseEvent, Callback[- _]](
  val eventProp: EventProp[Ev]
) extends AnyVal {

  @inline def :=(
    eventHandler: Ev => Unit,
    useCapture: Boolean
  )(
    implicit eventApi: EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]
  ): EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = {
    // @TODO[WTF] Can't remove type params from the line below because Scala Compiler is unable to infer them. I wonder why.
    new EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback](eventProp, eventHandler, useCapture)(eventApi)
  }

  @inline def :=(eventHandler: Ev => Unit)(
    implicit eventApi: EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]
  ): EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = {
    :=(eventHandler, useCapture = false)
  }

  // @TODO[Performance] Check how much function wrapping is happening here (there could also be "value _" in callee code)
  @inline def :=(eventHandler: () => Unit)(
    implicit eventApi: EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]
  ): EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = {
    :=(eventHandler = (_: Ev) => eventHandler(), useCapture = false)
  }

  // @TODO[Performance] Check how much function wrapping is happening here (there could also be "value _" in callee code)
  @inline def :=(
    eventHandler: () => Unit,
    useCapture: Boolean
  )(
    implicit eventApi: EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]
  ): EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = {
    :=(eventHandler = (_: Ev) => eventHandler(), useCapture)
  }
}
