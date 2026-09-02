package com.taller.pagos.modelo;

import java.time.LocalDate;

/** Entidad Factura. ENCAPSULAMIENTO: estado interno controlado por metodos. */
public class Factura {
    private final String id;
    private final Cliente cliente;
    private final String tipoServicio;
    private final double monto;
    private final LocalDate fechaEmision;
    private final LocalDate fechaVencimiento;
    private EstadoFactura estado;

    public Factura(String id, Cliente cliente, String tipoServicio, double monto,
                    LocalDate fechaEmision, LocalDate fechaVencimiento) {
        this.id = id;
        this.cliente = cliente;
        this.tipoServicio = tipoServicio;
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la factura debe ser mayor a 0");
        }
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = EstadoFactura.PENDIENTE;
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public String getTipoServicio() { return tipoServicio; }
    public double getMonto() { return monto; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public EstadoFactura getEstado() { return estado; }

    public void marcarComoPagada() { this.estado = EstadoFactura.PAGADA; }

    public boolean estaVencida() {
        return estado == EstadoFactura.PENDIENTE && LocalDate.now().isAfter(fechaVencimiento);
    }

    @Override
    public String toString() {
        return "Factura{id='%s', servicio='%s', monto=%.2f, estado=%s}"
            .formatted(id, tipoServicio, monto, estado);
    }
}
