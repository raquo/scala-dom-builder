package com.raquo.dombuilder

import scala.scalajs.js

package object jsdom {

  type JsCallback[-Ev] = js.Function1[Ev, Unit]
}
