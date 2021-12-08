package utils

object TrigonometricalOps {


  object Sinusoidal {
    /**
     * Computes the trigonometrical sinusoidal function for the input provided.
     * If the argument is NaN or an infinity, then the result is NaN.
     * If the argument is zero, then the result is a zero with the same sign as the argument.
     * @param yDilatation the wave amplitude, ie. the max deviation from zero before applying yTranslation
     * @param x the domain value where the function needs to be computed
     * @param phase the wave phase, i.e. the value of the sine is when x would be 0 (it causes horizontal translation)
     * @param yTranslation the translation applied to the y value after computing the A * sin (...) product
     * @return the value corresponding to input provided.
     */
    def sinusoidal(yDilatation: Float)(x: Float)(phase: Float)(yTranslation: Int): Int =
      (yDilatation * Math.sin(2 * Math.PI * x + phase)).toInt + yTranslation //should rename yDilatation to amplitude

    /**
     * Computes the [[sinusoidal]] function value with the input provided and a phase equals to 0.
     * If
     * @return
     */
    def zeroPhasedSinusoidal: (Float, Float, Int) => Int = sinusoidal(_: Float)(_: Float)(0)(_: Int)

    /**
     * Computes the [[sinusoidal]] function value with the input provided and a yTranslation equals to 0.
     * Everytime [[sinusoidal]] function would be called with a yTranslation equals to 0, this function should be
     * invoked instead.
     * @return the value corresponding to input provided.
     */
    def zeroYTranslatedSinusoidal: (Float, Float, Float) => Int = sinusoidal(_: Float)(_: Float)(_: Float)(0)

    /**
     * Computes the [[sinusoidal]] function value with the input provided and a phase equals to 0 and a yTranslation
     * equals to 0.
     * Everytime [[sinusoidal]] function would be called with a phase equals to 0 and a yTranslation equals to 0,
     * this function should be invoked instead.
     * @return the value corresponding to input provided.
     */
    def zeroPhasedZeroYTranslatedSinusoidal: (Float, Float) => Int = Curried.zeroPhasedSinusoidal(_: Float)(_: Float)(0)

    /**
     * Provides curried versions of [[Sinusoidal]] functions.
     * By supplying curried versions, function invocations can leverage, among the others, IDE automatic named
     * parameters and can be partially applied, in their turn.
     */
    object Curried {
      /**
       * Curried version of [[zeroPhasedSinusoidal]] that can be invoked with a syntax like that:
       * {{{
       *   zeroPhasedSinusoidal(1)(2)(3)
       * }}}
       * instead of non curried version:
       * {{{
       *    zeroPhasedSinusoidal(1, 2, 3)
       * }}}
       *
       * @return the sine wave value corresponding to the provided input.
       */
      def zeroPhasedSinusoidal: Float => Float => Int => Int = Sinusoidal.zeroPhasedSinusoidal.curried

      /**
       * Curried version of [[zeroYTranslatedSinusoidal]] that can be invoked with a syntax like that:
       * {{{
       *   zeroYTranslatedSinusoidal(1)(2)(3)
       * }}}
       * instead of non curried version:
       * {{{
       *    zeroYTranslatedSinusoidal(1, 2, 3)
       * }}}
       *
       * @return the sine wave value corresponding to the provided input.
       */
      def zeroYTranslatedSinusoidal: Float => Float => Float => Int = Sinusoidal.zeroYTranslatedSinusoidal.curried

      /**
       * Curried version of [[zeroPhasedZeroYTranslatedSinusoidal]] that can be invoked with a syntax like that:
       * {{{
       *   zeroPhasedZeroYTranslatedSinusoidal(1)(2)
       * }}}
       * instead of non curried version:
       * {{{
       *    zeroPhasedZeroYTranslatedSinusoidal(1, 2)
       * }}}
       *
       * @return the sine wave value corresponding to the provided input.
       */
      def zeroPhasedZeroYTranslatedSinusoidal: Float => Float => Int = Sinusoidal.zeroPhasedZeroYTranslatedSinusoidal.curried
    }

  }


}
