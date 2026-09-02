package com.taller.pagos.excepcion;

/** Excepcion de negocio: se lanza cuando una cuenta no tiene fondos/cupo suficiente. */
public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
