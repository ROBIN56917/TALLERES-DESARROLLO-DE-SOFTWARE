package co.edu.umanizales.tallerpoo;


import java.math.BigDecimal;

public class Cuenta {

    protected final String numero;
    protected BigDecimal saldo;


    public Cuenta(String numero, BigDecimal saldoInicial) {

        if(saldoInicial.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException(
                    "El saldo inicial no puede ser negativo"
            );
        }

        this.numero = numero;
        this.saldo = saldoInicial;
    }


    public void depositar(BigDecimal monto){

        if(monto.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException(
                    "El monto debe ser positivo"
            );
        }

        saldo = saldo.add(monto);
    }


    public void debitar(BigDecimal monto){

        if(monto.compareTo(saldo)>0){
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente"
            );
        }

        saldo = saldo.subtract(monto);
    }


    public BigDecimal getSaldo(){

        return saldo;
    }


    public String getNumero(){

        return numero;
    }
}

