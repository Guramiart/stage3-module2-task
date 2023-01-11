package com.mjc.school.repository.utils;

import com.mjc.school.repository.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public class Utils {

    static Logger LOGGER;

    private Utils() {}

    public static String getRandomData(String filename) {
        String data = "";
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(filename))))) {
            int counter = 0;
            int randomLine = new Random().nextInt(Constants.SOURCE_DATA_LIMIT);
            while ((data = bufferedReader.readLine()) != null && counter != randomLine) {
                counter++;
            }
        } catch (IOException e) {
            LOGGER.warning("Error while getting data from file: " + filename + " " + e.getMessage());
        }
        return data;
    }

    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        LocalDate day = LocalDate.now().minusDays(random.nextInt(Constants.DAY_SHIFT));
        LocalTime time = LocalTime.of(
                random.nextInt(Constants.HOUR_LIMIT),
                random.nextInt(Constants.MINUTE_LIMIT),
                random.nextInt(Constants.SECOND_LIMIT));
        return LocalDateTime.of(day, time);
    }
}
