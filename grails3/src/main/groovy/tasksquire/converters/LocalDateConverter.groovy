package tasksquire.converters
import grails.databinding.converters.ValueConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter implements ValueConverter {

    boolean canConvert(value) {
        value instanceof String
    }

    def convert(value) {
		LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(value));
    }

    Class<?> getTargetType() {
        LocalDate
    }
}