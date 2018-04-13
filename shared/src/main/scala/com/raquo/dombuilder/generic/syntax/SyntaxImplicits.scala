package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style, SvgAttr}

/** This trait defines implicit conversions that power Scala DOM Builder syntax,
  * namely the `key := value` setter creation, and `tag(...modifiers)` element creation.
  *
  * These conversions must be in scope to use conventional Scala DOM Builder syntax.
  *
  * Most likely you also want [[com.raquo.dombuilder.generic.KeyImplicits]] to be in scope
  * to use default [[com.raquo.dombuilder.generic.modifiers.Setter]] builders.
  */
trait SyntaxImplicits[N, BaseHtmlElementRef <: BaseRef, BaseSvgElementRef <: BaseRef, BaseRef, BaseEvent, Callback[- _]] {

  private type BaseHtmlElement = N with Element[N, BaseHtmlElementRef, BaseRef]

  private type BaseSvgElement = N with Element[N, BaseSvgElementRef, BaseRef]

  implicit def attrToKeySyntax[V](attr: Attr[V]): KeySyntax[Attr[V]] = {
    new KeySyntax(attr)
  }

  implicit def propToKeySyntax[V, DomV](prop: Prop[V, DomV]): KeySyntax[Prop[V, DomV]] = {
    new KeySyntax(prop)
  }

  implicit def intStyleToKeySyntax(style: Style[Int]): KeySyntax[Style[Int]] = {
    new KeySyntax(style)
  }

  implicit def doubleStyleToKeySyntax(style: Style[Double]): KeySyntax[Style[Double]] = {
    new KeySyntax(style)
  }

  implicit def stringStyleToKeySyntax(style: Style[_]): KeySyntax[Style[_]] = {
    new KeySyntax(style)
  }

  implicit def svgAttrToKeySyntax[V](attr: SvgAttr[V]): KeySyntax[SvgAttr[V]] = {
    new KeySyntax(attr)
  }

  // @TODO[API,IDE] IntelliJ 2017.2 does not understand such conversions. Maybe some types are not quite right?
  //  implicit def keyToSyntax[K <: Key, Element](key: K): KeySyntax[K, Modifier[Element]] = new KeySyntax(key)

  //  implicit def eventPropToSyntax[Ev <: BaseEvent](
  //    eventProp: EventProp[Ev]
  //  ): EventPropSyntax[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = new EventPropSyntax(eventProp)
}
