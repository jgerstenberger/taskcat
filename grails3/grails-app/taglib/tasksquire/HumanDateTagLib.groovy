package tasksquire

import java.time.temporal.ChronoUnit;
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.ZoneId


class CaseCategory {
	static boolean isCase(Closure casePredicate, Object switchParameter) {
		casePredicate.call switchParameter
	}
}

class HumanDateTagLib {
	DateTimeFormatter dt = new DateTimeFormatterBuilder().appendPattern("EEEE").toFormatter()
	
	def java8FormatDate = { attrs, body ->
		out << g.formatDate(date: Date.from(attrs.date.atStartOfDay(ZoneId.systemDefault()).toInstant()), format: attrs.format)
	}
	
	def humanDate = { attrs, body ->
		out << daysToString(ChronoUnit.DAYS.between(LocalDate.now(attrs.timeZone), attrs.date))
	}
	
	def daysToString(long days) {
		use(CaseCategory) {
			switch (days) {
				case {it < -1}: return "${days.abs()} days ago"
				case {it == -1}: return "Yesterday"
				case {it == 0}: return "Today"
				case {it == 1}: return "Tomorrow"
				case {it > 1}: return "${days} days from now"
			}
		}
	}
	
	def humanDayOfWeek = { attrs, body ->
		out << dt.format(LocalDate.now().with(ChronoField.DAY_OF_WEEK, Integer.valueOf(attrs['dayOfWeek'])))
	}
}