# IronHackFinalProjectBank

### COSAS A TENER EN CUENTA

1. Las clases padre (Account y User) son abstractas para que no se puedan instanciar
2. He creado varias carpetas para que sea más ordenado y limpio
3. No he usado Lombok, ya que da problemas a la hora de herencias, etc.
4. No tengo el método setId() ni setters en las constantes ni en el creationDate porque no tiene lógica incluirlos
5. Los métodos toString() de las clases hijas, están sobreescritos correctamente, ya que si lo sobreescribes de normal, los atributos que hereda no los tendrá en cuenta.
6. A la hora de acceder a los balance de los accounts, solo pueden acceder los primary owners y secondary owners a sus respectivas cuentas. He tenido en cuenta que los secondary owners también puedan acceder (Aparte de los Admins)

### Diagrama de Clases (No acabado)
![](DiagramaDeClasesBanco.png)