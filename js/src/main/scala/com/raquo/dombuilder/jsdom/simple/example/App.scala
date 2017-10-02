package com.raquo.dombuilder.jsdom.simple.example

import com.raquo.dombuilder.jsdom.simple
import com.raquo.dombuilder.jsdom.simple.SimpleHtmlElement
import com.raquo.dombuilder.jsdom.simple.example.components.{Counter, Toggle}
import org.scalajs.dom
import org.scalajs.dom.document

object App {

  def main(args: Array[String]): Unit = {
    document.addEventListener("DOMContentLoaded", (e: dom.Event) => {
      dom.console.log("=== DOMContentLoaded ===")

      val container = document.getElementById("app-container")
      container.textContent = ""

      val root = simple.mount(container, renderCounter())
//      val root = simple.mount(container, renderToggle())

      // root.unmount()
    })
  }

  def renderCounter(): SimpleHtmlElement = {
    val counter = new Counter()
    counter.element
  }

  def renderToggle(): SimpleHtmlElement = {
    val toggle = new Toggle(initialIsChecked = true)

//    dom.window.setInterval(() => {
//      toggle.setChecked(!toggle.isChecked)
//    }, timeout = 500)

    toggle.element
  }
}
