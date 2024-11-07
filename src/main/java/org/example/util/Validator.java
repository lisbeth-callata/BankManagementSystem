package org.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase que proporciona métodos de validación para el sistema bancario.
 */
public class Validator {

    /**
     * Valida si el DNI es único y tiene un formato correcto.
     * Este ejemplo solo valida que no esté vacío. En un caso real,
     * se debería verificar que el DNI no esté duplicado en la base de datos.
     *
     * @param dni El DNI a validar.
     * @return true si el DNI es válido, false en caso contrario.
     */
    public static boolean isValidDni(String dni) {
        return dni != null && !dni.isEmpty(); // Validación simple, en un caso real debería verificar duplicados.
    }

    /**
     * Valida si el correo electrónico tiene un formato válido.
     *
     * @param email El correo electrónico a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}