package org.example;

import org.example.models.BankAccount;
import org.example.models.Client;
import org.example.models.AccountType;
import org.example.services.BankAccountService;
import org.example.services.ClientService;
import org.example.util.Validator;

import java.util.Scanner;
import java.util.Optional;

public class Main {

    private static ClientService clientService = new ClientService();
    private static BankAccountService bankAccountService = new BankAccountService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea de nueva línea

            switch (option) {
                case 1 -> registerClient();
                case 2 -> openBankAccount();
                case 3 -> depositMoney();
                case 4 -> withdrawMoney();
                case 5 -> checkBalance();
                case 6 -> exitSystem();
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Menú ===");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Abrir Cuenta Bancaria");
        System.out.println("3. Depositar Dinero");
        System.out.println("4. Retirar Dinero");
        System.out.println("5. Consultar Saldo");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registerClient() {
        String dni = promptForUniqueDni();
        String firstName = promptForNonEmptyInput("Ingrese el nombre del cliente: ");
        String lastName = promptForNonEmptyInput("Ingrese el apellido del cliente: ");
        String email = promptForValidEmail();

        Client client = clientService.registerClient(firstName, lastName, dni, email);
        System.out.println("Cliente registrado: " + client);
    }

    private static String promptForUniqueDni() {
        while (true) {
            System.out.print("Ingrese el DNI del cliente: ");
            String dni = scanner.nextLine();
            if (clientService.findClientByDni(dni).isEmpty()) {
                return dni;
            }
            System.out.println("El DNI ya está registrado. Intente con otro.");
        }
    }

    private static String promptForNonEmptyInput(String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static String promptForValidEmail() {
        String email;
        do {
            System.out.print("Ingrese el correo electrónico del cliente: ");
            email = scanner.nextLine();
            if (!Validator.isValidEmail(email)) {
                System.out.println("Email inválido. Por favor, intente nuevamente.");
            }
        } while (!Validator.isValidEmail(email));
        return email;
    }

    private static void openBankAccount() {
        System.out.print("Ingrese el DNI del cliente para abrir la cuenta: ");
        String clientDni = scanner.nextLine();
        Optional<Client> existingClient = clientService.findClientByDni(clientDni);

        if (existingClient.isPresent()) {
            System.out.println("Cliente encontrado: " + existingClient.get());
            System.out.print("Seleccione el tipo de cuenta (1: Ahorros, 2: Corriente): ");
            int accountTypeOption = scanner.nextInt();
            scanner.nextLine();
            AccountType accountType = (accountTypeOption == 1) ? AccountType.SAVINGS : AccountType.CHECKING;
            BankAccount account = clientService.openBankAccount(existingClient.get(), accountType);
            System.out.println("Cuenta abierta: " + account);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void depositMoney() {
        System.out.print("Ingrese el DNI del cliente para realizar el depósito: ");
        String clientDni = scanner.nextLine();
        Optional<Client> existingClient = clientService.findClientByDni(clientDni);

        if (existingClient.isPresent()) {
            System.out.print("Ingrese el número de cuenta para realizar el depósito: ");
            String accountNumber = scanner.nextLine();
            Optional<BankAccount> bankAccount = bankAccountService.findAccountByNumber(existingClient.get(), accountNumber);

            if (bankAccount.isPresent()) {
                System.out.print("Ingrese la cantidad a depositar: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consumir la línea de nueva línea
                bankAccountService.deposit(bankAccount.get(), amount);
                System.out.println("Depósito realizado. Saldo actual: " + bankAccountService.checkBalance(bankAccount.get()));
            } else {
                System.out.println("Cuenta bancaria no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Ingrese el DNI del cliente para realizar el retiro: ");
        String clientDni = scanner.nextLine();
        Optional<Client> existingClient = clientService.findClientByDni(clientDni);

        if (existingClient.isPresent()) {
            System.out.print("Ingrese el número de cuenta para realizar el retiro: ");
            String accountToWithdraw = scanner.nextLine();
            Optional<BankAccount> withdrawAccount = bankAccountService.findAccountByNumber(existingClient.get(), accountToWithdraw);

            if (withdrawAccount.isPresent()) {
                System.out.print("Ingrese la cantidad a retirar: ");
                double withdrawalAmount = scanner.nextDouble();
                scanner.nextLine(); // Consumir la línea de nueva línea
                boolean withdrawalSuccess = bankAccountService.withdraw(withdrawAccount.get(), withdrawalAmount);
                if (withdrawalSuccess) {
                    System.out.println("Retiro realizado. Saldo actual: " + bankAccountService.checkBalance(withdrawAccount.get()));
                } else {
                    System.out.println("No se pudo realizar el retiro.");
                }
            } else {
                System.out.println("Cuenta bancaria no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void checkBalance() {
        System.out.print("Ingrese el DNI del cliente para consultar el saldo: ");
        String clientDni = scanner.nextLine();
        Optional<Client> existingClient = clientService.findClientByDni(clientDni);

        if (existingClient.isPresent()) {
            System.out.print("Ingrese el número de cuenta para consultar el saldo: ");
            String accountToCheck = scanner.nextLine();
            Optional<BankAccount> accountToCheckOpt = bankAccountService.findAccountByNumber(existingClient.get(), accountToCheck);

            if (accountToCheckOpt.isPresent()) {
                System.out.println("Saldo actual: " + bankAccountService.checkBalance(accountToCheckOpt.get()));
            } else {
                System.out.println("Cuenta bancaria no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void exitSystem() {
        System.out.println("Saliendo del sistema...");
        scanner.close();
        System.exit(0);
    }
}