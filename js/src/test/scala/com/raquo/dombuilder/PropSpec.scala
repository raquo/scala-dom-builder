package com.raquo.dombuilder

import com.raquo.dombuilder.jsdom.simple.bundle
import com.raquo.dombuilder.jsdom.simple.bundle.{checked, div, input, option, selected, typ}
import com.raquo.dombuilder.jsdom.simple.implicits._

class PropSpec extends UnitSpec {

  it("sets props") {

    mount("input:checkbox", input(typ := "checkbox", checked := true))
    expectNode(input like (typ is "checkbox", checked is true))
    unmount()

    mount("input:text", input(bundle.value := "yolo"))
    expectNode(input like(bundle.value is "yolo"))
    unmount()

    mount("option=true", option(selected := true, bundle.value := "12"))
    expectNode(option like(selected is true, bundle.value is "12"))
    unmount()

    mount("option=false", option(selected := false, bundle.value := "12"))
    expectNode(option like(selected is false, bundle.value is "12"))
    unmount()

    mount("nested input:text", div("foo", input(bundle.value := "yolo")))
    expectNode(div like ("foo", input like(bundle.value is "yolo")))

    unmount()
  }
}
