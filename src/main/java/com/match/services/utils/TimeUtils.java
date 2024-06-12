package com.match.services.utils;

import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TimeUtils {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String convertToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        // Преобразуем Timestamp в Date
        Date date = Date.from(timestamp.toDate().toInstant());
        return dateFormat.format(date);
    }

    public static Timestamp convertToTimestamp(String date) {
        if (date == null) {
            return null;
        }

        try {
            Date parseDate = dateFormat.parse(date);
            return Timestamp.of(parseDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer calculateAge(Timestamp dateOfBirth) {
        if (dateOfBirth == null) {
            return null;
        }

        return Period.between(dateOfBirth.toDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now())
                .getYears();
    }

}
