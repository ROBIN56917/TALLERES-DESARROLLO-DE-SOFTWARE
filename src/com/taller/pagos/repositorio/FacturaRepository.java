package com.taller.pagos.repositorio;

import com.taller.pagos.modelo.Factura;

/** CRUD de Factura. */
public class FacturaRepository extends RepositorioEnMemoria<Factura, String> {
    @Override
    protected String extraerId(Factura entidad) {
        return entidad.getId();
    }
}
