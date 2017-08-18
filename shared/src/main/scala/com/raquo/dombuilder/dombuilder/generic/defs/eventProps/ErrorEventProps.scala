package com.raquo.dombuilder.dombuilder.generic.defs.eventProps

import com.raquo.dombuilder.dombuilder.generic.builders.BoundedBuilder

trait ErrorEventProps[P[_ <: DomEvent], DomEvent, DomErrorEvent <: DomEvent] { this: BoundedBuilder[P, DomEvent] =>

  /**
    * Script to be run when an error occurs when the file is being loaded
    */
  lazy val onError: P[DomErrorEvent] = build("onerror")
}
