

# FernanPop Web (Java Server Pages)

## _Manual de usuario_

- Seguro y confiable.

- Rapido y eficaz.

- Amigable y practico.

  

  [TOC]

________________

### ***Requisitos previos antes de ejecutar el programa en sistemas operativos Windows y MacOs.***

**Windows**

- Tener un sistema operativo superior a Windows 7. Necesitas un espacio en RAM como mínimo de 128MB. Además espacio en el disco duro mínimo 124MB para arrancar JRE (Java Runtime Environment). Contar con un explorador (Microsoft Edge, Firefox, Google Chrome, etc.) nos servirá para descargar el JRE en nuestro ordenador.

**MacOs**

- Tener un sistema operativo superior a Mac Os X 10.8.3 de 64bits.Necesitas un espacio en RAM como mínimo de 128MB. Además espacio en el disco duro mínimo 124MB para arrancar JRE (Java Runtime Environment). Contar con un explorador (Safari, Firefox, Google Chrome, etc.) nos servirá para descargar el JRE en nuestro ordenador.

### **Instalación de JRE (Java Runtime Environment).**

- El **JRE** es el **J**ava **R**untime **E**nvironment, en español es el Entorno de Ejecución de Java, en palabras del propio portal de Java es la implementación de la Máquina virtual de Java que realmente ejecuta los programas de Java, esto quiere decir que aquí encontraremos todo lo necesario para ejecutar nuestras aplicaciones escritas en Java.

