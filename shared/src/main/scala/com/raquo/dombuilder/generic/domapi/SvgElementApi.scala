package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.SvgAttr

trait SvgElementApi[N, BaseSvgRef <: BaseRef, BaseRef] {

  type BaseSvgElement = N with Element[N, BaseSvgRef, BaseRef]

  def createSvgElement[Ref <: BaseSvgRef](element: N with Element[N, Ref, BaseRef]): Ref

  def setSvgAttribute[V](element: BaseSvgElement, attr: SvgAttr[V], value: V): Unit

  def removeSvgAttribute(element: BaseSvgElement, attr: SvgAttr[_]): Unit
}
