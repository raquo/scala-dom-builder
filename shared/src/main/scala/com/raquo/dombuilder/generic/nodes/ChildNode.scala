package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes.generic.Modifier

trait ChildNode[N, +Ref <: BaseRef, BaseRef] extends RefNode[Ref] with Modifier[ParentNode[N, BaseRef, BaseRef]] { this: N =>

  private[this] var _maybeParent: Option[ParentNode[N, BaseRef, BaseRef]] = None

  override def apply(node: ParentNode[N, BaseRef, BaseRef]): Unit = {
    node.appendChild(this)
  }

  def maybeParent: Option[ParentNode[N, BaseRef, BaseRef]] = _maybeParent

  def setParent(newParent: ParentNode[N, BaseRef, BaseRef]): Unit = {
    _maybeParent = Some(newParent)
  }

  def clearParent(): Unit = {
    _maybeParent = None
  }
}
