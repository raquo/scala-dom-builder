# Scala DOM Builder

_Scala DOM Builder_ is a low level, unopinionated library for building and manipulating DOM trees (objects representing HTML tags and their attributes, properties and styles).

    "com.raquo" %%% "dombuilder" % "0.9"

This library can be used in two ways: 1) directly for simple things, and 2) as a flexible foundation for a more opinionated UI library.

## 1. Simple / Direct Usage

If all you want is a no-hassle, type-safe way to create and manipulate some DOM nodes / trees in Scala.js, use the provided `jsdom.simple` package, it lets you build DOM nodes using syntax similar to ScalaTags:

```scala
val scalaNode = div( // Create a Scala representation of a DOM node. Javascript DOM node gets created automatically at the same time in this example
  "Hello, ",
  a(href := "http://example.com", "World"),
  button(onClick := doSomething, "Do Something!")
)
 
// Scala Nodes map one-to-one to real Javascript DOM nodes 
val javascriptDomNode: org.scalajs.dom.Div = scalaNode.ref // This is how you get a reference to the real Javascript DOM node if you need it
  
mount(org.scalajs.dom.document.body, scalaNode) // Add the Javascript DOM Node to the rendered page
```

You can even build simple components using this low-level API:

```scala

import com.raquo.dombuilder.jsdom.simple.bundle._

class Counter {    // This doesn't need to be a class, all you need is to build a `SimpleElement` somehow
 
  private var count = 0    // Declare this component's internal state
 
  private val captionNode: SimpleText = count.toString    // Create node to represent the caption that shows the current count
                                                          // Uses string-to-textnode implicit conversion that you need to import
 
  private val incButton: SimpleElement[dom.Element] = button(    // Create a node to represent the "increment" button
    onClick := increment _,                                      // Add event listener to the button node
    "[ + ]"                                                      // Add a child node (which happens to be a text node) to the button node
  )
 
  val element: SimpleElement[dom.Element] = div(    // Create a node that will be either mounted as a root node or added as a child to another node.
    className := "CounterClassBlah",                // Add a CSS class name to this node (not used here, just an example)
    display.inlineBlock,                            // Set CSS display property to "inline-block" (just because)
    h1("Counter"),                                  // Create an h1 HTML node and add it as a child
    incButton,                                      // Add the "increment" button as a child node
    captionNode                                     // Add the caption as a child node
  )
 
  def increment(): Unit = {                         // Callback that will fire on every button click
    count += 1                                      // Update internal component state
    captionNode.ref.textContent = count.toString    // Update the DOM
  }
}
```

See [`example`](https://github.com/raquo/scala-dom-builder/tree/master/js/src/main/scala/com/raquo/dombuilder/jsdom/simple/example) directory for more examples.

This kind of design works great for small things, but if you're building rich, interactive web applications there's a better way – use (or make!) a higher level, more opinionated library on top of _Scala DOM Builder_. Read on for how to do that.

## 2. As a Foundation For Other UI Libraries

If you are building a DOM manipulation library in Scala, say you're inventing your own React, you will most probably want to represent either the DOM tree itself or the Component tree, whatever a _Component_ might be in your library. Scala DOM Builder's API was designed specifically for this use case – to serve as a low level foundation for other libraries.

This is similar to how Snabbdom is the foundation for many popular Javascript libraries, except Snabbdom gives you a virtual DOM solution. Scala DOM Builder is not a virtual DOM out of the box. We do not keep track of the DOM state other than the parent/child relationship of nodes. You could certainly implement a type safe virtual DOM library on top of _Scala DOM Builder_ though!

_Scala DOM Builder_ is specifically designed to serve as a foundation for more advanced, more opinionated libraries. By itself it is very flexible, it doesn't get in your way with its own opinions. It essentially lets you represent the mutable state of the DOM – your fancy functional reactive / monadic library will have to deal with that mutable state either way, but at least with Scala DOM Builder you can do it from a more convenient, type-safe API.

For an example on how to build your own library on top of _Scala DOM Builder_, check out the code of my library [Laminar](https://github.com/raquo/Laminar) which lets you build UI components in a functional reactive way. It was originally based on Snabbdom, and had a lot of unnecessary complexity in its code to deal with the impedance mismatch between the reactive streams API that it provides and the virtual DOM paradigm that it had to be aware of. It is now based on _Scala DOM Builder_, and its code is considerably smaller and faster as a result.

## Server Side Rendering

Even though Javascript DOM and the Scala.js interface to it are not available on the JVM, almost all _Scala DOM Builder_ components work just fine on the JVM if you implement a simple JS DOM API backend (see [domapi](https://github.com/raquo/scala-dom-builder/tree/master/shared/src/main/scala/com/raquo/dombuilder/generic/domapi) traits). So you can certainly build JVM things on top of _Scala DOM Builder_, but because the library is fairly new, this is uncharted territory right now.

One thing that I definitely want to implement on the JVM is rendering to static HTML code, perhaps even extensible enough to support React-style DOM preloading.

## My Related Projects

- [Laminar](https://github.com/raquo/Laminar) – Reactive UI library based on _Scala DOM Builder_
- [Scala DOM Types](https://github.com/raquo/scala-dom-types) – Type definitions that we use for all the HTML tags, attributes, properties, and styles
- [Scala DOM TestUtils](https://github.com/raquo/scala-dom-testutils) – Test that your Javascript DOM nodes match your expectations

## Author

Nikita Gazarov – [raquo.com](http://raquo.com)

## License

_Scala DOM Builder_ is provided under the [MIT license](https://github.com/raquo/scala-dom-builder/blob/master/LICENSE.md).
