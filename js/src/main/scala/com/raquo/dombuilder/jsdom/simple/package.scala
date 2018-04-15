package com.raquo.dombuilder.jsdom

import com.raquo.dombuilder.generic.builders.SetterBuilders
import com.raquo.dombuilder.generic.nodes.{ChildNode, Element}
import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.builders.canonical.{CanonicalEventPropBuilder, CanonicalHtmlAttrBuilder, CanonicalPropBuilder, CanonicalReflectedAttrBuilder, CanonicalSvgAttrBuilder}
import com.raquo.domtypes.generic.defs.attrs.{AriaAttrs, Attrs, SvgAttrs}
import com.raquo.domtypes.generic.defs.props.Props
import com.raquo.domtypes.generic.defs.reflectedAttrs.ReflectedAttrs
import com.raquo.domtypes.generic.defs.styles.{Styles, Styles2}
import com.raquo.domtypes.generic.keys.{EventProp, HtmlAttr, Prop, SvgAttr}
import com.raquo.domtypes.jsdom.defs.eventProps.{ClipboardEventProps, ErrorEventProps, FormEventProps, KeyboardEventProps, MediaEventProps, MiscellaneousEventProps, MouseEventProps, WindowOnlyEventProps}
import com.raquo.domtypes.jsdom.defs.tags.{DocumentTags, EmbedTags, FormTags, GroupingTags, MiscTags, SectionTags, SvgTags, TableTags, TextTags}
import org.scalajs.dom

package object simple {

  type SimpleStyleSetter = Modifier[SimpleN with Element[SimpleN, dom.html.Element, dom.Node]]

  type ReflectedAttr[V, DomV] = HtmlAttr[V]

  object bundle
    // Attrs
    extends Attrs[HtmlAttr]
    with AriaAttrs[HtmlAttr]
    // Event Props
    with ClipboardEventProps[EventProp]
    with ErrorEventProps[EventProp]
    with FormEventProps[EventProp]
    with KeyboardEventProps[EventProp]
    with MediaEventProps[EventProp]
    with MiscellaneousEventProps[EventProp]
    with MouseEventProps[EventProp]
    with WindowOnlyEventProps[EventProp]
    // Props
    with Props[Prop]
    // Reflected Attrs
    with ReflectedAttrs[ReflectedAttr]
    // Styles
    with Styles[SimpleStyleSetter]
    with Styles2[SimpleStyleSetter]
    // Tags
    with DocumentTags[SimpleHtmlTag]
    with EmbedTags[SimpleHtmlTag]
    with FormTags[SimpleHtmlTag]
    with GroupingTags[SimpleHtmlTag]
    with MiscTags[SimpleHtmlTag]
    with SectionTags[SimpleHtmlTag]
    with TableTags[SimpleHtmlTag]
    with TextTags[SimpleHtmlTag]
    // Builders
    with CanonicalHtmlAttrBuilder
    with CanonicalReflectedAttrBuilder
    with CanonicalEventPropBuilder[dom.Event]
    with CanonicalPropBuilder
    with SimpleHtmlTagBuilder
    with SetterBuilders[SimpleN, dom.html.Element, dom.svg.Element, dom.Node]
    // Other
    with SimpleImplicits {

    @inline def comment(text: String): SimpleComment = new SimpleComment(text)

    object svg
      extends SvgTags[SimpleSvgTag]
      with SvgAttrs[SvgAttr]
      with CanonicalSvgAttrBuilder
      with SimpleSvgTagBuilder
  }

  def mount(
    container: dom.Element,
    child: SimpleN with ChildNode[SimpleN, dom.Node, dom.Node]
  ): SimpleRoot = new SimpleRoot(container, child)

  /** Import `implicits._` if you don't want to import `bundle._` */
  object implicits
    extends SimpleImplicits
    with SetterBuilders[SimpleN, dom.html.Element, dom.svg.Element, dom.Node]

  object builders {

    // @TODO[API] This is used mostly for testing. Should we keep it here?
    val commentNode: () => SimpleComment = () => bundle.comment("")

    val textNode: () => SimpleText = () => new SimpleText("")
  }
}
