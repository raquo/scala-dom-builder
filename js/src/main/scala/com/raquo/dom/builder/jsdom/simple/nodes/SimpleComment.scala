package com.raquo.dom.builder.jsdom.simple.nodes

import com.raquo.dom.builder.jsdom.nodes.{ChildNode, Comment}
import com.raquo.dom.builder.jsdom.simple.SimpleRefNode
import org.scalajs.dom

class SimpleComment(override protected[this] var _text: String)
  extends SimpleRefNode
  with Comment
  with ChildNode[SimpleRefNode, dom.Comment]
