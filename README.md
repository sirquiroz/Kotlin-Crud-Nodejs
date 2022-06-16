# Api Rest con nodejs
Proyecto de Crud para APIREst basado en el [Tutorial | Nodejs ApiRest](https://www.youtube.com/watch?v=p8CoR-wymQg) - [codigo fuente](https://github.com/FaztWeb/mysql-nodejs-rest-api)
Comencemos con los primeros pasos para crear el ApiRest utiliando nodejs
### Crear archivos de configuración package.json
```
npm ini --yes]
```
### Instalar librerias de express y mysql
```
npm install express mysql
```
Instalacion de otras dependencias opcionales
```
npm install path morgan express-myconnection
```
Listado de depdendencias:
***Importantes***
-  express: ^4.18.1
- mysql: ^2.18.1
- nodemon: ^2.0.16

***Opcionales***
- path: ^0.12.7
- express-myconnection: ^1.0.4
- morgan: ^1.10.0

### Ejecutar Server
```
node src/index.js Server on port 3000
```
### Modulo para refrescar el server en Desarrollo
la D sirve para Developer y como depencia principal
```
npm install nodemon -D
```
Para utilizar nodemon se agrega esta linea en package.json
"dev": "nodemon src/index.js", y se ejecuta el comando
```
npm run dev
```

### Base de Datos
```
CREATE DATABASE IF NOT EXISTS dbcompany;

USE dbcompany;

CREATE TABLE employee (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) DEFAULT NULL,
  email VARCHAR(45) DEFAULT NULL,
  edad INT(11) DEFAULT NULL, 
  PRIMARY KEY(id)
);

DESCRIBE employee;

INSERT INTO employee values 
  (1, 'Ryan Ray',  'rray@company.com',40),
  (2, 'Joe McMillan', 'jmcmillan@company.com',38),
  (3, 'John chuchuca','jchuchuca@company.com',53);

SELECT * FROM employee;
```
## Procedimiento almacenado
```
USE dbcompany;
DELIMITER $$
USE `dbcompany`$$
CREATE PROCEDURE `employeeAddOrEdit` (
  IN _id INT,
  IN _name VARCHAR(45),
  IN _email VARCHAR(45),
  IN _edad INT
)
BEGIN 
  IF _id = 0 THEN
    INSERT INTO employee (name, email,edad)
    VALUES (_name, _email,_edad);

    SET _id = LAST_INSERT_ID();
  ELSE
    UPDATE employee
    SET
    name = _name,
    email = _email,
    edad = _edad
    WHERE id = _id;
  END IF;

  SELECT _id AS 'id';
END
```

# Códigos de ayuda

***Para el diseño***
- https://material.io/

***Tarea con ejemplos para el diseño***
- https://www.youtube.com/watch?v=YV721SU9msI
- https://www.youtube.com/watch?v=qivl9j_zivU
- Kotlin manual: https://developer.android.com/codelabs/kotlin-android-training-linear-layout#8

***NodeJS API con JSON WEB TOKEN (JWT) y Express***
- https://www.youtube.com/watch?v=cL3bXzUBFUA
- https://github.com/codigomentor/NodeJS-JWT
- https://github.com/lovisgod/Simple_Authentication_jwt_sample_nodejs
- https://www.youtube.com/watch?v=hOxN1QRUB4c&list=PLqPX-IDHkSdFHKXpeQLRAhCmM9yUd4niP
- https://developer.android.com/training/id-auth/authenticate
- https://blog.logrocket.com/implement-oauth-2-0-node-js/


***GraphQL***
- https://www.youtube.com/watch?v=RreRD41qlpw

+ Utilizar Volley con Kotlin 
- https://www.youtube.com/watch?v=1nkBZAenhOE
- https://escapesequence89.medium.com/volley-http-for-android-60999099ddda
- https://auth0.com/blog/authenticating-android-apps-developed-with-kotlin/
- https://dzone.com/articles/authenticating-android-apps-developed-in-kotlin
- https://hirukarunathilaka.medium.com/token-based-authentication-rest-api-implementation-for-android-kotlin-apps-d2109b18eb36


***Utilizar Retrofit con Kotlin***
- https://www.youtube.com/watch?v=aQP-mUGWh1U
- https://www.youtube.com/watch?v=L3pM5YuxYp4
- https://github.com/HirSK/android-api-retrofit
- https://github.com/tirgei/RetrofitAuthorization
- https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
- https://hirukarunathilaka.medium.com/token-based-authentication-rest-api-implementation-for-android-kotlin-apps-d2109b18eb36 


***API Publicas***
- https://dog.ceo/dog-api/documentation/
- https://jsoneditoronline.org/

***Sistema "MediCitas" kotlin con APIs, JWT, Retrifit2***
- https://github.com/cabugit/MyAppointments



## License
Copyright 2022 Miguel Quiroz
MIT

**Free Software, Hell Yeah!**
<!-- 
https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax -->