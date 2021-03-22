package com.epam.engx.cleancode.dry;

import com.epam.engx.cleancode.dry.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);
        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return getDifferentBetweenYearsWithCorrection(startCalendar, endCalendar, diffYear);
    }

    private int getDifferentBetweenYearsWithCorrection(Calendar startCalendar, Calendar endCalendar, int diffYear) {
        return getDifferenceInYear(startCalendar, endCalendar) ? diffYear - 1 : diffYear;
    }

    private boolean getDifferenceInYear(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        return (AGE <= accountDetails.getAge()) ? getInterest(accountDetails, SENIOR_PERCENT) : getInterest(accountDetails, INTEREST_PERCENT);
    }

    private BigDecimal getInterest(AccountDetails accountDetails, double interestPercent) {
        return BigDecimal.valueOf(accountDetails.getBalance().doubleValue()
                * durationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * interestPercent / 100);
    }
}

