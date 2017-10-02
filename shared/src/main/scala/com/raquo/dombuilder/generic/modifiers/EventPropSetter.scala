package com.raquo.dombuilder.generic.modifiers

import com.raquo.dombuilder.generic.domapi.EventApi
import com.raquo.dombuilder.generic.nodes.EventfulNode
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.EventProp

/** @param useCapture – see https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener about "useCapture"
  */
class EventPropSetter[N, BaseElementRef <: BaseRef, BaseRef, Ev <: BaseEvent, BaseEvent, Callback[-_]](
  val key: EventProp[Ev],
  val value: Ev => Unit,
  val useCapture: Boolean
)(
  implicit eventApi: EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]
) extends Modifier[N with EventfulNode[N, BaseElementRef, BaseElementRef, BaseRef, BaseEvent, Callback]] {

  /** explanation in [[com.raquo.dombuilder.generic.domapi.EventApi.callback]] */
  val domValue: Callback[Ev] = eventApi.callback(value)

  override def apply(node: N with EventfulNode[N, BaseElementRef, BaseElementRef, BaseRef, BaseEvent, Callback]): Unit = {
    node.addEventListener(this)
  }

  override def equals(that: Any): Boolean = {
    that match {
      case setter: EventPropSetter[N, _, _, _, _, _] if (key == setter.key) && (domValue == setter.domValue) => true
      case _ => false
    }
  }
}
