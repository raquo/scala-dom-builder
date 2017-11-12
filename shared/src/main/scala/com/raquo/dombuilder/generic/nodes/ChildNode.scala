package com.raquo.dombuilder.generic.nodes

import com.raquo.dombuilder.generic.domapi.TreeApi
import com.raquo.domtypes.generic.Modifier

import scala.annotation.tailrec

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

  @inline def isDescendantOf(parent: BaseParentNode): Boolean = {
    ChildNode.isDescendantOf(child = this, parent)
  }

  def clearParent(): Unit = {
    _maybeParent = None
  }

  override def apply(parentNode: BaseParentNode): Unit = {
    parentNode.appendChild(this)(treeApi)
  }
}

object ChildNode {

  @tailrec final def isDescendantOf[N, BaseRef](
    child: ChildNode[N, BaseRef, BaseRef],
    parent: ParentNode[N, BaseRef, BaseRef]
  ): Boolean = {
    child.maybeParent match {
      case Some(`parent`) => true
      case Some(intermediateParent: ChildNode[N, BaseRef, BaseRef]) => isDescendantOf(intermediateParent, parent)
      case _ => false
    }
  }
}
