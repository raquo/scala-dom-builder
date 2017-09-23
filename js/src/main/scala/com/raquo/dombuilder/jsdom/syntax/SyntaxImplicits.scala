package com.raquo.dombuilder.jsdom.syntax

import com.raquo.dombuilder.generic
import com.raquo.domtypes.generic.keys.EventProp
import org.scalajs.dom

/** See also: [[com.raquo.dombuilder.generic.syntax.SyntaxImplicits]] */
trait SyntaxImplicits extends generic.syntax.SyntaxImplicits {

  implicit def eventPropToSyntax[Ev <: dom.Event](eventProp: EventProp[Ev]): EventPropSyntax[Ev] = new EventPropSyntax(eventProp)
}
