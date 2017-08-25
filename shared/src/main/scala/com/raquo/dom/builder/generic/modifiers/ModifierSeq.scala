package com.raquo.dom.builder.generic.modifiers

class ModifierSeq[N](
  val modifiers: Iterable[Modifier[N]]
) extends Modifier[N] {

  def applyTo(node: N): Unit = {
    modifiers.foreach(_.applyTo(node))
  }
}
