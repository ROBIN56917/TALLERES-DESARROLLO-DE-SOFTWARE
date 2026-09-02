package com.taller.pagos.servicio;

import com.taller.pagos.excepcion.EntidadNoEncontradaException;
import com.taller.pagos.excepcion.FacturaYaPagadaException;
import com.taller.pagos.excepcion.SaldoInsuficienteException;
import com.taller.pagos.modelo.Cuenta;
import com.taller.pagos.modelo.EstadoFactura;
import com.taller.pagos.modelo.Factura;
import com.taller.pagos.modelo.Pago;
import com.taller.pagos.repositorio.CuentaRepository;
import com.taller.pagos.repositorio.FacturaRepository;
import com.taller.pagos.repositorio.PagoRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servicio de aplicacion que implementa las 4 funcionalidades del taller:
 * procesarPago, obtenerSaldoCuenta, obtenerPagosPorCliente y
 * obtenerFacturasPorCliente. Implementa Pagable (PILAR - ABSTRACCION /
 * POLIMORFISMO: se programa contra la interfaz, no contra la clase concreta).
 */
public class PagoService implements Pagable {
    private final CuentaRepository cuentaRepository;
    private final FacturaRepository facturaRepository;
    private final PagoRepository pagoRepository;

    public PagoService(CuentaRepository cuentaRepository,
                        FacturaRepository facturaRepository,
                        PagoRepository pagoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.facturaRepository = facturaRepository;
        this.pagoRepository = pagoRepository;
    }

    /** UC-01 Procesar Pago (flujo principal + validaciones de alternos/excepciones). */
    @Override
    public Pago procesarPago(Factura factura, Cuenta cuenta, double monto) {
        if (factura == null || cuenta == null) {
            throw new EntidadNoEncontradaException("Factura o cuenta no encontrada");
        }
        if (factura.getEstado() == EstadoFactura.PAGADA) {
            throw new FacturaYaPagadaException(
                "La factura " + factura.getId() + " ya se encuentra pagada");
        }
        if (Math.abs(monto - factura.getMonto()) > 0.001) {
            throw new IllegalArgumentException(
                "El monto del pago (%.2f) no coincide con el valor de la factura (%.2f)"
                    .formatted(monto, factura.getMonto()));
        }

        // POLIMORFISMO: retirar() ejecuta la regla propia de CuentaAhorros o
        // CuentaCorriente segun el tipo real del objeto 'cuenta' en tiempo de ejecucion.
        boolean retiroExitoso = cuenta.retirar(monto); // puede lanzar SaldoInsuficienteException
        if (!retiroExitoso) {
            throw new SaldoInsuficienteException("No fue posible debitar la cuenta");
        }

        factura.marcarComoPagada();
        Pago pago = new Pago(UUID.randomUUID().toString(), factura, cuenta, monto);
        pagoRepository.crear(pago);
        return pago;
    }

    /** UC-02 Obtener Saldo de Cuenta. */
    public double obtenerSaldoCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.obtenerPorId(numeroCuenta);
        return cuenta.consultarSaldo();
    }

    /** UC-03 Obtener Pagos por Cliente. */
    public List<Pago> obtenerPagosPorCliente(String clienteId) {
        return pagoRepository.obtenerTodos().stream()
            .filter(p -> p.getFactura().getCliente().getId().equals(clienteId))
            .collect(Collectors.toList());
    }

    /** UC-04 Obtener Facturas por Cliente. */
    public List<Factura> obtenerFacturasPorCliente(String clienteId) {
        return facturaRepository.obtenerTodos().stream()
            .filter(f -> f.getCliente().getId().equals(clienteId))
            .collect(Collectors.toList());
    }
}
