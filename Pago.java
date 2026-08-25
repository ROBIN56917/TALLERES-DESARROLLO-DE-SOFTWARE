package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;


public interface Pago {


    void procesar(BigDecimal monto);


    String getDescripcion();

}