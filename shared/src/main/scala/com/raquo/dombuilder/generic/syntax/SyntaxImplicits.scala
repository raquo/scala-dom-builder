package com.raquo.dombuilder.generic.syntax

import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.Tag
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

/** This trait defines implicit conversions that power Scala DOM Builder syntax,
  * namely the `key := value` setter creation, and `tag(...modifiers)` element creation.
  *
  * These conversions must be in scope to use conventional Scala DOM Builder syntax.
  *
  * Most likely you also want [[com.raquo.dombuilder.generic.KeyImplicits]] to be in scope
  * to use default [[com.raquo.dombuilder.generic.modifiers.Setter]] builders.
  */
trait SyntaxImplicits[N, BaseElementRef <: BaseRef, BaseRef, BaseEvent, Callback[- _]] {

  private type BaseElement = N with Element[N, BaseElementRef, BaseRef]

  implicit def attrToKeySyntax[V](attr: Attr[V]): KeySyntax[Attr[V], Modifier[BaseElement]] = {
    new KeySyntax(attr)
  }

  implicit def propToKeySyntax[V, DomV](prop: Prop[V, DomV]): KeySyntax[Prop[V, DomV], Modifier[BaseElement]] = {
    new KeySyntax(prop)
  }

  implicit def intStyleToKeySyntax(style: Style[Int]): KeySyntax[Style[Int], Modifier[BaseElement]] = {
    new KeySyntax(style)
  }

  implicit def doubleStyleToKeySyntax(style: Style[Double]): KeySyntax[Style[Double], Modifier[BaseElement]] = {
    new KeySyntax(style)
  }

  implicit def stringStyleToKeySyntax(style: Style[_]): KeySyntax[Style[_], Modifier[BaseElement]] = {
    new KeySyntax(style)
  }

  // @TODO[API,IDE] IntelliJ 2017.2 does not understand such conversions. Maybe some types are not quite right?
  //  implicit def keyToSyntax[K <: Key, Element](key: K): KeySyntax[K, Modifier[Element]] = new KeySyntax(key)

  //  implicit def eventPropToSyntax[Ev <: BaseEvent](
  //    eventProp: EventProp[Ev]
  //  ): EventPropSyntax[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback] = new EventPropSyntax(eventProp)
}
