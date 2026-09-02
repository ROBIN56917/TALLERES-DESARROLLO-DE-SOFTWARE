package com.taller.pagos.servicio;

import com.taller.pagos.modelo.Cuenta;
import com.taller.pagos.modelo.Factura;
import com.taller.pagos.modelo.Pago;

/** PILAR - ABSTRACCION: contrato de negocio para procesar un pago de factura. */
public interface Pagable {
    Pago procesarPago(Factura factura, Cuenta cuenta, double monto);
}
