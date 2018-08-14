package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.nodes.{ChildNode, Root}
import org.scalajs.dom

class SimpleRoot(
  override val container: dom.Element,
  override val child: SimpleN with ChildNode[SimpleN, dom.Node, dom.Node]
) extends Root[SimpleN, dom.Element, dom.Node]
  with SimpleN {

  appendChild(child)(SimpleDomApi.treeApi)
}
