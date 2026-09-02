package com.taller.pagos.modelo;

import java.util.Objects;

/**
 * Entidad Cliente.
 * PILAR - ENCAPSULAMIENTO: todos los atributos son privados y solo se
 * accede/modifica a traves de getters y setters, que ademas validan los datos.
 */
public class Cliente {
    private final String id;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;

    public Cliente(String id, String nombre, String documento, String email, String telefono) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        setNombre(nombre);
        this.documento = Objects.requireNonNull(documento, "El documento no puede ser nulo");
        setEmail(email);
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalido: " + email);
        }
        this.email = email;
    }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cliente{id='%s', nombre='%s', documento='%s'}".formatted(id, nombre, documento);
    }
}
