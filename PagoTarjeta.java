package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;


public class PagoTarjeta implements Pago {


    private String numeroTarjeta;


    public PagoTarjeta(String numeroTarjeta){

        this.numeroTarjeta = numeroTarjeta;

    }



    @Override
    public void procesar(BigDecimal monto){

        System.out.println(
                "Pago tarjeta: $" + monto
        );

    }



    @Override
    public String getDescripcion(){

        return "Tarjeta";

    }

}