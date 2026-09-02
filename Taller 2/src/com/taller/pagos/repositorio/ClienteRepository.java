package com.taller.pagos.repositorio;

import com.taller.pagos.modelo.Cliente;

/** CRUD de Cliente. HERENCIA: reutiliza toda la logica de RepositorioEnMemoria. */
public class ClienteRepository extends RepositorioEnMemoria<Cliente, String> {
    @Override
    protected String extraerId(Cliente entidad) {
        return entidad.getId();
    }
}
