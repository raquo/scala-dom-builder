package com.raquo.dombuilder

import com.raquo.dombuilder.jsdom.simple.bundle.{div, onClick}
import com.raquo.dombuilder.jsdom.simple.bundle.svg._
import com.raquo.dombuilder.jsdom.simple.implicits._

class SvgSpec extends UnitSpec {

  it("renders sample svg, sets attrs and responds to events") {

    var clickCount = 0

    val polylineEl = polyline(
      points := "20,20 40,25 60,40 80,120 120,140 200,180",
      fill := "none",
      stroke := "black",
      strokeWidth := "3",
      xlinkHref := "http:// example.com",
      onClick := (() => clickCount += 1)
    )

    val el = svg(
      height := "800",
      width := "500",
      polylineEl
    )

    mount(div(el))

    expectNode(div like (svg like (
      height is "800",
      width is "500",
      polyline like (
        points is "20,20 40,25 60,40 80,120 120,140 200,180",
        fill is "none",
        stroke is "black",
        strokeWidth is "3",
        xlinkHref is "http:// example.com"
      )
    )))

    (stroke := "red").apply(polylineEl)
    (xlinkHref := null).apply(polylineEl)

    expectNode(div like (svg like (
      height is "800",
      width is "500",
      polyline like (
        points is "20,20 40,25 60,40 80,120 120,140 200,180",
        fill is "none",
        stroke is "red", // <-- the change
        strokeWidth is "3",
        xlinkHref.isEmpty
      )
    )))

    polylineEl.maybeEventListeners.get.length shouldBe 1
    clickCount shouldBe 0

    // One event listener added
    simulateClick(polylineEl.ref)
    clickCount shouldBe 1

    unmount()
  }
}
