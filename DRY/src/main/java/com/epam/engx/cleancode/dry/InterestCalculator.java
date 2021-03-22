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
        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        return (AGE <= accountDetails.getAge()) ? getInterest(accountDetails, SENIOR_PERCENT) : getInterest(accountDetails, INTEREST_PERCENT);
    }
    
    private BigDecimal getInterest(AccountDetails accountDetails, double interestPercent) {
        return BigDecimal.valueOf(accountDetails.getBalance().doubleValue()
                * durationSinceStartDateInYears(accountDetails.getStartDate()) * interestPercent / 100);
    }

    private int durationSinceStartDateInYears(Date startDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date());

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;

    }
}
