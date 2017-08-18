package com.raquo.dombuilder.dombuilder.generic.defs.eventProps

import com.raquo.dombuilder.dombuilder.generic.builders.BoundedBuilder

trait FormEventProps[P[_ <: DomEvent], DomEvent] { this: BoundedBuilder[P, DomEvent] =>

  /**
    * The blur event is raised when an element loses focus.
    *
    * MDN
    */
  lazy val onBlur: P[DomEvent] = build("blur")

  /**
    * The change event is fired for input, select, and textarea elements
    * when a change to the element's value is committed by the user.
    *
    * MDN
    */
  lazy val onChange: P[DomEvent] = build("change")

  /**
    * The focus event is raised when the user sets focus on the given element.
    *
    * MDN
    */
  lazy val onFocus: P[DomEvent] = build("focus")

  /**
    * The select event only fires when text inside a text input or textarea is
    * selected. The event is fired after the text has been selected.
    *
    * MDN
    */
  lazy val onSelect: P[DomEvent] = build("select")

  /**
    * The submit event is raised when the user clicks a submit button in a form
    * (<input type="submit"/>).
    *
    * MDN
    */
  lazy val onSubmit: P[DomEvent] = build("submit")

  /**
    * The reset event is fired when a form is reset.
    *
    * MDN
    */
  lazy val onReset: P[DomEvent] = build("reset")

  /**
    * Script to be run when a context menu is triggered
    */
  lazy val onContextMenu: P[DomEvent] = build("contextmenu")

  /**
    * Script to be run when an element gets user input
    */
  lazy val onInput: P[DomEvent] = build("input")

  /**
    * Script to be run when an element is invalid
    */
  lazy val onInvalid: P[DomEvent] = build("invalid")

  /**
    * Fires when the user writes something in a search field (for <input="search">)
    */
  lazy val onSearch: P[DomEvent] = build("search")
}
