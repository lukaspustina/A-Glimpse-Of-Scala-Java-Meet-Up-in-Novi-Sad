package b

object TypeInference {

  val i: Int = 10
  
  object Vars {
    val s: String = "I'm a string"
    val s1 = "Me too."
  }
  
  object Functions {
    def inc(p: Int): Int = {
      p + 1
    }
    
    def inc1(p: Int) = {
      p + 1
    }
    
    def inc2(p: Int) = p + 1
 
    def increase(p: Int): Int = p + 1
  }
 
  object Calls {
    val pInc: Int = Functions.increase(i)
    
    val pInc1 = Functions increase i
  } 
}
