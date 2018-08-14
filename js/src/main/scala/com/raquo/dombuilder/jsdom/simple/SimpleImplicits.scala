package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.KeyImplicits
import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.syntax.{EventPropSyntax, SyntaxImplicits}
import com.raquo.dombuilder.jsdom.JsCallback
import com.raquo.domtypes.generic.keys.EventProp
import org.scalajs.dom

trait SimpleImplicits
  extends KeyImplicits[SimpleN, dom.html.Element, dom.svg.Element, dom.Node]
  with SyntaxImplicits[SimpleN, dom.html.Element, dom.svg.Element, dom.Node, dom.Event, JsCallback]
  with SimpleDomApi { this: SetterBuilders[SimpleN, dom.html.Element, dom.svg.Element, dom.Node] =>

  implicit def eventPropToSyntax[Ev <: dom.Event](
    eventProp: EventProp[Ev]
  ): EventPropSyntax[SimpleN, dom.Element, dom.Node, Ev, dom.Event, JsCallback] = {
    // @TODO[IDE] IntelliJ 2017.2 needs explicit type params below to avoid erroring
    new EventPropSyntax[SimpleN, dom.Element, dom.Node, Ev, dom.Event, JsCallback](eventProp)
  }

  implicit def stringToTextNode(text: String): SimpleText = new SimpleText(text)
}
