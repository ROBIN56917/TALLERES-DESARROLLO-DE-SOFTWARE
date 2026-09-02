package com.taller.pagos.repositorio;

import com.taller.pagos.modelo.Cuenta;

/**
 * CRUD de Cuenta. POLIMORFISMO: el repositorio trabaja con la referencia
 * abstracta Cuenta, por lo que puede almacenar indistintamente objetos
 * CuentaAhorros o CuentaCorriente sin conocer su tipo concreto.
 */
public class CuentaRepository extends RepositorioEnMemoria<Cuenta, String> {
    @Override
    protected String extraerId(Cuenta entidad) {
        return entidad.getNumeroCuenta();
    }
}
