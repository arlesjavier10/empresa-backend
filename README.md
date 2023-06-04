# empresa-backend

Pasos:

1. Crear bd en postgres: empresabd
2. Inportar proyecto maven, compilar y ejecutar (automaticamente crea la tabla cliente)
3. Validar:

-Listar: GET
http://localhost:8090/clientes

-Registrar: POST
http://localhost:8090/clientes

{
    "nombres": "Nombre",
    "apellidos": "Apellido",
    "dni": "12345678",
    "direccion": "Direccion",
    "telefono": "987654321",
    "email": "email@gmail.com"
}

-Buscar por ID: GET
http://localhost:8090/clientes/1

-Actualizar: PUT
http://localhost:8090/clientes

{
    "idCliente": 1,
    "nombres": "Nombre Nombre",
    "apellidos": "Apellido Apellido",
    "dni": "12345678",
    "direccion": "Direccion",
    "telefono": "987654321",
    "email": "email@gmail.com"
}

-Eliminar: DELETE
http://localhost:8090/clientes/2

