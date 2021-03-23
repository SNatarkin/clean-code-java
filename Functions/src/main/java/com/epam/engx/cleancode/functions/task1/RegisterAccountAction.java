package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private static final int LENGTH_NAME = 5;
    private static final int PASSWORD_LENGTH = 8;
    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        if (verificationPassword(account) && verificationName(account)) {
            createAccount(account);
            accountManager.createNewAccount(account);
        }
    }

    private void createAccount(Account account) {
        account.setCreatedDate(new Date());
        account.setAddresses(createAddress(account));
    }

    private List<Address> createAddress(Account account) {
        return Arrays.asList(account.getHomeAddress(), account.getWorkAddress(), account.getAdditionalAddress());
    }

    private boolean verificationPassword(Account account) {
        boolean isCorrect;
        String password = account.getPassword();
        if (password.length() <= PASSWORD_LENGTH) {
            throw new TooShortPasswordException();
        }
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        } else {
            isCorrect = true;
        }
        return isCorrect;
    }

    private boolean verificationName(Account account) {
        boolean isCorrect;
        if (account.getName().length() <= LENGTH_NAME) {
            throw new WrongAccountNameException();
        } else {
            isCorrect = true;
        }
        return isCorrect;
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

}
