package com.raquo.dombuilder.jsdom.syntax

import com.raquo.dombuilder.jsdom.modifiers.EventPropSetter
import com.raquo.domtypes.generic.keys.EventProp
import org.scalajs.dom

class EventPropSyntax[Ev <: dom.Event](val eventProp: EventProp[Ev]) extends AnyVal {

  def := (value: Ev => Unit): EventPropSetter[Ev] = {
    new EventPropSetter[Ev](eventProp, value)
  }

  // @TODO[Performance] Check how much function wrapping is happening here (there's also "value _" in user code)
  def := (value: () => Unit): EventPropSetter[Ev] = {
    new EventPropSetter[Ev](eventProp, _ => value())
  }
}
