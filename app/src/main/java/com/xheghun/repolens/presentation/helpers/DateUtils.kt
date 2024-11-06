import org.threeten.bp.Instant
import org.threeten.bp.Duration

//We're using the ThreeTen library as this provides backward compatibility for older android APIs
fun timeAgo(timeString: String): String {
    val dateTime = Instant.parse(timeString)
    val now = Instant.now()
    val duration = Duration.between(dateTime, now)

    return when {
        duration < Duration.ofMinutes(1) -> "just now"
        duration < Duration.ofHours(1) -> "${duration.toMinutes()} minute(s) ago"
        duration < Duration.ofDays(1) -> "${duration.toHours()} hour(s) ago"
        duration < Duration.ofDays(7) -> "${duration.toDays()} day(s) ago"
        duration < Duration.ofDays(30) -> "${duration.toDays() / 7} week(s) ago"
        duration < Duration.ofDays(365) -> "${duration.toDays() / 30} month(s) ago"
        else -> "${duration.toDays() / 365} year(s) ago"
    }
}
