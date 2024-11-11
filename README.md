# Expense Tracker API

Challenge basado en: [Expense Tracker API](https://roadmap.sh/projects/expense-tracker-api)
Este proyecto es una API para llevar un registro de los gastos de un usuario. Permite crear,
actualizar, eliminar y filtrar gastos, además de ofrecer autenticación mediante JWT para proteger
los endpoints. Los gastos pueden ser categorizados en diferentes categorías como "Alimentos","
Ocio", "Electrónica", etc.

## Características

### Autenticación de Usuario

* **Registro de Usuario**: Los usuarios pueden registrarse con una cuenta nueva.
* **Generación y Validación de JWT**: Se usa JWT (JSON Web Token) para autenticar a los usuarios y
  gestionar sus sesiones.

### Gestión de Gastos

* **Listar y Filtrar Gastos**: Los usuarios pueden listar sus gastos y filtrarlos por:

    * Última semana
    * Último mes
    * Últimos 3 meses
    * Personalizado (permitiendo especificar una fecha de inicio y finalización)

* **Agregar un Nuevo Gasto**: Los usuarios pueden agregar un gasto con un monto y categoría.
* **Eliminar Gastos**: Los usuarios pueden eliminar un gasto previamente agregado.
* **Actualizar Gastos**: Los usuarios pueden actualizar el monto o la categoría de un gasto
  existente.

### Categorías de Gasto

Los gastos pueden ser categorizados de la siguiente manera (puedes decidir cómo implementarlo en el
modelo de datos):

* **Alimentos**: Compras de supermercado, productos alimenticios, etc.
* **Ocio**: Actividades recreativas, entretenimiento, etc.
* **Electrónica**: Compras relacionadas con dispositivos electrónicos.
* **Servicios Públicos**: Facturas de agua, luz, gas, internet, etc.
* **Ropa**: Compras de prendas de vestir.
* **Salud**: Gastos médicos, farmacia, etc.
* **Otros**: Cualquier otro gasto que no encaje en las categorías anteriores.

## Tecnologías Usadas

* **Backend**: SpringBoot / Java
* **Seguridad**: Spring Security / JSON Web Tokens (JWT)
* **Base de Datos**:  PostgreSQL
* **Validación de Datos**: Spring Validation