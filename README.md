# Bci java Test
Restful Api para test tecnico de bci

> Las versiones usadas en este proyecto son las siguientes: <br> `Java 17.`<br> `Springboot: 3.0.6` <br> `maven: 3.8.2`

> Para compilar y ejecutar el proyecto, basta con ejecutar el comando`mvn spring-boot:run`.

> Las expresiones regulares tanto como el correo, la clave y el JWT secret, se configuran en el archivo `application.yml.` <br> Los mensajes de error estan descritos en el archivo `messages.properties`.

> El test tecnico usa una base de datos en memoria de tipo H2, el modelo de base de datos se crea al lanzar la app.

> Diagrama de solucion: <br>
![diagrama de flujo negocio-Estandar drawio (1) drawio](https://github.com/pcornejov/bciTest/assets/45262334/1cace9c1-e0b5-497d-8907-95a242743888)


## Swagger

- http://localhost:8080/swagger-ui/index.html

## Endpoints

- Crear usuario: POST http://localhost:8080/api/users

```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
        }
    ]
}
```

- Obtener todos los usuarios: GET http://localhost:8080/api/users

- Obtener usuario por ID: GET http://localhost:8080/api/users/{id}

- Actualizar usuario por ID: PUT http://localhost:8080/api/users/{id}

```
    {
    "name": "Juan Rodriguez Loyola",
    "email": "juan@dominio.cl",
    "password": "hunter3",
    "phones": [
        {
            "number": "333333333",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "999999999",
            "cityCode": "2",
            "countryCode": "99"
        }
    ]
}
```

- Eliminar usuario por ID: DELETE http://localhost:8080/api/users/{id}
