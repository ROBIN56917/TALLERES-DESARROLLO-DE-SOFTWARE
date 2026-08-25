package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;


public class Pedido {


    private BigDecimal total;


    public Pedido(BigDecimal total){

        this.total = total;

    }


    public BigDecimal getTotal(){

        return total;

    }

}