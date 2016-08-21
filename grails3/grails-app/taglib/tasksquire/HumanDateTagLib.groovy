package tasksquire

import java.time.temporal.ChronoUnit;
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeFormatter;


class CaseCategory {
	static boolean isCase(Closure casePredicate, Object switchParameter) {
		casePredicate.call switchParameter
	}
}

class HumanDateTagLib {
	DateTimeFormatter dt = new DateTimeFormatterBuilder().appendPattern("EEEE").toFormatter()
	
	def humanDate = { attrs, body ->
//		println Days.daysBetween(new LocalDate(), attrs.date);
		out << daysToString(ChronoUnit.DAYS.between(new LocalDate(attrs.timeZone), attrs.date).getDays())
	}
	
	def daysToString(int days) {
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
		out << dt.print(new LocalDate(attrs['timeZone']).withDayOfWeek(Integer.valueOf(attrs['dayOfWeek'])))
	}
}