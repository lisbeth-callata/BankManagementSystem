package org.example.models;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un cliente del banco.
 */
public class Client {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private List<BankAccount> bankAccounts;
    /**
     * Constructor de la clase Cliente. Inicializa la lista de cuentas bancarias.
     *
     * @param firstName El nombre del cliente.
     * @param lastName El apellido del cliente.
     * @param dni El DNI del cliente.
     * @param email El correo electrónico del cliente.
     */
    public Client(String firstName, String lastName, String dni, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.bankAccounts = new ArrayList<>(); // Inicialización de la lista
    }

    // Getters y Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (DNI: " + dni + ")";
    }
}