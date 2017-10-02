package com.raquo.dombuilder.jsdom.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi
import com.raquo.dombuilder.generic.nodes.{ChildNode, Root}
import org.scalajs.dom

class JsRoot[N](
  override val container: dom.Element,
  override val child: N with ChildNode[N, dom.Node, dom.Node],
  treeApi: TreeApi[N, dom.Node]
) extends Root[N, dom.Element, dom.Node] { this: N =>

  appendChild(child)(treeApi)
}
