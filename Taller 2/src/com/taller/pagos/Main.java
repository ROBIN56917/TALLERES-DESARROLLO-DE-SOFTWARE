package com.taller.pagos;

import com.taller.pagos.excepcion.EntidadNoEncontradaException;
import com.taller.pagos.excepcion.FacturaYaPagadaException;
import com.taller.pagos.excepcion.SaldoInsuficienteException;
import com.taller.pagos.modelo.*;
import com.taller.pagos.repositorio.*;
import com.taller.pagos.servicio.PagoService;

import java.time.LocalDate;
import java.util.List;

/**
 * Programa de demostracion: crea clientes, cuentas (una de ahorros y una
 * corriente -> demuestra HERENCIA/POLIMORFISMO), factura y ejecuta las 4
 * funcionalidades del taller, incluyendo los flujos alternos/excepciones.
 */
public class Main {
    public static void main(String[] args) {
        // --- Repositorios (CRUD por entidad) ---
        ClienteRepository clienteRepo = new ClienteRepository();
        CuentaRepository cuentaRepo = new CuentaRepository();
        FacturaRepository facturaRepo = new FacturaRepository();
        PagoRepository pagoRepo = new PagoRepository();
        PagoService pagoService = new PagoService(cuentaRepo, facturaRepo, pagoRepo);

        // --- CRUD Cliente: crear ---
        Cliente cliente = new Cliente("C1", "Ana Torres", "1017123456", "ana@mail.com", "3001234567");
        clienteRepo.crear(cliente);

        // --- CRUD Cuenta: crear una de cada subtipo (POLIMORFISMO) ---
        Cuenta ahorros = new CuentaAhorros("AH-001", 200_000, cliente, 0.02, 50_000);
        Cuenta corriente = new CuentaCorriente("CC-001", 0, cliente, 100_000);
        cuentaRepo.crear(ahorros);
        cuentaRepo.crear(corriente);

        // Misma llamada, comportamiento distinto segun el tipo real -> POLIMORFISMO
        System.out.println(ahorros.describirCuenta());
        System.out.println(corriente.describirCuenta());

        // --- CRUD Factura: crear ---
        Factura facturaAgua = new Factura("F1", cliente, "Acueducto", 80_000,
            LocalDate.now().minusDays(5), LocalDate.now().plusDays(10));
        facturaRepo.crear(facturaAgua);

        // ===== Flujo principal: procesarPago =====
        Pago pago = pagoService.procesarPago(facturaAgua, ahorros, 80_000);
        System.out.println("Pago registrado: " + pago);

        // ===== obtenerSaldoCuenta =====
        System.out.printf("Saldo actual cuenta AH-001: %.2f%n",
            pagoService.obtenerSaldoCuenta("AH-001"));

        // ===== obtenerFacturasPorCliente =====
        List<Factura> facturas = pagoService.obtenerFacturasPorCliente("C1");
        System.out.println("Facturas del cliente C1: " + facturas);

        // ===== obtenerPagosPorCliente =====
        List<Pago> pagos = pagoService.obtenerPagosPorCliente("C1");
        System.out.println("Pagos del cliente C1: " + pagos);

        // ===== Excepcion 1: factura ya pagada =====
        try {
            pagoService.procesarPago(facturaAgua, ahorros, 80_000);
        } catch (FacturaYaPagadaException e) {
            System.out.println("[Excepcion esperada] " + e.getMessage());
        }

        // ===== Excepcion 2: saldo insuficiente (cuenta de ahorros no puede
        // bajar del saldo minimo) =====
        Factura facturaLuz = new Factura("F2", cliente, "Energia", 500_000,
            LocalDate.now(), LocalDate.now().plusDays(15));
        facturaRepo.crear(facturaLuz);
        try {
            pagoService.procesarPago(facturaLuz, ahorros, 500_000);
        } catch (SaldoInsuficienteException e) {
            System.out.println("[Excepcion esperada] " + e.getMessage());
        }

        // ===== Excepcion 3: cuenta inexistente =====
        try {
            pagoService.obtenerSaldoCuenta("NO-EXISTE");
        } catch (EntidadNoEncontradaException e) {
            System.out.println("[Excepcion esperada] " + e.getMessage());
        }

        // ===== Flujo alterno: pagar con cuenta corriente usando sobregiro =====
        Factura facturaGas = new Factura("F3", cliente, "Gas", 50_000,
            LocalDate.now(), LocalDate.now().plusDays(20));
        facturaRepo.crear(facturaGas);
        Pago pagoConSobregiro = pagoService.procesarPago(facturaGas, corriente, 50_000);
        System.out.println("Pago con sobregiro: " + pagoConSobregiro);
        System.out.println(corriente.describirCuenta());
    }
}
