package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private PasswordChecker passwordChecker;
    private AccountManager accountManager;
    private static final int LENGTH_NAME =  5;
    private static final int PASSWORD_LENGTH = 8;

    public void register(Account account) {
        verificationPassword(account);
        verificationName(account);
        account.setCreatedDate(new Date());
        List<Address> addresses = Arrays.asList(account.getHomeAddress(), account.getWorkAddress(), account.getAdditionalAddress());
        account.setAddresses(addresses);
        accountManager.createNewAccount(account);
    }

    private void verificationPassword(Account account) {
        String password = account.getPassword();
        if (password.length() <= PASSWORD_LENGTH) {
            throw new TooShortPasswordException();
        }
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private void verificationName(Account account) {
        if (account.getName().length() <= LENGTH_NAME) {
            throw new WrongAccountNameException();
        }
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

}
