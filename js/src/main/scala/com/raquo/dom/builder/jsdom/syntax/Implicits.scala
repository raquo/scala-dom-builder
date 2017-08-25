package com.raquo.dom.builder.jsdom.syntax

import com.raquo.dom.builder.generic
import com.raquo.dom.types.generic.keys.EventProp
import org.scalajs.dom

/** See also: [[com.raquo.dom.builder.generic.syntax.Implicits]] */
trait Implicits extends generic.syntax.Implicits {

  implicit def eventPropSyntax[Ev <: dom.Event](eventProp: EventProp[Ev]): EventPropSyntax[Ev] = new EventPropSyntax(eventProp)
}
