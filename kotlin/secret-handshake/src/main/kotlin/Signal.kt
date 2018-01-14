enum class Signal {
    WINK, DOUBLE_BLINK, CLOSE_YOUR_EYES, JUMP
}

class HandshakeCalculator {
  companion object {
      fun calculateHandshake(value: Int) : List<Signal> {
          val handshake = (Signal.WINK.ordinal..Signal.JUMP.ordinal)
                  .mapNotNull { bits ->
                      if (value.hasFlag(1 shl bits))
                          Signal.values()[bits]
                      else null
                  }
          return if (value.hasFlag(0b10000)) handshake.asReversed() else handshake
      }
  }
}

fun Int.hasFlag(flag: Int) = flag and this == flag