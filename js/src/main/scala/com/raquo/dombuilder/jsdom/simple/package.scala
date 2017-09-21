package com.raquo.dombuilder.jsdom

import com.raquo.dombuilder.generic.modifiers.{StringStyleSetter, StyleSetter}
import com.raquo.dombuilder.generic.simple.SharedSimple
import com.raquo.dombuilder.jsdom.builders.StyleBuilder
import com.raquo.dombuilder.jsdom.nodes.ChildNode
import com.raquo.dombuilder.jsdom.simple.builders.{SimpleTag, SimpleTagBuilder}
import com.raquo.dombuilder.jsdom.simple.nodes.{SimpleElement, SimpleRoot, SimpleText}

import com.raquo.domtypes.generic.defs.styles.{Styles, Styles2}
import com.raquo.domtypes.generic.keys.EventProp
import com.raquo.domtypes.jsdom.builders.EventPropBuilder
import com.raquo.domtypes.jsdom.defs.eventProps.{ClipboardEventProps, ErrorEventProps, FormEventProps, KeyboardEventProps, MouseEventProps}
import com.raquo.domtypes.jsdom.defs.tags.{DocumentTags, EmbedTags, FormTags, GroupingTags, MiscTags, SectionTags, TableTags, TextTags}

import org.scalajs.dom

package object simple extends SharedSimple {

  type SimpleHtmlElement = SimpleElement[dom.html.Element]

  /** Import `implicits._` to get access to composition methods := and TagSyntax.apply */
  object implicits extends syntax.Implicits {

    implicit def textToNode(text: String): SimpleText = {
      new SimpleText(text)
    }
  }

  object events
    extends MouseEventProps[EventProp]
    with FormEventProps[EventProp]
    with KeyboardEventProps[EventProp]
    with ClipboardEventProps[EventProp]
    with ErrorEventProps[EventProp]
    with EventPropBuilder

  object tags
    extends DocumentTags[SimpleTag]
    with GroupingTags[SimpleTag]
    with TextTags[SimpleTag]
    with FormTags[SimpleTag]
    with SectionTags[SimpleTag]
    with EmbedTags[SimpleTag]
    with TableTags[SimpleTag]
    with SimpleTagBuilder

  object tags2
    extends MiscTags[SimpleTag]
    with SimpleTagBuilder

  object styles
    extends Styles[StyleSetter, StringStyleSetter]
    with StyleBuilder

  object styles2
    extends Styles2[StyleSetter, StringStyleSetter]
    with StyleBuilder

  def mount(
    container: dom.Element,
    child: SimpleRefNode with ChildNode[SimpleRefNode, dom.Element]
  ): SimpleRoot = {
    new SimpleRoot(container, child)
  }
}
