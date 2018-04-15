package com.raquo.dombuilder.generic.builders

import com.raquo.dombuilder.generic.domapi.{HtmlElementApi, SvgElementApi}
import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.StyleBuilders
import com.raquo.domtypes.generic.keys.{HtmlAttr, Prop, Style, SvgAttr}

/** These [[Setter]] factories are used in
  * [[com.raquo.dombuilder.generic.KeyImplicits]] to support syntax like `attr := value`
  */
trait SetterBuilders[N, BaseHtmlElementRef <: BaseRef, BaseSvgElementRef <: BaseRef, BaseRef]
  extends StyleBuilders[Modifier[N with Element[N, BaseHtmlElementRef, BaseRef]]] {

  type BaseHtmlElement = N with Element[N, BaseHtmlElementRef, BaseRef]

  type BaseSvgElement = N with Element[N, BaseSvgElementRef, BaseRef]

  val htmlElementApi: HtmlElementApi[N, BaseHtmlElementRef, BaseRef]

  val svgElementApi: SvgElementApi[N, BaseSvgElementRef, BaseRef]

  @inline def buildHtmlAttrSetter[V](key: HtmlAttr[V], value: V): Setter[HtmlAttr[V], V, BaseHtmlElement] = {
    new Setter(key, value, htmlElementApi.setHtmlAttribute)
  }

  @inline def buildPropSetter[V, DomV](key: Prop[V, DomV], value: V): Setter[Prop[V, DomV], V, BaseHtmlElement] = {
    new Setter(key, value, htmlElementApi.setProperty)
  }

  @inline def buildIntStyleSetter(key: Style[Int], value: Int): Setter[Style[Int], Int, BaseHtmlElement] = {
    new Setter(key, value, htmlElementApi.setStyle)
  }

  @inline def buildDoubleStyleSetter(key: Style[Double], value: Double): Setter[Style[Double], Double, BaseHtmlElement] = {
    new Setter(key, value, htmlElementApi.setStyle)
  }

  @inline def buildStringStyleSetter(key: Style[_], value: String): Setter[Style[_], String, BaseHtmlElement] = {
    new Setter(key, value, htmlElementApi.setStringStyle)
  }

  @inline def buildSvgAttrSetter[V](key: SvgAttr[V], value: V): Setter[SvgAttr[V], V, BaseSvgElement] = {
    new Setter(key, value, svgElementApi.setSvgAttribute)
  }
}
