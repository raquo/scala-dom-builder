package com.raquo.dom.builder

import com.raquo.dom.builder.generic.nodes.RefNode
import com.raquo.dom.builder.jsdom.simple.SimpleRefNode
import com.raquo.dom.builder.jsdom.simple.builders.{SimpleCommentBuilder, SimpleTextBuilder}
import com.raquo.dom.testutils.EventSimulator
import com.raquo.dom.testutils.matching.{ExpectedNode, RuleImplicits}
import com.raquo.dom.testutils.scalatest.MountSpec
import org.scalajs.dom
import org.scalatest.Suite

trait SimpleSpec
  extends MountSpec[SimpleRefNode]
  with RuleImplicits[SimpleRefNode]
  with EventSimulator
{
  this: Suite =>

  def mount(node: RefNode[dom.Node], clue: String = defaultMountedElementClue): Unit = {
    mount(node.ref, clue)
  }

  def mount(clue: String, node: RefNode[dom.Node]): Unit = {
    mount(node.ref, clue)
  }

  override def comment: ExpectedNode[SimpleRefNode] = {
    new ExpectedNode(SimpleCommentBuilder)
  }

  override def textNode: ExpectedNode[SimpleRefNode] = {
    new ExpectedNode(SimpleTextBuilder)
  }
}
