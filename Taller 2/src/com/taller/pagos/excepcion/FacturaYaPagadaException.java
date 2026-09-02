package com.taller.pagos.excepcion;

/** Excepcion de negocio: se lanza al intentar pagar una factura que ya esta PAGADA. */
public class FacturaYaPagadaException extends RuntimeException {
    public FacturaYaPagadaException(String mensaje) {
        super(mensaje);
    }
}
