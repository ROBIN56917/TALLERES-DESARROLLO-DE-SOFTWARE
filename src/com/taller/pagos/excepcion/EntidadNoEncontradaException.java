package com.taller.pagos.excepcion;

/** Excepcion de negocio: se lanza cuando un ID buscado no existe en el repositorio. */
public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
