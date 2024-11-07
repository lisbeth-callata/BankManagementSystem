# **Bank Management System**

---

## Descripción

Este es un **sistema bancario** de gestión de clientes y cuentas, desarrollado en **Java**, que permite registrar clientes, abrir cuentas bancarias, realizar depósitos y retiros, y consultar el saldo de las cuentas. 
El sistema cumple con distintas reglas de negocio como la validación del DNI único, la verificación del formato del correo electrónico, y la gestión de cuentas corrientes y de ahorro con reglas específicas de sobregiro y saldo.

---

## **Tecnologías Usadas**

- **Java 17**
- **POO (Programación Orientada a Objetos)**
- **UML** (Diagramas de clases)
- **Maven** (para la gestión de dependencias)
- **Git y GitHub** (para control de versiones)

---

## **Funcionalidades**

### 👨‍💼 Cliente

- Registro de un cliente con nombre, apellido, DNI y email.
- El DNI es único para cada cliente.
- El email debe tener un formato válido.
- Un cliente puede tener varias cuentas bancarias.

### 💳 Cuenta Bancaria

- El sistema permite abrir cuentas de tipo Ahorros o Corriente.
- Se valida que el número de cuenta sea único.
- Se implementan reglas de negocio:
  - Las cuentas de ahorro no pueden tener saldo negativo.
  - Las cuentas corrientes pueden tener un saldo negativo hasta un límite de sobregiro de -500.

---

## **Diagrama de Clases UML**

A continuación se muestra una imagen del diagrama UML:

![Diagrama de Clases](https://github.com/user-attachments/assets/a266a896-0594-4035-a15d-05af63f50059)  

---

## **Requerimientos**

- **Java 8 o superior**
- **MySQL** para la persistencia de datos
- **Maven** para gestionar las dependencias

---

## **Autor**

- [Lisbeth Callata](https://github.com/lisbeth-callata)
