package co.edu.umanizales.tallerpoo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class SistemaBanco {


    private Scanner teclado = new Scanner(System.in);

    private ArrayList<Cuenta> cuentas = new ArrayList<>();


    public void iniciar(){

        int opcion;


        do{

            System.out.println("\n===== SISTEMA BANCARIO =====");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Mostrar cuentas");
            System.out.println("6. Salir");


            System.out.print("Seleccione: ");

            opcion = teclado.nextInt();


            switch(opcion){


                case 1:

                    crearCuenta();

                    break;


                case 2:

                    consultarSaldo();

                    break;


                case 3:

                    depositar();

                    break;


                case 4:

                    retirar();

                    break;


                case 5:

                    mostrarCuentas();

                    break;


                case 6:

                    System.out.println(
                            "Gracias por usar el sistama bancario de Robinson"
                    );

                    break;


                default:

                    System.out.println(
                            "Opción incorrecta"
                    );

            }


        }while(opcion != 6);


    }



    // CREAR CUENTA

    private void crearCuenta(){


        teclado.nextLine();


        System.out.print(
                "Número de cuenta: "
        );

        String numero =
                teclado.nextLine();



        System.out.print(
                "Saldo inicial: "
        );

        BigDecimal saldo =
                teclado.nextBigDecimal();



        Cuenta nuevaCuenta =
                new Cuenta(numero, saldo);



        cuentas.add(nuevaCuenta);



        System.out.println(
                "Cuenta creada correctamente"
        );


    }




    // BUSCAR CUENTA POR NUMERO


    private Cuenta buscarCuenta(String numero){


        for(Cuenta cuenta : cuentas){


            if(cuenta.getNumero().equals(numero)){


                return cuenta;


            }


        }


        return null;


    }





    // CONSULTAR SALDO


    private void consultarSaldo(){


        teclado.nextLine();


        System.out.print(
                "Número de cuenta: "
        );


        String numero =
                teclado.nextLine();



        Cuenta cuenta =
                buscarCuenta(numero);



        if(cuenta != null){


            System.out.println(
                    "Saldo actual: "
                            + cuenta.getSaldo()
            );


        }else{


            System.out.println(
                    "Cuenta no encontrada"
            );


        }


    }





    // DEPOSITAR


    private void depositar(){


        teclado.nextLine();


        System.out.print(
                "Número de cuenta: "
        );


        String numero =
                teclado.nextLine();



        Cuenta cuenta =
                buscarCuenta(numero);




        if(cuenta != null){


            System.out.print(
                    "Valor a depositar: "
            );


            BigDecimal monto =
                    teclado.nextBigDecimal();



            cuenta.depositar(monto);



            System.out.println(
                    "Depósito realizado"
            );



        }else{


            System.out.println(
                    "Cuenta no encontrada"
            );


        }


    }





    // RETIRAR


    private void retirar(){


        teclado.nextLine();


        System.out.print(
                "Número de cuenta: "
        );


        String numero =
                teclado.nextLine();



        Cuenta cuenta =
                buscarCuenta(numero);




        if(cuenta != null){


            System.out.print(
                    "Valor a retirar: "
            );



            BigDecimal monto =
                    teclado.nextBigDecimal();




            try{


                cuenta.debitar(monto);


                System.out.println(
                        "Retiro realizado"
                );


            }catch(SaldoInsuficienteException e){


                System.out.println(
                        e.getMessage()
                );


            }




        }else{


            System.out.println(
                    "Cuenta no encontrada"
            );


        }


    }






    // MOSTRAR TODAS LAS CUENTAS


    private void mostrarCuentas(){


        if(cuentas.isEmpty()){


            System.out.println(
                    "No hay cuentas registradas"
            );


            return;


        }



        System.out.println(
                "\n===== CUENTAS REGISTRADAS ====="
        );



        for(Cuenta cuenta : cuentas){


            System.out.println(
                    "Cuenta: "
                            + cuenta.getNumero()
                            + " | Saldo: "
                            + cuenta.getSaldo()
            );


        }


    }



}