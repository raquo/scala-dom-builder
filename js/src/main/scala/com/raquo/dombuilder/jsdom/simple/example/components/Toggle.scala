package com.raquo.dombuilder.jsdom.simple.example.components

import com.raquo.dombuilder.jsdom.simple.bundle.{checked, className, div, input, onChange, typ, color}
import com.raquo.dombuilder.jsdom.simple.implicits._
import com.raquo.dombuilder.jsdom.simple.{SimpleHtmlElement, SimpleText}

class Toggle(initialIsChecked: Boolean) {

  private var _isChecked = false

  private val inputNode = input(
    typ := "checkbox",
    onChange := (() => setChecked(!_isChecked))
  )

  private val captionNode: SimpleText = captionText

  val element: SimpleHtmlElement = div(
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
      captionNode.setText(captionText)
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
