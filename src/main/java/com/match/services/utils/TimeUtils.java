package com.match.services.utils;

import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeUtils {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String convertToString(Timestamp timestamp) {
        return timestamp != null ? dateFormat.format(timestamp) : null;
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

}
