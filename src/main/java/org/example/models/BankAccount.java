package org.example.models;

/**
 * Clase que representa una cuenta bancaria.
 */
public class BankAccount {
    private String accountNumber;
    private double balance;
    private AccountType accountType;

    /**
     * Constructor de la clase BankAccount.
     *
     * @param accountNumber Número de cuenta.
     * @param accountType Tipo de cuenta (Ahorros o Corriente).
     */
    public BankAccount(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0.0;
    }

    // Getters y Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Método para depositar dinero en la cuenta.
     *
     * @param amount Monto a depositar.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    /**
     * Método para retirar dinero de la cuenta.
     *
     * @param amount Monto a retirar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean withdraw(double amount) {
        if (amount > 0) {
            if (accountType == AccountType.SAVINGS && balance - amount >= 0) {
                balance -= amount;
                return true;
            } else if (accountType == AccountType.CHECKING && balance - amount >= -500) {
                balance -= amount;
                return true;
            }
        }
        return false; // Si no se puede realizar la operación
    }

    @Override
    public String toString() {
        return "Número de Cuenta = " + accountNumber + ", Monto =" + balance + ", Tipo de cuenta =" + accountType;
    }
}