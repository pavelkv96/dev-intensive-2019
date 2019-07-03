package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

/**
 *Created by Pavel on 29.06.2019.
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    return SimpleDateFormat(pattern, Locale("ru")).format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    val s = when (val diffAbs = abs(diff)) {
        in (0 * SECOND)..(1 * SECOND) -> "только что"
        in (1 * SECOND)..(45 * SECOND) -> "несколько секунд"
        in (45 * SECOND)..(75 * SECOND) -> "минуту"
        in (75 * SECOND)..(45 * MINUTE) -> TimeUnits.MINUTE.plural((diffAbs/ MINUTE).toInt())
        in (45 * MINUTE)..(75 * MINUTE) -> "час"
        in (75 * MINUTE)..(22 * HOUR) -> TimeUnits.HOUR.plural((diffAbs/ HOUR).toInt())
        in (22 * HOUR)..(26 * HOUR) -> "день"
        in (26 * HOUR)..(360 * DAY) -> TimeUnits.DAY.plural((diffAbs/ DAY).toInt())
        else -> if (diff > 360 * DAY) "более года назад" else "более чем через год"
    }

    val after = if (diff in (-360 * DAY..-2 * SECOND)) "через " else ""
    val before = if (diff in (2 * SECOND..360 * DAY)) " назад" else ""

    return "$after$s$before"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return "$value ${Utils.getPluralForm(this, value)}"
    }
}