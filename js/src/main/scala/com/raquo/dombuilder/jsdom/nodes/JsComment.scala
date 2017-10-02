package com.raquo.dombuilder.jsdom.nodes

import com.raquo.dombuilder.generic.domapi.{CommentApi, TreeApi}
import com.raquo.dombuilder.generic.nodes.{ChildNode, Comment}
import org.scalajs.dom

// @TODO[IDE] IntelliJ 2017.2 highlights the next line as an error, but Scala compiles just fine.
class JsComment[N](
  val text: String,
  val commentApi: CommentApi[N, dom.Comment, dom.Node],
  override val treeApi: TreeApi[N, dom.Node]
) extends Comment[N, dom.Comment, dom.Node]
  with ChildNode[N, dom.Comment, dom.Node] { this: N =>

  override val ref: dom.Comment = commentApi.createNode

  ref.textContent = text
}
