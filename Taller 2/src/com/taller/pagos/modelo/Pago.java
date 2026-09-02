package com.taller.pagos.modelo;

import java.time.LocalDateTime;

/** Entidad Pago: registra el pago de una Factura desde una Cuenta. */
public class Pago {
    private final String id;
    private final Factura factura;
    private final Cuenta cuenta;
    private final double monto;
    private final LocalDateTime fecha;
    private EstadoPago estado;

    public Pago(String id, Factura factura, Cuenta cuenta, double monto) {
        this.id = id;
        this.factura = factura;
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.estado = EstadoPago.CONFIRMADO;
    }

    public String getId() { return id; }
    public Factura getFactura() { return factura; }
    public Cuenta getCuenta() { return cuenta; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public EstadoPago getEstado() { return estado; }

    public void confirmar() { this.estado = EstadoPago.CONFIRMADO; }
    public void anular() { this.estado = EstadoPago.ANULADO; }

    @Override
    public String toString() {
        return "Pago{id='%s', factura='%s', cuenta='%s', monto=%.2f, estado=%s}"
            .formatted(id, factura.getId(), cuenta.getNumeroCuenta(), monto, estado);
    }
}
