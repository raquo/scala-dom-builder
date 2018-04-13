package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.Modifier
import com.raquo.domtypes.generic.keys.Key

/** This class provides `key := value` syntax for supported key-value types.
  *
  * Event props use slightly different syntax defined in [[EventPropSyntax]]
  *
  * You need an implicit setter builder value in scope for this to work.
  * Default ones are specified in [[com.raquo.dombuilder.generic.KeyImplicits]]
  */
class KeySyntax[+K <: Key](val key: K) extends AnyVal {

  @inline def :=[V, M <: Modifier[_]](value: V)(implicit setterBuilder: (K, V) => M): M = {
    setterBuilder(key, value)
  }
}
