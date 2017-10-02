package com.raquo.dombuilder.generic.builders

import com.raquo.dombuilder.generic.domapi.ElementApi
import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.StyleBuilders
import com.raquo.domtypes.generic.keys.{Attr, Prop, Style}

/** These [[Setter]] factories are used in
  * [[com.raquo.dombuilder.generic.KeyImplicits]] to support syntax like `attr := value`
  */
trait SetterBuilders[N, BaseElementRef <: BaseRef, BaseRef]
  extends StyleBuilders[Modifier[N with Element[N, BaseElementRef, BaseRef]]] {

  type BaseElement = N with Element[N, BaseElementRef, BaseRef]

  val elementApi: ElementApi[N, BaseElementRef, BaseRef]

  @inline def buildAttrSetter[V](key: Attr[V], value: V): Setter[Attr[V], V, BaseElement] = {
    new Setter(key, value, elementApi.setAttribute)
  }

  @inline def buildPropSetter[V](key: Prop[V], value: V): Setter[Prop[V], V, BaseElement] = {
    new Setter(key, value, elementApi.setProperty)
  }

  @inline def buildIntStyleSetter(key: Style[Int], value: Int): Setter[Style[Int], Int, BaseElement] = {
    new Setter(key, value, elementApi.setStyle)
  }

  @inline def buildDoubleStyleSetter(key: Style[Double], value: Double): Setter[Style[Double], Double, BaseElement] = {
    new Setter(key, value, elementApi.setStyle)
  }

  @inline def buildStringStyleSetter(key: Style[_], value: String): Setter[Style[_], String, BaseElement] = {
    new Setter(key, value, elementApi.setStringStyle)
  }
}
