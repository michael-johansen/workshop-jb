package ii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val yearDiff = this.year.compareTo(other.year)
        val monthDiff = this.month.compareTo(other.month)
        return when {
            yearDiff != 0 -> yearDiff
            monthDiff != 0 -> monthDiff
            else -> dayOfMonth.compareTo(other.dayOfMonth)
        };
    }

    fun rangeTo(endDate: MyDate): DateRange = DateRange(this, endDate)

    operator fun plus(interval: TimeInterval): MyDate {
        return plus(MultipleTimeInterval(1, interval))    }

    operator fun plus(interval: MultipleTimeInterval): MyDate {
        val field = when (interval.timeInterval) {
            TimeInterval.DAY -> Calendar.DAY_OF_MONTH
            TimeInterval.WEEK -> Calendar.WEEK_OF_YEAR
            TimeInterval.YEAR -> Calendar.YEAR
        }
        val instance = Calendar.getInstance()
        instance.set(year,month,dayOfMonth)
        instance.add(field, interval.multiplier)
        return MyDate(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH))
    }
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(multiplier: Int): MultipleTimeInterval {
        return MultipleTimeInterval(multiplier, this)
    }
}

class MultipleTimeInterval(val multiplier:Int, val timeInterval: TimeInterval)

class DateRange(public override val start: MyDate, public override val end: MyDate) : Iterable<MyDate>, Range<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var nextDay: MyDate = start;

            override fun next(): MyDate {
                val oldNextDay = nextDay
                nextDay = nextDay.nextDay();
                return oldNextDay
            }

            override fun hasNext(): Boolean {
                return nextDay <= end
            }

        }
    }

    operator override fun contains(item: MyDate): Boolean {
        return start <= item && item <= end
    }
}
