package com.epam.engx.cleancode.dry;

import com.epam.engx.cleancode.dry.thirdpartyjar.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AccountDetails implements Account {
    private Date birth;

    private BigDecimal balance;
    private Date startDate;

    @Override
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getAge() {
        return Period.between(getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();    // should depend on current time
    }
}
