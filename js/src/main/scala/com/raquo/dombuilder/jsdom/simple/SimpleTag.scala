package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.domapi.{ElementApi, EventApi, TreeApi}
import com.raquo.dombuilder.jsdom.JsCallback
import com.raquo.dombuilder.jsdom.nodes.JsElement
import com.raquo.domtypes.generic.builders.Tag
import org.scalajs.dom

class SimpleTag[+Ref <: dom.Element] (
  override val tagName: String,
  override val void: Boolean = false,
  elementApi: ElementApi[SimpleN, dom.Element, dom.Node],
  eventApi: EventApi[SimpleN, dom.Element, dom.Node, dom.Event, JsCallback],
  treeApi: TreeApi[SimpleN, dom.Node]
) extends Tag[JsElement[SimpleN, Ref] with SimpleN] {

  override def build(): JsElement[SimpleN, Ref] with SimpleN = {
    // @TODO[IDE] IntelliJ 2017.2 unconvinced that this is correct
    new JsElement[SimpleN, Ref](tagName, void, elementApi, eventApi, treeApi) with SimpleN
  }
}
