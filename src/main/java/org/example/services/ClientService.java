package org.example.services;

import org.example.models.Client;
import org.example.models.BankAccount;
import org.example.models.AccountType;
import org.example.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio que gestiona las operaciones relacionadas con los clientes.
 */
public class ClientService {
    private List<Client> clients = new ArrayList<>(); // Lista de clientes del banco

    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param firstName Nombre del cliente.
     * @param lastName Apellido del cliente.
     * @param dni DNI del cliente.
     * @param email Correo electrónico del cliente.
     * @return El cliente registrado.
     * @throws IllegalArgumentException Si los datos no son válidos.
     */
    public Client registerClient(String firstName, String lastName, String dni, String email) {


        if (!Validator.isValidDni(dni)) {
            throw new IllegalArgumentException("DNI inválido.");
        }
        if (!Validator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }


        Client client = new Client(firstName, lastName, dni, email);
        clients.add(client);
        return client;
    }

    /**
     * Busca un cliente por su DNI.
     *
     * @param dni DNI del cliente a buscar.
     * @return El cliente encontrado o un Optional vacío si no se encuentra.
     */
    public Optional<Client> findClientByDni(String dni) {
        return clients.stream()
                .filter(client -> client.getDni().equals(dni))
                .findFirst();
    }

    /**
     * Crea una cuenta bancaria para un cliente.
     *
     * @param client El cliente que abrirá la cuenta.
     * @param accountType El tipo de cuenta (Ahorros o Corriente).
     * @return La cuenta bancaria creada.
     * @throws IllegalArgumentException Si el cliente no existe o el tipo de cuenta es inválido.
     */
    public BankAccount openBankAccount(Client client, AccountType accountType) {
        if (client == null) {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }

        String accountNumber = generateAccountNumber();
        BankAccount bankAccount = new BankAccount(accountNumber, accountType);
        client.getBankAccounts().add(bankAccount);
        return bankAccount;
    }

    /**
     * Genera un número de cuenta único.
     *
     * @return Un número de cuenta generado.
     */
    private String generateAccountNumber() {
        return "AC" + System.currentTimeMillis();
    }

    // Método para mostrar todos los clientes (solo para pruebas)
    public void listClients() {
        clients.forEach(client -> System.out.println(client));
    }
}