package com.raquo.dombuilder.jsdom.simple.example.components

import com.raquo.dombuilder.jsdom.simple.bundle.{checked, className, color, div, input, onChange, typ}
import com.raquo.dombuilder.jsdom.simple.implicits._
import com.raquo.dombuilder.jsdom.simple.{SimpleHtmlElement, SimpleText}
import org.scalajs.dom

class Toggle(initialIsChecked: Boolean) {

  private var _isChecked = false

  private val inputNode = input(
    typ := "checkbox",
    onChange := (() => setChecked(!_isChecked))
  )

  private val captionNode: SimpleText = captionText

  val element: SimpleHtmlElement[dom.html.Element] = div(
    className := "Toggle",
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
      (checked := newIsChecked).apply(inputNode)
      captionNode.ref.textContent = captionText
      (color := captionColor).apply(element)
    }
  }

  def captionColor: String = {
    if (_isChecked) "green" else "red"
  }

  def captionText: String = {
    if (_isChecked) "ON" else "off"
  }
}
