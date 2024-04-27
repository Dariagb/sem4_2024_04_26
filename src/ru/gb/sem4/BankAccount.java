package ru.gb.sem4;

public class BankAccount extends Client {
    public int initialAccountBalance;
    int depositAmount;
    int withdrawalAmount;

    public BankAccount(String name, int age, int initialAccountBalance) {
        super(name, age);
        this.initialAccountBalance = initialAccountBalance;
    }

    public int getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public int getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setInitialAccountBalance(int initialBalance) {
    }
}
