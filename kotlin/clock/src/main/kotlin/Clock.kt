import java.lang.Math.abs

class Clock(hours: Int, minutes: Int) {
    private var minutesIntoDay = (hours * 60 + minutes).toPositiveMinutes()
    private var displayedHours = minutesIntoDay / 60 % 24
    private var displayedMinutes = minutesIntoDay % 60

    override fun toString(): String {
        return "${displayedHours.formatTime()}:${displayedMinutes.formatTime()}"
    }

    fun add(minutes: Int) {
        minutesIntoDay += minutes
        displayedHours = minutesIntoDay.toPositiveMinutes() / 60 % 24
        displayedMinutes = minutesIntoDay.toPositiveMinutes()  % 60
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Clock) {
            displayedHours == other.displayedHours && displayedMinutes == other.displayedMinutes
        } else false
    }

    private fun Int.formatTime() = java.lang.String.format("%02d", this)
    private fun Int.toPositiveMinutes() : Int {
        var currentMinutes = this
        while (currentMinutes < 0) {
            currentMinutes = 24 * 60 - abs(currentMinutes)
        }
        return currentMinutes
    }
}