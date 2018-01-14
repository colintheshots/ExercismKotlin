import java.math.BigInteger

class Board {
    companion object {
        fun getGrainCountForSquare(square : Int) : BigInteger {
            require(square in 1..64) {"Only integers between 1 and 64 (inclusive) are allowed"}
            return BigInteger.valueOf(1L).shiftLeft(square - 1)
        }

        fun getTotalGrainCount() : BigInteger {
            return BigInteger.valueOf(1L).shiftLeft(64) - BigInteger.ONE
        }
    }
}