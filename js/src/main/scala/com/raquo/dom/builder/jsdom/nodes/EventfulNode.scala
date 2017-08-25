package com.raquo.dom.builder.jsdom.nodes

import com.raquo.dom.builder.generic
import org.scalajs.dom

trait EventfulNode[N, +Ref <: dom.Node]
  extends generic.nodes.EventfulNode[N, Ref, dom.Node]
{
  this: N =>
}
