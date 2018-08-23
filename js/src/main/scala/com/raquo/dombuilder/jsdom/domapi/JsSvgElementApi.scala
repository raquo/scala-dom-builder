package com.raquo.dombuilder.jsdom.domapi

import com.raquo.dombuilder.generic.domapi.SvgElementApi
import com.raquo.dombuilder.generic.nodes.Element
import com.raquo.domtypes.generic.keys.SvgAttr
import org.scalajs.dom

/** Regarding namespaces:
  * @see https://developer.mozilla.org/en-US/docs/Web/SVG/Namespaces_Crash_Course#Scripting_in_namespaced_XML
  * @see https://stackoverflow.com/q/6701705/2601788
  */
trait JsSvgElementApi[N] extends SvgElementApi[N, dom.svg.Element, dom.Node] {

  private val svgNamespaceUri = "http://www.w3.org/2000/svg"

  override def createSvgElement[Ref <: dom.svg.Element](element: N with Element[N, Ref, dom.Node]): Ref = {
    dom.document
      .createElementNS(namespaceURI = svgNamespaceUri, qualifiedName = element.tag.name)
      .asInstanceOf[Ref]
  }

  override def setSvgAttribute[V](element: BaseSvgElement, attr: SvgAttr[V], value: V): Unit = {
    val domValue = attr.codec.encode(value)
    if (domValue == null) { // End users should use `removeSvgAttribute` instead. This is to support boolean attributes.
      removeSvgAttribute(element, attr)
    } else {
      element.ref.setAttributeNS(namespaceURI = attr.namespace.orNull, qualifiedName = attr.name, value = domValue)
    }
  }

  override def removeSvgAttribute(element: BaseSvgElement, attr: SvgAttr[_]): Unit = {
    element.ref.removeAttributeNS(namespaceURI = attr.namespace.orNull, localName = localName(attr.name))
  }

  @inline private def localName(qualifiedName: String): String = {
    val nsPrefixLength = qualifiedName.indexOf(':')
    if (nsPrefixLength > -1) {
      qualifiedName.substring(nsPrefixLength + 1)
    } else qualifiedName
  }
}
