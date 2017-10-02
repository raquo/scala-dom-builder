package com.raquo.dombuilder.jsdom.simple.example.components

import com.raquo.dombuilder.generic.modifiers.Setter
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.dombuilder.jsdom.nodes.JsElement
import com.raquo.dombuilder.jsdom.simple.implicits._
import com.raquo.dombuilder.jsdom.simple.tags.{div, input}
import com.raquo.dombuilder.jsdom.simple.{SimpleHtmlElement, SimpleN, SimpleText, attrs, events, styles}
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.Style
import org.scalajs.dom

class Toggle(initialIsChecked: Boolean) {

  private var _isChecked = false

  private val inputNode = input(
    attrs.typ := "checkbox",
    events.onChange := (() => setChecked(!_isChecked))
  )

  private val captionNode: SimpleText = captionText

  val element: SimpleHtmlElement = div(
    attrs.cls := "Toggle",
    div(
      inputNode,
      captionNode
    )
  )

  setChecked(initialIsChecked, force = true)

  @inline def isChecked: Boolean = _isChecked

  def setChecked(newIsChecked: Boolean, force: Boolean = false): Unit = {
    if (force || _isChecked != newIsChecked) {
      _isChecked = !_isChecked
      (attrs.checked := newIsChecked).apply(inputNode)
      captionNode.setText(captionText)
      (styles.color := color).apply(element)
    }
  }

  def color: String = {
    if (_isChecked) "green" else "red"
  }

  def captionText: String = {
    if (_isChecked) "ON" else "off"
  }
}
