package com.raquo.dom.builder.jsdom.simple.builders

import com.raquo.dom.types.generic.builders.BoundedBuilder
import com.raquo.dom.types.generic.keys.EventProp
import org.scalajs.dom

trait SimpleEventBuilder extends BoundedBuilder[EventProp, dom.Event] {

  override def build[Ev <: dom.Event](key: String): EventProp[Ev] = {
    new EventProp[Ev](name = key)
  }
}
