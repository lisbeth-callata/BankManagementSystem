package org.example.services;

import org.example.models.AccountType;
import org.example.models.BankAccount;
import org.example.models.Client;


import java.util.Optional;

/**
 * Clase de servicio que gestiona las operaciones relacionadas con las cuentas bancarias.
 */
public class BankAccountService {

    /**
     * Realiza un depósito en una cuenta bancaria.
     *
     * @param bankAccount La cuenta bancaria donde se realizará el depósito.
     * @param amount Monto a depositar.
     * @return true si el depósito fue exitoso, false en caso contrario.
     */
    public boolean deposit(BankAccount bankAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que 0.");
        }
        bankAccount.deposit(amount);
        return true;
    }

    /**
     * Realiza un retiro de dinero de una cuenta bancaria.
     *
     * @param bankAccount La cuenta bancaria de la cual se realizará el retiro.
     * @param amount Monto a retirar.
     * @return true si el retiro fue exitoso, false en caso contrario.
     */
    public boolean withdraw(BankAccount bankAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que 0.");
        }
        return bankAccount.withdraw(amount);
    }

    /**
     * Consulta el saldo de una cuenta bancaria.
     *
     * @param bankAccount La cuenta bancaria de la cual se consultará el saldo.
     * @return El saldo actual de la cuenta bancaria.
     */
    public double checkBalance(BankAccount bankAccount) {
        return bankAccount.getBalance();
    }

    /**
     * Busca una cuenta bancaria por su número de cuenta.
     *
     * @param client El cliente que posee la cuenta.
     * @param accountNumber El número de cuenta que se busca.
     * @return La cuenta bancaria encontrada o un Optional vacío si no se encuentra.
     */
    public Optional<BankAccount> findAccountByNumber(Client client, String accountNumber) {
        return client.getBankAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }
}