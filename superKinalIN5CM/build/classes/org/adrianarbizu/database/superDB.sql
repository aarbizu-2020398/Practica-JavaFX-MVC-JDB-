create database superDB;

use superDB;

create table Clientes(
	clienteID int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
	telefono varchar(15) not null,
    direccion varchar(200) not null,
    NIT varchar(30) not null,
    primary key pk_clienteId (clienteId)	
);

insert into Clientes(nombre, apellido,telefono, direccion) values
('Carlos','Perez', '2481-5135','Ciudad'),
('Mario','Estrada', '2481-8264','Ciudad'),
('Javier','Aguilar', '2481-1282','Ciudad');

-- CRUD Clientes

-- Agregar
Delimiter $$
create procedure sp_AgregarClientes(in nom varchar(30), in ape varchar (30), in tel varchar(15), in dir varchar (200))
	Begin
		insert into Clientes(nombre, apellido, telefono, direccion) values
			(nom, ape, tel, dir);
	End$$
Delimiter ;

-- Listar
Delimiter $$
create procedure sp_ListarClientes()
	begin
		select
			Clientes.clienteId,
            Clientes.nombre,
            Clientes.apellido,
            Clientes.telefono,
            Clientes.direccion
				from Clientes;
	end $$
Delimiter ;

-- Eliminar
Delimiter $$
create procedure sp_EliminarCliente(in cliId int)
	begin 
		delete from Clientes
			where clienteId = cliId;
	end $$
Delimiter ;

-- Buscar
Delimiter $$
create procedure sp_BuscarCliente(in cliId int)
	begin
		select
            Clientes.nombre,
            Clientes.apellido,
            Clientes.telefono,
            Clientes.direccion
				from Clientes
					where clienteId = cliId;
	end $$
delimiter ;
             
-- Editar
Delimiter $$
create procedure sp_EditarCliente(in cliId int, in nom varchar(30), in ape varchar (30), in tel varchar(15), in dir varchar (200))
	begin
		update Clientes
			set
				nombre = nom,
                apellido = ape,
                telefono = tel,
                direccion = dir
					where clienteId = cliId;
	end $$
Delimiter ;




SET GLOBAL time_zone = '-6:00';