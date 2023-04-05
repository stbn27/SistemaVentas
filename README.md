## Sistema de ventas

- Sistema de ventas en Java es una solución elegante y eficiente para administrar y optimizar las ventas de su empresa. Con una interfaz intuitiva y fácil de usar, nuestro sistema le permite llevar un seguimiento detallado de sus ventas, clientes y provedores, generando informes y estadísticas en tiempo real para ayudarle a tomar decisiones informadas y mejorar su negocio.”

### Todo lo realizado fue gracias al canal de YouTube de: [Vida Informatico - AS](https://www.youtube.com/@vidainformatico-as/featured "Vida Informatico - AS")


# Informacion y resumen:


El programa se conecta a una **base de datos** de Acces donde de almacena toda la informacion, la cual se encuentra dentro de la carpeta del mismo programa.

1. La ventana principal tiene un  **login** para acceder, donde existen dos roles uno de **Administrador** con todas las opciones activadas y otro de **Asistente** para que solo pueda ejecutar operaciones sencillas como realizar ventas o registrar clientes..
![Login Ventana](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/Login.png?raw=true "Login Ventana")

2. Una vez que el login haya sido exitoso se abre la interfas principal, dependendiendo el rol tendran mas opciones.
> -- Ventana administrador:  
  ![Ventana Adminisrador](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/VentanaPrincipalAdministrador.png?raw=true "Ventana Adminisrador")
  
  **En esta ventana el usuario puede:**
- Realizar una venta.
- Añadir y visualizar clientes.
- Añadir y visualizar provedores.
- Añadir y visualizar productos.
- Vializar todas las ventas y grafica de ventas por dia.
- Modificar los datos de la empresa.
- Agregar un nuevo Usuario.

**Acciones de cada ventana:**
En la ventana de **Nueva venta,** unicamente se ingresa el codigo del producto y los demas campos se rellenan de forma automatica, lo mismo sucede en el campo DNI/clave del cliente, posteriormente se ingresa la cantidad de producto y se van apilando en la tabla,  y el total va cambiando de forma automatica. Una vez finalizada la venta se da clip sobre el boton generar venta y en automatico se abre un pdf con la informacion de la venta; los pdf de igual forma se pueden visualizar en el apartado de ventas.
![Ventana Nueva Venta](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/VentaNueva.gif?raw=true "Ventana Nueva Venta")

Posteriormente en la ventana registro de **productos **se pueden modificar nombres, stock, descripcion, costo de otras cosas mas de cada producto o bien añadir nuevos productos. Esta misma logica tienen las demas pestañas **provedor, clientes**.
![Productos ventana](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/ResgitroDeProductos.gif?raw=true "Productos ventana")

Ademas en la seccion de configuracion se puede personalizar la informacion de la empresa, dicha informacion se usa para generar el reporte de cada venta. En la pestaña de Nuevo usuario permite agregar otro usuario ya sea administrador o Asistente.
En la ventana de productos permite visualizar todos los productos vendidos ademas de ver el reporte de cada venta en PDF:
![Ventas realizadas](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/Ventas.gif?raw=true "Ventas realizadas")

Finalmete al acceder como asistente se le bloquean los botones innesarios para esta puesto quedando de la siguiete forma:
![Ventana Asistente](https://github.com/stbn27/SistemaVentas/blob/master/ImagenesProyecto/VentanaAsistente.gif?raw=true "Ventana Asistente")

Este proyecto se logro gracias al canal de Youtube:** Vida Informatico - AS**
> Autor: Esteban Jose
