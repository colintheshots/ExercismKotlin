import java.math.BigDecimal

class SpaceAge(private val age: Long) {
    constructor(age: Int) : this(age.toLong())
    fun onEarth() : Double {return age.secs(1.0)}
    fun onMercury() : Double {return age.secs(0.2408467)}
    fun onVenus() : Double {return age.secs(0.61519726)}
    fun onMars() : Double {return age.secs(1.8808158)}
    fun onJupiter() : Double {return age.secs(11.862615)}
    fun onSaturn() : Double {return age.secs(29.447498)}
    fun onUranus() : Double {return age.secs(84.016846)}
    fun onNeptune() : Double {return age.secs(164.79132)}
}

fun Long.secs(earthYears: Double) : Double = this.div(earthYears * 31557600).roundTwoPlaces()
fun Double.roundTwoPlaces() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()