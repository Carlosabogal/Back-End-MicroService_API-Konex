
# Backend del Frontend - Ventas y Medicamentos

## Configuración de la Base de Datos Oracle
Para ejecutar el Backend del Frontend correctamente, asegúrate de tener instalada la base de datos Oracle y configurar las siguientes propiedades en el archivo de configuración de la aplicación (por ejemplo, application.properties):

    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
    
    spring.datasource.driverClassName=oracle.jdbc.driver.OracleDriver
    
    spring.datasource.username=SYSTEM
    
    spring.datasource.password=12345
    
    spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
    
    spring.jpa.hibernate.ddl-auto=update
    
    spring.jpa.show-sql=true
    
    spring.jpa.properties.hibernate.format_sql=true
    
    server.error.whitelabel.enabled=false

# Configurar  Conexion Front End
Validación del Uso de "@CrossOrigin" en el Controlador y Angular
Recuerda usar el @CrossOrigin(origins = "http://localhost:4200")  una anotación que se utiliza tanto en el controlador del backend como en el código Angular del frontend para gestionar la política de intercambio de recursos entre diferentes dominios. Su uso es esencial para permitir que el frontend y el backend se comuniquen correctamente cuando se ejecutan en dominios diferentes.


Asegúrate de que la anotación @CrossOrigin(origins = "http://localhost:4200") esté presente en cada controlador del backend que necesite permitir solicitudes desde el frontend Angular. Esta anotación se coloca en la clase de controlador, justo encima de la definición de la clase o de un método específico dentro del controlador. Su presencia permitirá que el backend responda a las solicitudes enviadas desde "http://localhost:4200", que es la ubicación predeterminada del servidor de desarrollo de Angular.

Ejemplo:

    @RestController
    @RequestMapping("/venta")
    @CrossOrigin(origins = "http://localhost:4200") // Anotación para permitir solicitudes desde el frontend Angular
    public class VentaController {
    
        // ... Código del controlador ...
    
    }
Se debe validar que este en ambos controladores

# Ventas
El componente de Ventas maneja las solicitudes relacionadas con ventas y se encarga de interactuar con la base de datos Oracle para realizar las operaciones correspondientes.

### Funcionalidades Principales
## Realizar Venta (POST /venta/realizar): 
Permite realizar ventas de medicamentos mediante la recepción de una lista de objetos VentaDTO que contienen los IDs de los medicamentos y las cantidades vendidas. La venta se registra en la base de datos Oracle y se actualiza el stock de medicamentos.

    {
      "nombre": "Aspirina",
      "laboratorio": "Bayer",
      "fechaFabricacion": "2023-07-21",
      "fechaVencimiento": "2024-07-21",
      "cantidadStock": 100,
      "valorUnitario": 10.99
 


## Actualizar Venta (PUT /venta/{ventaId}):
Permite actualizar una venta existente mediante la recepción de un objeto VentaDTO con la nueva información. La venta se actualiza en la base de datos Oracle con los nuevos detalles proporcionados.
  
    {
            "medicamentoId": 1,
            "cantidad": 10
    
    }

Solo se toma en cuenta sel medicamento id y cantidad debido a que el resto se hace teniendo en cuenta el valor unitario del medicamento 
## Eliminar Venta (DELETE /venta/{id}): 
Permite eliminar una venta existente de la base de datos Oracle mediante el ID de la venta.
    http://localhost:8080/venta/1

## Buscar Ventas por Rango de Fechas (GET /venta/filtrar):
Permite obtener una lista de ventas realizadas dentro de un rango de fechas específico. Se envían las fechas de inicio y fin como parámetros en el formato dd/mm/aaaa.

    http://localhost:8080/venta/filtrar?fechaInicio=2023-07-01T00:00:00&fechaFin=2024-07-22T21:58:11
    
## Obtener Todas las Ventas (GET /venta): 
Permite obtener todas las ventas registradas en la base de datos.

# Medicamentos
El componente de Medicamentos maneja las solicitudes relacionadas con medicamentos y se encarga de interactuar con la base de datos Oracle para realizar las operaciones correspondientes.

### Funcionalidades Principales
## Crear Medicamento (POST /medicament): 
Permite crear un nuevo medicamento mediante la recepción de un objeto Medicament. El medicamento se registra en la base de datos Oracle.
  
    {
    "nombre": "Aspirina",
    "laboratorio": "Bayer",
    "fechaFabricacion": "2023-07-21",
    "fechaVencimiento": "2024-07-21",
    "cantidadStock": 100,
    "valorUnitario": 10.99



## Actualizar Medicamento (PUT /medicament/{id}):
Permite actualizar un medicamento existente mediante la recepción de un objeto Medicament con la nueva información. El medicamento se actualiza en la base de datos Oracle con los nuevos detalles proporcionados.

    http://localhost:8080/medicament/1
    
    {
        "nombre": "Dolex Ultra",
        "laboratorio": "Bayer",
        "fechaFabricacion": "2023-07-21",
        "fechaVencimiento": "2024-07-21",
        "cantidadStock": 100,
        "valorUnitario": 30.99
    }


## Eliminar Medicamento (DELETE /medicament/{id}):
Permite eliminar un medicamento existente de la base de datos Oracle mediante el ID del medicamento.

    http://localhost:8080/medicament/1

## Buscar Medicamento por ID (GET /medicament/{id}): 
Permite obtener un medicamento específico de la base de datos Oracle mediante el ID del medicamento.

    http://localhost:8080/medicament/1

## Buscar Medicamentos con Filtros (GET /medicament):
Permite obtener una lista de medicamentos con posibles filtros como el nombre y el laboratorio. Además, admite paginación para mostrar resultados por página.
