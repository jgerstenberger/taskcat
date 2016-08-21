package tasksquire

import java.time.temporal.ChronoUnit;
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;


class CaseCategory {
	static boolean isCase(Closure casePredicate, Object switchParameter) {
		casePredicate.call switchParameter
	}
}

class HumanDateTagLib {
	DateTimeFormatter dt = new DateTimeFormatterBuilder().appendPattern("EEEE").toFormatter()
	
	def humanDate = { attrs, body ->
//		println Days.daysBetween(new LocalDate(), attrs.date);
		out << daysToString(ChronoUnit.DAYS.between(LocalDate.now(attrs.timeZone).atStartOfDay(), attrs.date).getDays())
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
		out << dt.print(LocalDate.now().with(ChronoField.DAY_OF_WEEK, Integer.valueOf(attrs['dayOfWeek'])))
	}
}