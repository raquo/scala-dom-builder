package com.raquo.dombuilder.generic.modifiers

import com.raquo.domtypes.generic.Modifier

class ModifierSeq[N](
  val modifiers: Iterable[Modifier[N]]
) extends Modifier[N] {

  def apply(node: N): Unit = {
    modifiers.foreach(_(node))
  }
}
