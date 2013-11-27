package taskcat

import org.joda.time.Days;
import org.joda.time.LocalDate


class CaseCategory {
	static boolean isCase(Closure casePredicate, Object switchParameter) {
		casePredicate.call switchParameter
	}
}

class HumanDateTagLib {
	def humanDate = { attrs, body ->
//		println Days.daysBetween(new LocalDate(), attrs.date);
		out << daysToString(Days.daysBetween(new LocalDate(), attrs.date).getDays())
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
}