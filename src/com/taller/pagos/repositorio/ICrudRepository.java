package com.taller.pagos.repositorio;

import java.util.List;

/**
 * PILAR - ABSTRACCION: define el CONTRATO de un repositorio CRUD generico
 * sin exponer como se almacenan los datos (lista en memoria, BD, archivo...).
 * Cualquier entidad (Cliente, Cuenta, Factura, Pago) puede tener un
 * repositorio propio que respete esta abstraccion.
 */
public interface ICrudRepository<T, ID> {
    T crear(T entidad);
    T obtenerPorId(ID id);
    List<T> obtenerTodos();
    T actualizar(ID id, T entidad);
    boolean eliminar(ID id);
}
