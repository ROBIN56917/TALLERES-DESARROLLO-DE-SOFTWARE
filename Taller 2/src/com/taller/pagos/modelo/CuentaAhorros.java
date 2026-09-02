package com.taller.pagos.modelo;

import com.taller.pagos.excepcion.SaldoInsuficienteException;

/**
 * PILAR - HERENCIA: CuentaAhorros extiende Cuenta y reutiliza sus atributos
 * y metodos comunes (consultarSaldo, depositar, numeroCuenta...).
 * PILAR - POLIMORFISMO: sobrescribe retirar() y describirCuenta() con una
 * regla propia (exige mantener un saldo minimo).
 */
public class CuentaAhorros extends Cuenta {
    private final double tasaInteres;
    private final double saldoMinimo;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, Cliente cliente,
                          double tasaInteres, double saldoMinimo) {
        super(numeroCuenta, saldoInicial, cliente);
        this.tasaInteres = tasaInteres;
        this.saldoMinimo = saldoMinimo;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a 0");
        }
        if (saldo - monto < saldoMinimo) {
            throw new SaldoInsuficienteException(
                "Cuenta de ahorros %s: el retiro dejaria el saldo por debajo del minimo (%.2f)"
                    .formatted(numeroCuenta, saldoMinimo));
        }
        saldo -= monto;
        return true;
    }

    public double calcularInteres() { return saldo * tasaInteres; }

    @Override
    public String describirCuenta() {
        return "Cuenta de Ahorros #%s - saldo: %.2f (tasa interes: %.2f%%)"
            .formatted(numeroCuenta, saldo, tasaInteres * 100);
    }
}
