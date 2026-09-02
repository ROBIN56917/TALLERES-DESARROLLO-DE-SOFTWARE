package com.taller.pagos.repositorio;

import com.taller.pagos.modelo.Pago;

/** CRUD de Pago. */
public class PagoRepository extends RepositorioEnMemoria<Pago, String> {
    @Override
    protected String extraerId(Pago entidad) {
        return entidad.getId();
    }
}
