package com.raquo.dombuilder.dombuilder.jsdom.simple.nodes

import com.raquo.dombuilder.dombuilder.jsdom.nodes.{ChildNode, Text}
import com.raquo.dombuilder.dombuilder.jsdom.simple.SimpleRefNode
import org.scalajs.dom

class SimpleText(override protected[this] var _text: String)
  extends SimpleRefNode
  with Text
  with ChildNode[SimpleRefNode, dom.Text]
