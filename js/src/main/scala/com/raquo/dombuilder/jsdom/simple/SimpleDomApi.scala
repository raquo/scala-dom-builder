package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.jsdom.domapi.{JsCommentApi, JsElementApi, JsEventApi, JsTextApi, JsTreeApi}

trait SimpleDomApi {
  implicit val elementApi: JsElementApi[SimpleN] = SimpleDomApi.elementApi
  implicit val eventApi: JsEventApi[SimpleN] = SimpleDomApi.eventApi
  implicit val commentApi: JsCommentApi[SimpleN] = SimpleDomApi.commentApi
  implicit val textApi: JsTextApi[SimpleN] = SimpleDomApi.textApi
  implicit val treeApi: JsTreeApi[SimpleN] = SimpleDomApi.treeApi
}

object SimpleDomApi {
  val elementApi: JsElementApi[SimpleN] = new JsElementApi[SimpleN] {}
  val eventApi: JsEventApi[SimpleN] = new JsEventApi[SimpleN] {}
  val commentApi: JsCommentApi[SimpleN] = new JsCommentApi[SimpleN] {}
  val textApi: JsTextApi[SimpleN] = new JsTextApi[SimpleN] {}
  val treeApi: JsTreeApi[SimpleN] = new JsTreeApi[SimpleN] {}
}
