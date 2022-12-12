# IronHackFinalProjectBank

### COSAS A TENER EN CUENTA

1. Las clases padre (Account y User) son abstractas para que no se puedan instanciar
2. He creado varias carpetas para que sea más ordenado y limpio
3. No he usado Lombok, ya que da problemas a la hora de herencias, etc.
4. No tengo el método setId() ni setters en las constantes ni en el creationDate porque no tiene lógica incluirlos
5. Los métodos toString() de las clases hijas, están sobreescritos correctamente, ya que si lo sobreescribes de normal, los atributos que hereda no los tendrá en cuenta.
6. A la hora de acceder a los balance de los accounts, solo pueden acceder los primary owners y secondary owners a sus respectivas cuentas. He tenido en cuenta que los secondary owners también puedan acceder (Aparte de los Admins)

### Diagrama de Clases
![](DiagramaDeClasesBanco.png)

Los administradores pueden hacer y deshacer todo lo que quieran, en la clase security esta establecido.
Los Third Party Users pueden enviar y recibir dinero indicando las cuentas a donde desean hacer la transferencia.
Los Account Holder Users pueden acceder únicamente a sus cuentas, y pueden tener 1 o más cuentas y de distinto tipo

Las Checking Account tienen en cuenta 1 cosa antes de ser creadas, si el propietario es menor de 24 años será una Student Checking Account, sino, una Checking Account.
Las Student Account no tienen ni mantenimiento ni un mínimo de balance para poder ser creadas. En cambio, las Checking sí.

Las Savings Account deben de tener un mínimo de balance (1000), y un interest Rate, este interest rate va aumentando el valor de la cuenta cada año.
Las Credit Card Account son parecidas a las Savings pero el valor va aumentando cada mes.

Todas las cuentas tienen un penalty fee, esto quiere decir que si una cuenta cae por debajo del mínimo que debe de tener, se aplicará el penalty fee.