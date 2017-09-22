package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

trait Element extends domtypes.generic.nodes.Element {

  def setAttribute[V](attr: Attr[V], value: V): Unit

  def removeAttribute(attr: Attr[_]): Unit

  def setProperty[V](prop: Prop[V], value: V): Unit

  def setStyle[V](style: Style[V], value: V): Unit

  def setStringStyle(style: Style[_], value: String): Unit
}
