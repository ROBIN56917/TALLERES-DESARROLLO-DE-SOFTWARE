# Taller 2 — Sistema de Pagos de Servicios Públicos

## Compilar y ejecutar (Java 17+)

```bash
find src -name "*.java" > sources.txt
mkdir -p out
javac -d out @sources.txt
java -cp out com.taller.pagos.Main
```

## Estructura

```
src/com/taller/pagos/
├── Main.java                 # Demo: flujo principal, flujo alterno y 3 excepciones
├── modelo/                   # Entidades: Cliente, Cuenta (abstracta), CuentaAhorros,
│                              # CuentaCorriente, Factura, Pago, EstadoFactura, EstadoPago
├── repositorio/               # ICrudRepository<T,ID>, RepositorioEnMemoria<T,ID> (CRUD genérico)
│                              # y repos concretos: ClienteRepository, CuentaRepository,
│                              # FacturaRepository, PagoRepository
├── servicio/                  # Pagable (interfaz) y PagoService (las 4 funcionalidades)
└── excepcion/                 # SaldoInsuficienteException, FacturaYaPagadaException,
                               # EntidadNoEncontradaException
```

## Pilares POO aplicados
- **Abstracción**: `Cuenta` (clase abstracta), `Pagable` e `ICrudRepository<T,ID>` (interfaces).
- **Encapsulamiento**: atributos privados/protegidos + validación en setters/constructores.
- **Herencia**: `CuentaAhorros`/`CuentaCorriente` extienden `Cuenta`; los repos concretos
  extienden `RepositorioEnMemoria<T,ID>`.
- **Polimorfismo**: `retirar()` y `describirCuenta()` se comportan distinto según el tipo
  real de cuenta, invocados a través de la referencia abstracta `Cuenta`.
