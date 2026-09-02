package com.taller.pagos.repositorio;

import com.taller.pagos.excepcion.EntidadNoEncontradaException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PILAR - ABSTRACCION + ENCAPSULAMIENTO: implementa el CRUD generico una
 * sola vez; el mapa interno (almacenamiento) es privado y no se expone.
 * Cada repositorio concreto (ClienteRepository, CuentaRepository, ...)
 * HEREDA esta clase y solo indica como extraer el id de su entidad
 * (PILAR - HERENCIA: reutilizacion de codigo entre repositorios).
 */
public abstract class RepositorioEnMemoria<T, ID> implements ICrudRepository<T, ID> {
    private final Map<ID, T> almacen = new LinkedHashMap<>();

    /** Cada subclase indica como obtener el id de su entidad concreta. */
    protected abstract ID extraerId(T entidad);

    @Override
    public T crear(T entidad) {
        ID id = extraerId(entidad);
        almacen.put(id, entidad);
        return entidad;
    }

    @Override
    public T obtenerPorId(ID id) {
        T entidad = almacen.get(id);
        if (entidad == null) {
            throw new EntidadNoEncontradaException("No existe la entidad con id: " + id);
        }
        return entidad;
    }

    @Override
    public List<T> obtenerTodos() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public T actualizar(ID id, T entidad) {
        if (!almacen.containsKey(id)) {
            throw new EntidadNoEncontradaException("No existe la entidad con id: " + id);
        }
        almacen.put(id, entidad);
        return entidad;
    }

    @Override
    public boolean eliminar(ID id) {
        return almacen.remove(id) != null;
    }
}
