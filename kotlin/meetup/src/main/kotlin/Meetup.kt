import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters

class Meetup(private val month: Int, private val year: Int) {
    private class TeenthAdjuster(val dayOfWeek: DayOfWeek) : TemporalAdjuster {
        override fun adjustInto(temporal: Temporal?): Temporal {
            var localDate = LocalDate.from(temporal)
            while (localDate.dayOfMonth < 13) {
                localDate = localDate.with(TemporalAdjusters.next(dayOfWeek))
            }
            return temporal!!.with(localDate)
        }
    }

    fun day(dayOfWeek: DayOfWeek, meetupSchedule: MeetupSchedule) : LocalDate {
        val firstOfMonth = LocalDate.of(year, month, 1)
        val dayOfMonth = when (meetupSchedule) {
            MeetupSchedule.FIRST -> firstOfMonth.with(TemporalAdjusters.firstInMonth(dayOfWeek))
            MeetupSchedule.SECOND -> firstOfMonth.with(TemporalAdjusters.dayOfWeekInMonth(2, dayOfWeek))
            MeetupSchedule.THIRD -> firstOfMonth.with(TemporalAdjusters.dayOfWeekInMonth(3, dayOfWeek))
            MeetupSchedule.FOURTH -> firstOfMonth.with(TemporalAdjusters.dayOfWeekInMonth(4, dayOfWeek))
            MeetupSchedule.LAST -> firstOfMonth.with(TemporalAdjusters.lastInMonth(dayOfWeek))
            MeetupSchedule.TEENTH -> firstOfMonth.with(TeenthAdjuster(dayOfWeek))
        }.dayOfMonth
        return LocalDate.of(year, month, dayOfMonth)
    }
}