package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;


public class PagoTransferencia implements Pago {


    private String cbu;


    public PagoTransferencia(String cbu){

        this.cbu = cbu;

    }



    @Override
    public void procesar(BigDecimal monto){

        System.out.println(
                "Transferencia realizada: $" + monto
        );

    }



    @Override
    public String getDescripcion(){

        return "Transferencia";

    }

}
