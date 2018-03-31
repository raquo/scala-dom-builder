package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.generic.nodes.{ChildNode, Comment}
import com.raquo.dombuilder.jsdom.domapi.JsTreeApi
import org.scalajs.dom

class SimpleComment(initialText: String)
  extends Comment[SimpleN, dom.Comment, dom.Node]
  with ChildNode[SimpleN, dom.Comment, dom.Node]
  with SimpleN {

  override val treeApi: JsTreeApi[SimpleN] = SimpleDomApi.treeApi

  override val ref: dom.Comment = SimpleDomApi.commentApi.createNode(initialText)

  @inline override def text: String = ref.text // @TODO[API] Do we even need this in base trait? I guess for HTML generation?
}
