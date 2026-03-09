package pet.dolphin.auth.mappers

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun ZonedDateTime.toStringFormat(): String {
    return this.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
}