package com.raquo.dombuilder.jsdom.syntax

import com.raquo.dombuilder.generic
import com.raquo.domtypes.generic.keys.EventProp
import org.scalajs.dom

/** See also: [[com.raquo.dombuilder.generic.syntax.Implicits]] */
trait Implicits extends generic.syntax.Implicits {

  implicit def eventPropSyntax[Ev <: dom.Event](eventProp: EventProp[Ev]): EventPropSyntax[Ev] = new EventPropSyntax(eventProp)
}
