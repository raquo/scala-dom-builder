package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.EventApi
import com.raquo.dombuilder.generic.modifiers.EventPropSetter

import scala.collection.mutable

/** This trait represents a [[Node]] that fires events that you can listen for.
  *
  * This is separate from [[Element]] partially because you might not be able
  * to listen for any events in the JVM environment.
  * */
trait EventfulNode[N, +Ref <: BaseElementRef, BaseElementRef <: BaseRef, BaseRef, BaseEvent, Callback[-_]] extends Element[N, Ref, BaseRef] { this: N =>

  type BaseCallback = Callback[BaseEvent]
  type BaseEventPropSetter = _EventPropSetter[_]
  private type _EventPropSetter[Ev <: BaseEvent] = EventPropSetter[N, BaseElementRef, BaseRef, Ev, BaseEvent, Callback]
  private type _EventApi = EventApi[N, BaseElementRef, BaseRef, BaseEvent, Callback]

  // @TODO[Naming] We reuse EventPropSetter to represent an active event listener. Makes for a bit confusing naming.
  protected[this] var _maybeEventListeners: Option[mutable.Buffer[BaseEventPropSetter]] = None

  @inline def maybeEventListeners: Option[mutable.Buffer[BaseEventPropSetter]] = _maybeEventListeners

  /** @return Whether listener was added (false if such a listener has already been present) */
  def addEventListener[Ev <: BaseEvent](eventPropSetter: _EventPropSetter[Ev])(implicit eventApi: _EventApi): Boolean = {
    val shouldAddListener = indexOfEventListener(eventPropSetter) == -1
    if (shouldAddListener) {
      // 1. Update this node
      if (_maybeEventListeners.isEmpty) {
        _maybeEventListeners = Some(mutable.Buffer(eventPropSetter))
      } else {
        _maybeEventListeners.foreach { eventListeners =>
          eventListeners += eventPropSetter
        }
      }
      // 2. Update the DOM
      eventApi.addEventListener(this, eventPropSetter)
    }
    shouldAddListener
  }

  def removeEventListener[Ev <: BaseEvent](eventPropSetter: _EventPropSetter[Ev])(implicit eventApi: _EventApi): Boolean = {
    val index = indexOfEventListener(eventPropSetter)
    val shouldRemoveListener = index != -1
    if (shouldRemoveListener) {
      // 1. Update this node
      _maybeEventListeners.foreach(eventListeners => eventListeners.remove(index))
      // 2. Update the DOM
      eventApi.removeEventListener(this, eventPropSetter)
    }
    shouldRemoveListener
  }

  def indexOfEventListener[Ev <: BaseEvent](eventPropSetter: _EventPropSetter[Ev]): Int = {
    // Note: Ugly for performance.
    //  - We want to reduce usage of Scala's collections and anonymous functions
    //  - js.Array is unaware of Scala's `equals` method
    val notFoundIndex = -1
    if (_maybeEventListeners.isEmpty) {
      notFoundIndex
    } else {
      var found = false
      var index = 0
      _maybeEventListeners.foreach { listeners =>
        while (!found && index < listeners.length) {
          if (eventPropSetter equals listeners(index)) {
            found = true
          } else {
            index += 1
          }
        }
      }
      if (found) index else notFoundIndex
    }
  }
}
