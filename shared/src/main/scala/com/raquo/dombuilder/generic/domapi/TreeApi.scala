package com.raquo.dombuilder.generic.domapi

import com.raquo.dombuilder.generic.nodes.{ChildNode, ParentNode}

trait TreeApi[N, BaseRef] {

  type BaseChildNode = N with ChildNode[N, BaseRef, BaseRef]
  type BaseParentNode = N with ParentNode[N, BaseRef, BaseRef]

  def appendChild(parent: BaseParentNode, child: BaseChildNode): Boolean

  def removeChild(parent: BaseParentNode, child: BaseChildNode): Boolean

  def insertBefore(
    parent: BaseParentNode,
    newChild: BaseChildNode,
    referenceChild: BaseChildNode
  ): Boolean

  def replaceChild(
    parent: BaseParentNode,
    newChild: BaseChildNode,
    oldChild: BaseChildNode
  ): Boolean
}
