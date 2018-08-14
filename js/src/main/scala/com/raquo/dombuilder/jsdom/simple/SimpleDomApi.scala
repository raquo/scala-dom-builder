package com.raquo.dombuilder.jsdom.simple

import com.raquo.dombuilder.jsdom.domapi.{JsCommentApi, JsEventApi, JsHtmlElementApi, JsSvgElementApi, JsTextApi, JsTreeApi}

trait SimpleDomApi {
  implicit val htmlElementApi: JsHtmlElementApi[SimpleN] = SimpleDomApi.htmlElementApi
  implicit val svgElementApi: JsSvgElementApi[SimpleN] = SimpleDomApi.svgElementApi
  implicit val eventApi: JsEventApi[SimpleN] = SimpleDomApi.eventApi
  implicit val commentApi: JsCommentApi[SimpleN] = SimpleDomApi.commentApi
  implicit val textApi: JsTextApi[SimpleN] = SimpleDomApi.textApi
  implicit val treeApi: JsTreeApi[SimpleN] = SimpleDomApi.treeApi
}

object SimpleDomApi {
  val htmlElementApi: JsHtmlElementApi[SimpleN] = new JsHtmlElementApi[SimpleN] {}
  val svgElementApi: JsSvgElementApi[SimpleN] = new JsSvgElementApi[SimpleN] {}
  val eventApi: JsEventApi[SimpleN] = new JsEventApi[SimpleN] {}
  val commentApi: JsCommentApi[SimpleN] = new JsCommentApi[SimpleN] {}
  val textApi: JsTextApi[SimpleN] = new JsTextApi[SimpleN] {}
  val treeApi: JsTreeApi[SimpleN] = new JsTreeApi[SimpleN] {}
}
