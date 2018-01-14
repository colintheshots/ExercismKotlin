import kotlin.math.exp

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {

    val abs = (real.sq() + imag.sq()).sqr()

    override fun equals(other: Any?) =
            other is ComplexNumber && other.real == real && other.imag == imag

    operator fun plus(other: ComplexNumber) =
            ComplexNumber(real + other.real, imag + other.imag)

    operator fun minus(other: ComplexNumber) =
            ComplexNumber(real - other.real, imag - other.imag)

    operator fun times(other: ComplexNumber) =
            ComplexNumber(real * other.real - imag * other.imag,
                imag * other.real + real * other.imag)

    operator fun div(other: ComplexNumber) =
            ComplexNumber((real * other.real + imag * other.imag)/(other.real.sq() + other.imag.sq()),
                (imag * other.real - real * other.imag)/(other.real.sq() + other.imag.sq()))

    fun conjugate() = ComplexNumber(real, -imag)

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + imag.hashCode()
        return result
    }
}

fun exponential(number: ComplexNumber) =
        ComplexNumber(exp(number.real) * number.imag.cos(), number.imag.sin())

fun Double.sq() = this * this
fun Double.sqr() = Math.sqrt(this)
fun Double.cos() = Math.cos(this)
fun Double.sin() = Math.sin(this)