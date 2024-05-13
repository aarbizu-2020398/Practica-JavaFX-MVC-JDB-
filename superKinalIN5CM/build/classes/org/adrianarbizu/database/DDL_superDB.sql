drop database if exists DDL_superDB;

create database if not exists DDL_superDB;

use DDL_superDB;

create table Clientes(
	clienteID int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
	telefono varchar(15) not null,
    direccion varchar(200) not null,
    NIT varchar(100) not null,
    primary key pk_clienteId (clienteId)	
);
 
 
 create table cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar(30),
    descripcionCargo varchar(100),
    primary key pk_cargoId(cargoId)
);
 
 
 create table Empleados(
    empleadoId int (11),
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal(10,2)not null,
    horaEntrada Time,
    horaSalida Time,
    cargoId int(11),
    encargadoId int(11),
    primary key PK_empleadoId(empleadoId),
    constraint FK_Empleados_Cargos foreign key Empleados (cargoId)
        references Cargos(cargoId),
    constraint FK_encargadoId foreign key Empleados(encargadoId)
        references Empleados(empleadoId)
);

create table Facturas(
    facturaId int(11),
    fecha date,
    hora time,
    clienteId int(11) ,
    empleadoId int(11),
    total decimal(10,2),
    primary key PK_facturaId(facturaId),
    constraint FK_Factura_Clientes foreign key Facturas(clienteId)
        references Clientes(clienteId),
    constraint FK_Facturas_Empleados foreign key Facturas(empleadoId)
        references Empleados(empleadoId)
);


 create table ticketSoporte(
	ticketsoporteId int(11),
    descripcionTicket varchar(250),
    estatus varchar(30),
    clienteId int(11) ,
    facturaId int(11),
   primary key PK_ticketSoporteId(ticketSoporteId),
    constraint FK_TicketSporte_Clientes foreign key TicketSporte(clienteId)
        references Clientes(clienteId),
    constraint FK_TicketSporte_Facturas foreign key TicketSporte(facturaId)
        references Facturas(facturaId)
);



create table Distribuidores(
    distribuidorId int (11),
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15),
    telefonoDitribuidor varchar(15),
    web varchar(50),
    primary key PK_distribuidorId(distribuidorId)
);


create table categoriaproductos(
	categoriaProductosId int(11),
    nombreCategoria varchar(30),
    descripcionCategoria varchar(100),
        primary key PK_categoriaProductosId(categoriaProductosId)
);


create table Productos(
    productoId int(11),
    nombreProducto varchar(50) not null,
    descripcionProducto varchar (100),
    cantidadStock int(11),
    precioVentaUnitario decimal(10,2),
    precioVentaMayor decimal(10,2),
    precioComprar decimal(10,2),
    distribuidorId int(11),
    categoriaProductosId int(11),
    primary key PK_productoId(productoId),
    constraint FK_Productos_Distribuidores foreign key Productos(distribuidorId)
        references Distribuidores(DistribuidorId),
	constraint FK_Productos_CategoriaProductos foreign key Productos(categoriaProductosId)
        references CategoriaProductos(categoriaProductosId)
);

create table compras(
	compraId int(11),
    fechaCompra date,
    totalCompra decimal(10,2),
    primary key pk_compraId (compraId)

);

create table Promociones(
	promocionId int,
    precioPromocio decimal(10,2),
    descripcionPromocion varchar(100),
    fechaInicio date, 
    fechaFinalizacion date ,
    productoId int (11),
    primary key promocionId(promocionId),
    constraint FK_Promociones_Productos foreign key Promociones(productoId)
		references Productos(productoId)
);
create table DetalleCompras(
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int(11),
    compraId int(11),
    primary key PK_detalleCompraId(detalleCompraId),
    constraint FK_DetalleCompras_Productos foreign key DetalleCompras(productoId)
		references Productos(productoId),
	constraint FK_DetalleCompras_Compras foreign key DetalleCompras(compraId)
		references Compras(compraId)
);
create table DetalleFacturas(
    detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key PK_detalleFacturaId(detalleFacturaId),
    constraint FK_DetalleFacturas_Facturas foreign key DetalleFacturas(facturaId)
        references Facturas(facturaId),
    constraint FK_DetalleFacturas_Productos foreign key DetalleFacturas(productoId)
        references Productos(productoId)
);



/*insert into Clientes(nombre, apellido,telefono, direccion, NIT) values
('Carlos','Perez', '5742-6921','Ciudad','154213-023'),
('Juan', 'González','4323-8754','12 Avenida Sur, Zona 10, Ciudad de Guatemala','131654-025'),
('María', 'López', '5697-4651','Calle de los Mangos, Antigua Guatemala','2154613-05'),
('Carlos', 'Castillo', '5236-6964','Colonia Primavera, Quetzaltenango','213113-012'),
('Ana', 'Martínez', '5743-2314','Aldea El Progreso, Petén','456110-05'),
('Pedro', 'Díaz', '5862-3620','Barrio La Reforma, Huehuetenango','113241-014'),
('Elena', 'Ramírez', '5642-5123','Avenida del Volcán, Escuintla','1213163-021'),
('Jorge', 'Hernández', '5452-1122','Callejón de los Cocos, Cobán','45134561-5'),
('Isabel', 'Chávez', '5525-6562','Condominio Las Rosas, Chimaltenango','616565-024'),
('Luis', 'Molina', '4579-6953','Carretera a Panajachel, Sololá','1314665-035'),
('Marta', 'Flores', '13232-665','Residencial El Bosque, Retalhuleu','161613-021'),
('Mario','Estrada', '2481-8264','Ciudad','154654651-051'),
('Javier','Aguilar', '2481-1282','Ciudad','1245123-021');
*/

