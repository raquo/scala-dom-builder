package com.raquo.dombuilder.generic.syntax

import com.raquo.domtypes.generic.{Modifier, SetterBuilder}
import com.raquo.domtypes.generic.keys.Key

/** This class provides `key := value` syntax for supported key-value types.
  *
  * You need an implicit [[SetterBuilder]] value in scope for this to work.
  * Default ones are specified in [[com.raquo.dombuilder.generic.KeyImplicits]]
  */
class KeySyntax[+K <: Key](val key: K) extends AnyVal {

  def :=[V, M <: Modifier[_]] (value: V)(implicit setterBuilder: SetterBuilder[K, V, M]): M = {
    setterBuilder(key, value)
  }
}
