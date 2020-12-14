package com.epam.user.dto.validation;

import com.epam.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RefreshScope
public class AgeIsValid {

    @Value("${age}")
    private long age;

    public boolean valid(User user) {
        LocalDate dateOfBirth = user.getDateOfBirth();
        LocalDate today = LocalDate.now();
        long years = ChronoUnit.YEARS.between(dateOfBirth, today);
        return years > age;
    }
}
