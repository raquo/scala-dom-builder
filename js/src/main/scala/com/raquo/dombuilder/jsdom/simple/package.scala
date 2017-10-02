package com.raquo.dombuilder.jsdom

import com.raquo.dombuilder.generic.KeyImplicits
import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.nodes.{ChildNode, Element}
import com.raquo.dombuilder.generic.syntax.{EventPropSyntax, SyntaxImplicits}
import com.raquo.dombuilder.jsdom.nodes.{JsComment, JsElement, JsRoot, JsText}
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.{AttrBuilder, PropBuilder}
import com.raquo.domtypes.generic.defs.attrs.{Attrs, GlobalAttrs, InputAttrs}
import com.raquo.domtypes.generic.defs.props.{NodeProps, Props}
import com.raquo.domtypes.generic.defs.styles.{Styles, Styles2}
import com.raquo.domtypes.generic.keys.{Attr, EventProp, Prop}
import com.raquo.domtypes.jsdom.builders.EventPropBuilder
import com.raquo.domtypes.jsdom.defs.eventProps.{ClipboardEventProps, ErrorEventProps, FormEventProps, KeyboardEventProps, MouseEventProps}
import com.raquo.domtypes.jsdom.defs.tags.{DocumentTags, EmbedTags, FormTags, GroupingTags, MiscTags, SectionTags, TableTags, TextTags}
import org.scalajs.dom

package object simple {

  type SimpleElement[Ref <: dom.Element] = SimpleN with JsElement[SimpleN, Ref]
  type SimpleBaseElement = SimpleN with JsElement[SimpleN, dom.Element]
  type SimpleHtmlElement = SimpleN with JsElement[SimpleN, dom.html.Element]
  type SimpleText = SimpleN with JsText[SimpleN]
  type SimpleComment = SimpleN with JsComment[SimpleN]

  // @TODO can the bundles below be genericized outside of the simple package, with only some implicits as inputs?

  object attrs
    extends Attrs[Attr]
      with InputAttrs[Attr]
      with GlobalAttrs[Attr]
      with AttrBuilder

  object props
    extends Props[Prop]
      with NodeProps[Prop]
      with PropBuilder

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
    extends Styles[Modifier[SimpleN with Element[SimpleN, dom.Element, dom.Node]]]
    with SetterBuilders[SimpleN, dom.Element, dom.Node]
    with SimpleDomApi

  object styles2
    extends Styles2[Modifier[SimpleN with Element[SimpleN, dom.Element, dom.Node]]]
    with SetterBuilders[SimpleN, dom.Element, dom.Node]
    with SimpleDomApi

  def mount(
    container: dom.Element,
    child: SimpleN with ChildNode[SimpleN, dom.Node, dom.Node]
  ): JsRoot[SimpleN] = {
    new JsRoot[SimpleN](container, child, SimpleDomApi.treeApi) with SimpleN
  }

  /** Import `implicits._` to get access to composition methods like `key := value` and `tag(modifiers)` */
  object implicits
    extends KeyImplicits[SimpleN, dom.Element, dom.Node]
    with SyntaxImplicits[SimpleN, dom.Element, dom.Node, dom.Event, JsCallback]
    with SetterBuilders[SimpleN, dom.Element, dom.Node]
    with SimpleDomApi {

    implicit def eventPropToSyntax[Ev <: dom.Event](
      eventProp: EventProp[Ev]
    ): EventPropSyntax[SimpleN, dom.Element, dom.Node, Ev, dom.Event, JsCallback] = {
      // @TODO[IDE] IntelliJ 2017.2 needs explicit type params below to avoid erroring
      new EventPropSyntax[SimpleN, dom.Element, dom.Node, Ev, dom.Event, JsCallback](eventProp)
    }

    implicit def stringToTextNode(text: String): JsText[SimpleN] with SimpleN = {
      val newTextNode = builders.textNode()
      newTextNode.setText(text)(SimpleDomApi.textApi)
      newTextNode
    }
  }

  object builders {

    @inline def comment(text: String): JsComment[SimpleN] with SimpleN = {
      new JsComment[SimpleN](text, SimpleDomApi.commentApi, SimpleDomApi.treeApi) with SimpleN
    }

    // @TODO[API] This is used mostly for testing. Should we keep it here?
    val commentNode: () => JsComment[SimpleN] with SimpleN = () => comment("")

    val textNode: () => JsText[SimpleN] with SimpleN = () => {
      new JsText[SimpleN]("", SimpleDomApi.textApi, SimpleDomApi.treeApi) with SimpleN
    }
  }
}