- [link de descarga](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)

  * Instalación en Windows escoger el fichero de descarga si es de 32 bits(x86) o 64 bits(x64).

    ![img](https://lh3.googleusercontent.com/zS1y70n8KDCeW47Wq9lf5Y8KzXWFvuh08ZZmg-u2x3kNqboLRZvygWN1FBM_W3RVGc9Efr_z3XIxtElMdyT8u-ZeN4-VhnmtHvcnVdKlQc1lM95Ps3kbn9jddUdrEAPlwyf0jHQ=s0)

  

  * Instalación en Mac Os X escoger el fichero de descarga si es de 32 bits(x86) o 64 bits(x64)

    ![img](https://lh6.googleusercontent.com/4-0EPGxQfTwkRFN_LjT7p6Zq4wP1TRjQNsREalZ_GCNXvPd_Rw8zJlP8eC7sTtzdRM-4So9_8caPelxUX8Xh7IpJOUAA1XbwNvLU_bMt0g09ckQMJwYZ3awjBr48tSO-jkXVomw=s0)



#### Obteniendo Servidor Apache Tomcat version 8

------

Apache Tomcat es un contenedor Java Servlet, o contenedor web, que proporciona la funcionalidad extendida para interactuar con Java Servlets, al tiempo que implementa varias especificaciones técnicas de la plataforma Java: JavaServer Pages (JSP), Java Expression Language (Java EL) y WebSocket. Esto nos permitirá poder ejecutar nuestra aplicación web.

A continuación les sugerimos ingresar al link o enlace de descarga del servido apache tomcat.

- [Enlace de descarga de servidor.](https://tomcat.apache.org/download-80.cgi "Página para descargar apache tomcat")

##### ¿Cuál descargar?

-  Tanto en usuarios de sistemas operativos Windows o Mac pueden optar por la versión de Source Code y descargando en formato zip, la cual luego tendrían que descomprimir. 

![tomcat-file-download](/Users/flaviooria/Desktop/tomcat.png)

------

#### **Sobre FernanPop**

- Esta aplicación  consiste en la venta y compra de productos. La cual permite el registro todas las personas que deseen con su correo electrónico. Cada usuario podrá crear productos e ir gestionandolos. Como usuario puedes ver tus datos y editarlos, ver tus artículos en venta y los que están en la aplicación. Ver tu historial de venta y de compras, realizar la venta de tu producto o quitarlo si no te gusta. Por último al realizar cada compra podrá tener una valoración tanto al vendedor como al comprador.

_**Nota:** Para realizar las operaciones sobre  un producto en la página, los usuarios deben de estar registrados, si fuera el caso de que solo se encuentra un solo usuario, el programa operará sin problema alguno, sin embargo no se podría llegar a finalizar el cierre de una venta, de dicho modo el histórico de venta y compra de cada usuario no sería visualizado._



## Ingresando a FernanPop (***Primeros Pasos***)



- Vista principal de la web, a continuación deberas de registrarte ingresando correos válidos es decir que funcionen con alguna entidad de correo como Gmail, Outlook o la que utilice. 



Vista móvil de la página[^1]

![localhost-iPhone-XS-X-375x812](/Users/flaviooria/Downloads/localhost-iPhone-XS-X-375x812.png)



- Vista de Escritorio[^1]

![localhost-Medium-Screen-1024x800](/Users/flaviooria/Downloads/localhost-Medium-Screen-1024x800.png)



[^1]: FernanPop Web.



- Registrándonos, revisen que sus datos sean correctos. Le das click a registrarse la cual te llevará a la pagina anterior vista. [^1] 

![Captura de pantalla 2021-09-08 a las 19.43.05](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-08 a las 19.43.05.png)



### Acceso de Token seguridad

Al iniciar sesión, nos encontraremos con un token, que nos servirá para validar nuestro datos, daremos click en icono de pegar y le damos a continuar.

 ![Captura de pantalla 2021-09-08 a las 19.53.44](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-08 a las 19.53.44.png)



Luego pegaremos nuestro token y nos validamos accediendo a la página de inicio.

![Captura de pantalla 2021-09-08 a las 19.57.23](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-08 a las 19.57.23.png)



**La HomePage o Pagina de Inicio de FernanPop** 

- Desde este punto tendremos una cierta variedad de opciones que podremos realizar y procesar, tales como, subir un producto, ver productos , solicitar y buscar productos y enviar mensajes con otros usuarios.

  ![localhost-Medium-Screen-1024x800 (1)](/Users/flaviooria/Downloads/localhost-Medium-Screen-1024x800 (1).png)



## Subida de un producto 

+ Vamos aprender de forma muy rápida y sencilla a crear un nuevo producto y ponerlo en venta.

+ Debes de dar click al menú que se encontrara en el lado izquierdo superior, ahí podrás dirigirte a distintas opciones, se vera de esta forma cuando sea desplegada.

  

  

![Nuevo proyecto](/Users/flaviooria/Downloads/Nuevo proyecto.png)



- Añadiendo datos de nuestro producto, la cual tendrás que subir la foto de tu producto te recomendamos que sea de buena calidad, un titulo adecuado y una descripción que llame la atención de los usuarios y añadir el precio una vez rellenado todo darle click a subir producto.

  

![Captura de pantalla 2021-09-08 a las 23.45.03](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-08 a las 23.45.03.png)



A posteriori se mostrará en la página de inicio mostrando tus productos recién creados.

## Editar Perfil

- En esta sección podrás editar tus datos personales, incluido el avatar o foto de tu perfil. Así mismo tienes la libertad de eliminar tu cuenta si no deseas contar  más con los servicios de FernanPop.

  

  ![Captura de pantalla 2021-09-09 a las 0.00.00](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 0.00.00.png)



## Ver Productos

- Veras en la siguiente imagen como se muestran los productos, además puedes realizar búsquedas acuerdo al producto que te interese, y poder realizar una solicitud de compra o comunicarte con el cliente respecto al producto.

![Captura de pantalla 2021-09-09 a las 17.15.24](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.15.24.png)



- ### **_Solicitando un producto_ ** 

  

  - Deberas de escoger el producto y es muy fácil como darle a ver producto, después tan solo es pinchar en solicitar y listo.

    ![Nuevo proyecto (1)](/Users/flaviooria/Downloads/Nuevo proyecto (1).png)



- ### Chat sobre el producto

  

  - Esta parte ingresaras el asunto y redactaras tu mensaje comunicándote directamente con el propietario.

  - Debes de tener en cuenta que todos los datos tienen que estar rellenados incluyendo la opción del vendedor.

    

    ![Captura de pantalla 2021-09-09 a las 17.37.26](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.37.26.png)



![Captura de pantalla 2021-09-09 a las 17.37.35](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.37.35.png)



## Notificaciones 	

- Esta apartado es para indicarte que en la página de inicio, cada vez que tengas una solicitud pendiente de aprobar, valoraciones que indicar y conversaciones pendientes se te serán mostradas por los iconos de abajo y haciendo click en cada uno de ellos se te mostrara los mensajes. Para acceder a ellos es tan fácil como dar click en los botones de aceptar o ver.



![Captura de pantalla 2021-09-09 a las 17.59.08](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.59.08.png)

![Captura de pantalla 2021-09-09 a las 17.59.24](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.59.24.png)

![Captura de pantalla 2021-09-09 a las 17.59.54](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 17.59.54.png)



## Ver Mensajes (Recibidos o Enviados)

- Aquí se mostrara tanto los mensajes enviados o recibidos, que tengamos, puedes acceder a esta página de formas mediante el menú de navegación que tenemos o dando en el botón de ver que se muestra en las notificaciones del iconos de mensajes.

  

![Captura de pantalla 2021-09-09 a las 18.12.54](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 18.12.54.png)

![Captura de pantalla 2021-09-09 a las 18.14.24](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 18.14.24.png)



- Como ves tienes una bandeja de entrada de los mensajes que te escriben los usuarios  y aquellos que escribas tu también serán visto, tienes la opción de eliminarlos en el icono contenedor de basura.

  Además tienes un botón flotante la cual al hacer click te enlaza con la página para enviar un mensaje. 

  De esta forma te podrás comunicar con el usuario que te halla enviado el mensaje.

   



## Venta Producto

- Al acceder a una solicitud pendiente es el paso a priori para realizar la venta del producto, puesto que has encontrado un usuario interesado en este, procederemos atender su solicitud generando la venta de nuestro producto. Sin embargo si no deseas continuar con la operación puedes eliminar lo solicitud y listo.

  

![Captura de pantalla 2021-09-09 a las 18.29.08](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 18.29.08.png)

![Captura de pantalla 2021-09-09 a las 18.30.08](/Users/flaviooria/Desktop/Captura de pantalla 2021-09-09 a las 18.30.08.png)



## Ver Tratos 

- Se mostraran todos los tratos que hayas generado ya sean de tipo venta o compra los tendrás todos.

![localhost_8080_FernanPop_war_exploded_pages_tratos.jsp(iPhone 6_7_8)](/Users/flaviooria/Downloads/localhost_8080_FernanPop_war_exploded_pages_tratos.jsp(iPhone 6_7_8).png)



- Eso sería todo con la explicación de uso de FernanPop Web, espero que les guste. 
