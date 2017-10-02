package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi
import com.raquo.domtypes.generic.Modifier

/** This trait represents a [[Node]] that can have a parent. All nodes except [[Root]] extend this trait. */
trait ChildNode[N, +Ref <: BaseRef, BaseRef]
  extends Node[N, Ref, BaseRef]
  with Modifier[N with ParentNode[N, BaseRef, BaseRef]]
{ this: N =>

  val treeApi: TreeApi[N, BaseRef]

  private type BaseParentNode = N with ParentNode[N, BaseRef, BaseRef]

  var _maybeParent: Option[BaseParentNode] = None

  def maybeParent: Option[BaseParentNode] = _maybeParent

  def setParent(newParent: BaseParentNode): Unit = {
    _maybeParent = Some(newParent)
  }

  def clearParent(): Unit = {
    _maybeParent = None
  }

  override def apply(parentNode: BaseParentNode): Unit = {
    parentNode.appendChild(this)(treeApi)
  }
}
