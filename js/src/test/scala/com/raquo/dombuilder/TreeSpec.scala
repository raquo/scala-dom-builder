package com.raquo.dombuilder

import com.raquo.dombuilder.jsdom.simple
import com.raquo.dombuilder.jsdom.simple.bundle
import com.raquo.dombuilder.jsdom.simple.bundle._

class TreeSpec extends UnitSpec {

  it("ChildNode.isDescendantOf") {

    val container = div(rel := "foo").ref

    val el0 = span("el0")
    val el10 = span("el10")
    val el11 = span("el11")
    val el2 = article("el2")
    val el3 = bundle.a("el3", href := "http://example.com")
    val root = simple.mount(container, el0)

    val otherEl = div("otherEl")
    val otherRoot = simple.mount(container, otherEl)

    val elx = b("elx")

    el0.isDescendantOf(root) shouldBe true
    el0.isDescendantOf(el10) shouldBe false
    el10.isDescendantOf(root) shouldBe false
    el11.isDescendantOf(el0) shouldBe false
    el11.isDescendantOf(el11) shouldBe false
    el0.isDescendantOf(otherRoot) shouldBe false
    el0.isDescendantOf(otherEl) shouldBe false

    el0.appendChild(el10)
    el0.appendChild(el11)

    el10.isDescendantOf(root) shouldBe true
    el11.isDescendantOf(root) shouldBe true
    el10.isDescendantOf(el0) shouldBe true
    el11.isDescendantOf(el0) shouldBe true
    el11.isDescendantOf(el10) shouldBe false
    el11.isDescendantOf(el2) shouldBe false
    el11.isDescendantOf(otherRoot) shouldBe false
    el11.isDescendantOf(otherEl) shouldBe false

    el10.appendChild(el2)

    el2.isDescendantOf(root) shouldBe true
    el2.isDescendantOf(el0) shouldBe true
    el2.isDescendantOf(el10) shouldBe true
    el2.isDescendantOf(el11) shouldBe false
    el2.isDescendantOf(el2) shouldBe false
    el2.isDescendantOf(otherRoot) shouldBe false
    el2.isDescendantOf(otherEl) shouldBe false

    el2.appendChild(el3)

    el3.isDescendantOf(root) shouldBe true
    el3.isDescendantOf(el0) shouldBe true
    el3.isDescendantOf(el2) shouldBe true
    el3.isDescendantOf(el11) shouldBe false
    el3.isDescendantOf(elx) shouldBe false
    el3.isDescendantOf(otherRoot) shouldBe false
    el3.isDescendantOf(otherEl) shouldBe false

    el3.setParent(Some(elx))

    el3.isDescendantOf(elx) shouldBe true
    el3.isDescendantOf(root) shouldBe false
    el3.isDescendantOf(el0) shouldBe false
    el3.isDescendantOf(el11) shouldBe false
    el3.isDescendantOf(el2) shouldBe false
    el3.isDescendantOf(otherRoot) shouldBe false
    el3.isDescendantOf(otherEl) shouldBe false

    el3.setParent(Some(el10))

    el3.isDescendantOf(root) shouldBe true
    el3.isDescendantOf(el0) shouldBe true
    el3.isDescendantOf(el10) shouldBe true
    el3.isDescendantOf(el11) shouldBe false
    el3.isDescendantOf(el2) shouldBe false
    el3.isDescendantOf(elx) shouldBe false
    el3.isDescendantOf(otherRoot) shouldBe false
    el3.isDescendantOf(otherEl) shouldBe false
  }

//  it("")

}
