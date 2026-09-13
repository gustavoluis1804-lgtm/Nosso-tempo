package br.com.gustavo.nossotempo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

final class RelationshipTime {
    static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");
    static final ZonedDateTime START = LocalDateTime.of(2026, 9, 12, 16, 43).atZone(ZONE);

    static Parts now() { return between(START, ZonedDateTime.now(ZONE)); }

    static Parts between(ZonedDateTime start, ZonedDateTime end) {
        if (end.isBefore(start)) return new Parts(0, 0, 0, 0, 0, 0);
        Period date = Period.between(start.toLocalDate(), end.toLocalDate());
        ZonedDateTime cursor = start.plusYears(date.getYears()).plusMonths(date.getMonths()).plusDays(date.getDays());
        if (cursor.isAfter(end)) {
            cursor = cursor.minusDays(1);
            date = Period.between(start.toLocalDate(), cursor.toLocalDate());
        }
        Duration clock = Duration.between(cursor, end);
        long seconds = clock.getSeconds();
        return new Parts(date.getYears(), date.getMonths(), date.getDays(),
                (int) (seconds / 3600), (int) ((seconds % 3600) / 60), (int) (seconds % 60));
    }

    static final class Parts {
        final int years, months, days, hours, minutes, seconds;
        Parts(int years, int months, int days, int hours, int minutes, int seconds) {
            this.years = years; this.months = months; this.days = days;
            this.hours = hours; this.minutes = minutes; this.seconds = seconds;
        }
        String mainLine() { return years + " anos  •  " + months + " meses  •  " + days + " dias"; }
        String clockLine() { return two(hours) + " : " + two(minutes) + " : " + two(seconds); }
        private String two(int value) { return String.format(java.util.Locale.US, "%02d", value); }
    }
}
