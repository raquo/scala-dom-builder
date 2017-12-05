package com.raquo.dombuilder.generic.nodes

import com.raquo.domtypes

/** This is the base trait for Scala DOM Builder nodes,
  * which are objects representing a tree of real JS DOM nodes.
  *
  * @tparam N if you're extending Scala DOM Builder (i.e. you're not using the [[com.raquo.dombuilder.jsdom.simple]] package,
  *           this type param can be the base trait of your nodes. This will ensure that your nodes are instantiated with
  *           the same shared logic that you define in [[N]], and that your nodes can only interact with each other, not
  *           other Scala DOM Builder nodes that might not have that shared logic.
  *
  * WARNING: This is a new library. The [[N]] mechanism is not battle tested for either usefulness or reliability,
  *          so it might very well change. I implemented [[N]] primarily looking at the needs of my Laminar project.
  */
trait Node[N, +Ref <: BaseRef, BaseRef] extends domtypes.generic.nodes.Node { this: N =>

  // @TODO[API] find a way to lock down internal API, maybe require a bogus implicit param that needs to be imported, or something

  type BaseElement = N with Element[N, BaseRef, BaseRef] // @TODO second param should be BaseElementRef, and this should not live here
  type BaseParentNode = N with ParentNode[N, BaseRef, BaseRef]
  type BaseChildNode = N with ChildNode[N, BaseRef, BaseRef]
  type BaseNode = N with Node[N, BaseRef, BaseRef]

  /** Reference to the real DOM node which this [[Node]] represents. */
  val ref: Ref
}
