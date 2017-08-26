package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

trait Element extends domtypes.generic.nodes.Element {

  def setAttribute[V](attrName: String, value: V): Unit

  def removeAttribute(attrName: String): Unit

  def setProperty[V](propName: String, value: V): Unit

  def setStyle[V](propName: String, value: V): Unit
}
