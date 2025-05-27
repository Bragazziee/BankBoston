/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankboston;

/**
 *
 * @author braga
 */
// Paquete principal

import java.util.Scanner;

/**
 * Clase principal que contiene el menú de operaciones para el sistema bancario
 * según las instrucciones del caso Bank Boston.
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Cliente cliente = null;

    public static void main(String[] args) {
        int opcion;

        // Menú de opciones para operar con el cliente y su cuenta corriente
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos de cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> {
                    if (validarCliente()) cliente.verDatos();
                }
                case 3 -> {
                    if (validarCliente()) {
                        System.out.print("Ingrese un monto para depositar: ");
                        int monto = sc.nextInt();
                        cliente.getCuenta().depositar(monto);
                    }
                }
                case 4 -> {
                    if (validarCliente()) {
                        System.out.print("Ingrese un monto para girar: ");
                        int monto = sc.nextInt();
                        cliente.getCuenta().girar(monto);
                    }
                }
                case 5 -> {
                    if (validarCliente()) cliente.getCuenta().consultarSaldo();
                }
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);
    }

    // Verifica si hay un cliente registrado antes de realizar operaciones
    public static boolean validarCliente() {
        if (cliente == null) {
            System.out.println("Primero debe registrar un cliente.");
            return false;
        }
        return true;
    }

    // Registra un nuevo cliente con validaciones específicas del caso
    public static void registrarCliente() {
        System.out.print("Ingrese Rut: ");
        String rut = sc.nextLine();
        if (rut.length() < 11 || rut.length() > 12) {
            System.out.println("Rut invalido. Debe tener entre 11 y 12 caracteres (incluyendo puntos y guion).");
            return;
        }

        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido paterno: ");
        String apPaterno = sc.nextLine();
        System.out.print("Ingrese apellido materno: ");
        String apMaterno = sc.nextLine();
        System.out.print("Ingrese domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Ingrese comuna: ");
        String comuna = sc.nextLine();
        System.out.print("Ingrese telefono: ");
        String telefono = sc.nextLine();
        System.out.print("Ingrese numero de cuenta corriente (9 digitos): ");
        int numeroCuenta = sc.nextInt();

        CuentaCorriente cuenta = new CuentaCorriente(numeroCuenta);
        cliente = new Cliente(rut, nombre, apPaterno, apMaterno, domicilio, comuna, telefono, cuenta);

        System.out.println("\n¡Cliente registrado exitosamente!");
    }
}

/**
 * Clase que representa a un cliente del banco.
 * Incluye todos los atributos necesarios según el caso entregado.
 */
class Cliente {
    private String rut, nombre, apellidoPaterno, apellidoMaterno;
    private String domicilio, comuna, telefono;
    private CuentaCorriente cuenta;

    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
                   String domicilio, String comuna, String telefono, CuentaCorriente cuenta) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cuenta = cuenta;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    // Imprime en pantalla todos los datos personales del cliente
    public void verDatos() {
        System.out.println("\nRut: " + rut);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido paterno: " + apellidoPaterno);
        System.out.println("Apellido materno: " + apellidoMaterno);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Comuna: " + comuna);
        System.out.println("Telefono: " + telefono);
        System.out.println("Numero de cuenta corriente: " + cuenta.getNumero());
        System.out.println("Saldo: " + cuenta.getSaldo() + " pesos");
    }
}

/**
 * Clase que representa una cuenta corriente.
 * Contiene lógica para depósito, giro y consulta de saldo.
 */
class CuentaCorriente {
    private int numero;
    private int saldo;

    public CuentaCorriente(int numero) {
        this.numero = numero;
        this.saldo = 0; // Toda cuenta se inicia en 0
    }

    public int getNumero() {
        return numero;
    }

    public int getSaldo() {
        return saldo;
    }

    // Realiza un depósito si el monto es válido
    public void depositar(int monto) {
        if (monto <= 0) {
            System.out.println("Monto invalido. Debe ser mayor a cero.");
            return;
        }
        saldo += monto;
        System.out.println("\n¡Deposito realizado exitosamente!");
        System.out.println("Saldo actual: " + saldo + " pesos");
    }

    // Realiza un giro si hay saldo suficiente y el monto es válido
    public void girar(int monto) {
        if (monto <= 0) {
            System.out.println("Monto invalido. Debe ser mayor a cero.");
            return;
        }
        if (monto > saldo) {
            System.out.println("Saldo insuficiente para realizar el giro.");
            return;
        }
        saldo -= monto;
        System.out.println("\n¡Giro realizado exitosamente!");
        System.out.println("Saldo actual: " + saldo + " pesos");
    }

    // Muestra el saldo actual de la cuenta
    public void consultarSaldo() {
        System.out.println("\nSaldo actual: " + saldo + " pesos");
    }
}
