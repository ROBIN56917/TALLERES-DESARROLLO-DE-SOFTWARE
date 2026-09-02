package com.taller.pagos.modelo;

import com.taller.pagos.excepcion.SaldoInsuficienteException;
import java.time.LocalDate;

/**
 * PILAR - ABSTRACCION: Cuenta modela el concepto general de "cuenta bancaria"
 * y declara el comportamiento (retirar, describirCuenta) sin definir COMO
 * se hace; cada subclase decide su propia implementacion. No se puede
 * instanciar directamente (es abstracta), solo se conocen sus operaciones.
 *
 * PILAR - ENCAPSULAMIENTO: el estado (saldo, numeroCuenta...) es protegido/
 * privado y solo se modifica mediante metodos que validan reglas de negocio.
 */
public abstract class Cuenta {
    protected final String numeroCuenta;
    protected double saldo;
    protected final Cliente cliente;
    protected final LocalDate fechaApertura;

    protected Cuenta(String numeroCuenta, double saldoInicial, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.saldo = saldoInicial;
        this.cliente = cliente;
        this.fechaApertura = LocalDate.now();
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getFechaApertura() { return fechaApertura; }

    /** Consulta el saldo actual. Usado por obtenerSaldoCuenta(). */
    public double consultarSaldo() { return saldo; }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a 0");
        }
        this.saldo += monto;
    }

    /**
     * Metodo abstracto: cada tipo de cuenta define su propia regla para
     * permitir o no un retiro (POLIMORFISMO en tiempo de ejecucion cuando
     * se invoca sobre una referencia de tipo Cuenta).
     */
    public abstract boolean retirar(double monto) throws SaldoInsuficienteException;

    /** Metodo abstracto que cada subclase sobrescribe (POLIMORFISMO). */
    public abstract String describirCuenta();
}
