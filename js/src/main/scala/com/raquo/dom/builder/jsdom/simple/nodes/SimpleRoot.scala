package com.raquo.dom.builder.jsdom.simple.nodes

import com.raquo.dom.builder.generic
import com.raquo.dom.builder.jsdom.nodes.{ChildNode, ParentNode}
import com.raquo.dom.builder.jsdom.simple.SimpleRefNode
import org.scalajs.dom

class SimpleRoot (
  override val container: dom.Element,
  override val child: SimpleRefNode with ChildNode[SimpleRefNode, dom.Element]
)
  extends SimpleRefNode
  with generic.nodes.Root[SimpleRefNode, SimpleRefNode with ChildNode[SimpleRefNode, dom.Element], dom.Element, dom.Node]
  with ParentNode[SimpleRefNode, dom.Element]
