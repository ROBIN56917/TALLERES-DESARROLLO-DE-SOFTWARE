package co.edu.umanizales.tallerpoo;


public class CheckoutService {


    public void finalizarCompra(
            Pedido pedido,
            Pago metodoPago
    ){


        metodoPago.procesar(
                pedido.getTotal()
        );


        System.out.println(
                "Compra finalizada usando: "
                        + metodoPago.getDescripcion()
        );


    }

}