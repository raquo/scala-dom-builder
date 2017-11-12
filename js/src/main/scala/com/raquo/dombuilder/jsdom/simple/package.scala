package com.raquo.dombuilder.jsdom

import com.raquo.dombuilder.generic.KeyImplicits
import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.nodes.{ChildNode, Element}
import com.raquo.dombuilder.generic.syntax.{EventPropSyntax, SyntaxImplicits}
import com.raquo.dombuilder.jsdom.nodes.{JsComment, JsElement, JsRoot, JsText}
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.canonical.{CanonicalAttrBuilder, CanonicalEventPropBuilder, CanonicalPropBuilder, CanonicalReflectedAttrBuilder}
import com.raquo.domtypes.generic.defs.attrs.{AriaAttrs, Attrs}
import com.raquo.domtypes.generic.defs.props.Props
import com.raquo.domtypes.generic.defs.reflectedAttrs.ReflectedAttrs
import com.raquo.domtypes.generic.defs.styles.{Styles, Styles2}
import com.raquo.domtypes.generic.keys.{Attr, EventProp, Prop}
import com.raquo.domtypes.jsdom.defs.eventProps.{ClipboardEventProps, ErrorEventProps, FormEventProps, KeyboardEventProps, MediaEventProps, MiscellaneousEventProps, MouseEventProps, WindowEventProps}
import com.raquo.domtypes.jsdom.defs.tags.{DocumentTags, EmbedTags, FormTags, GroupingTags, MiscTags, SectionTags, TableTags, TextTags}
import org.scalajs.dom

package object simple {

  type SimpleElement[Ref <: dom.Element] = SimpleN with JsElement[SimpleN, Ref]
  type SimpleBaseElement = SimpleN with JsElement[SimpleN, dom.Element]
  type SimpleHtmlElement = SimpleN with JsElement[SimpleN, dom.html.Element]
  type SimpleText = SimpleN with JsText[SimpleN]
  type SimpleComment = SimpleN with JsComment[SimpleN]

  type SimpleStyleSetter = Modifier[SimpleN with Element[SimpleN, dom.Element, dom.Node]]

  type ReflectedAttr[V, DomV] = Attr[V]

  object bundle
    // Attrs
    extends Attrs[Attr]
    with AriaAttrs[Attr]
    // Event Props
    with ClipboardEventProps[EventProp]
    with ErrorEventProps[EventProp]
    with FormEventProps[EventProp]
    with KeyboardEventProps[EventProp]
    with MediaEventProps[EventProp]
    with MiscellaneousEventProps[EventProp]
    with MouseEventProps[EventProp]
    with WindowEventProps[EventProp]
    // Props
    with Props[Prop]
    // Reflected Attrs
    with ReflectedAttrs[ReflectedAttr]
    // Styles
    with Styles[SimpleStyleSetter]
    with Styles2[SimpleStyleSetter]
    // Tags
    with DocumentTags[SimpleTag]
    with EmbedTags[SimpleTag]
    with FormTags[SimpleTag]
    with GroupingTags[SimpleTag]
    with MiscTags[SimpleTag]
    with SectionTags[SimpleTag]
    with TableTags[SimpleTag]
    with TextTags[SimpleTag]
    // Builders
    with CanonicalAttrBuilder
    with CanonicalReflectedAttrBuilder
    with CanonicalEventPropBuilder[dom.Event]
    with CanonicalPropBuilder
    with SimpleTagBuilder
    with SetterBuilders[SimpleN, dom.Element, dom.Node]
    // Other
    with SimpleImplicits

  def mount(
    container: dom.Element,
    child: SimpleN with ChildNode[SimpleN, dom.Node, dom.Node]
  ): SimpleN with JsRoot[SimpleN] = {
    new JsRoot[SimpleN](container, child, SimpleDomApi.treeApi) with SimpleN
  }

  /** Import `implicits._` if you don't want to import `bundle._` */
  object implicits
    extends SimpleImplicits
    with SetterBuilders[SimpleN, dom.Element, dom.Node]

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
