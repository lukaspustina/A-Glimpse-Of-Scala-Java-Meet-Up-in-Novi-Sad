package b

object TypeInferenceAdvanced {

  object Methods {
    def toStr(i: Int) = i.toString

    def toStr(f: Float) = f.toString
  }

  object PolyMorphicMethods {
    // Structural type
    def toStr[T <: { def toString(): String } ](p: T) = p.toString

    def toList[T](p: T): List[T] = p :: Nil
  }

  object Calls {
    Methods toStr 1

    PolyMorphicMethods toStr "Scala"

    PolyMorphicMethods toStr 1

    PolyMorphicMethods toList 1
  }
}
