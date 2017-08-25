package com.raquo.dom.builder.generic.nodes

import com.raquo.dom.types

trait Element extends types.generic.nodes.Element {

  def setAttribute[V](attrName: String, value: V): Unit

  def removeAttribute(attrName: String): Unit

  def setProperty[V](propName: String, value: V): Unit

  def setStyle[V](propName: String, value: V): Unit
}
