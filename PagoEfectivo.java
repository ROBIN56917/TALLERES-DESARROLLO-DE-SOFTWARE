package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;


public class PagoEfectivo implements Pago {


    @Override
    public void procesar(BigDecimal monto){

        System.out.println(
                "Pago efectivo: $" + monto
        );

    }



    @Override
    public String getDescripcion(){

        return "Efectivo";

    }

}