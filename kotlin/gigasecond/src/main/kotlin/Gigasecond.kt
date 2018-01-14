import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Gigasecond(startingDate: LocalDateTime) {
    constructor(startingDate: LocalDate) : this(startingDate.atStartOfDay())
    val seconds : Long = (1..9).fold(1L) { total, _ -> total * 10 }
    val date : LocalDateTime = startingDate.plus(seconds, ChronoUnit.SECONDS)
}