package com.taller.pagos.modelo;

import com.taller.pagos.excepcion.SaldoInsuficienteException;

/**
 * PILAR - HERENCIA: reutiliza el comportamiento comun de Cuenta.
 * PILAR - POLIMORFISMO: retirar() permite sobregiro hasta un cupo, una regla
 * distinta a la de CuentaAhorros aunque comparten la misma firma de metodo.
 */
public class CuentaCorriente extends Cuenta {
    private final double cupoSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, Cliente cliente,
                            double cupoSobregiro) {
        super(numeroCuenta, saldoInicial, cliente);
        this.cupoSobregiro = cupoSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a 0");
        }
        if (saldo - monto < -cupoSobregiro) {
            throw new SaldoInsuficienteException(
                "Cuenta corriente %s: el retiro supera el cupo de sobregiro (%.2f)"
                    .formatted(numeroCuenta, cupoSobregiro));
        }
        saldo -= monto;
        return true;
    }

    @Override
    public String describirCuenta() {
        return "Cuenta Corriente #%s - saldo: %.2f (cupo sobregiro: %.2f)"
            .formatted(numeroCuenta, saldo, cupoSobregiro);
    }
}
