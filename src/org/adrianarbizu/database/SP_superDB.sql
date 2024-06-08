use superDB;

-- ===============================================CRUD CLIENTES============================================================
-- =========================================================================================================================

Delimiter $$
create procedure sp_agregarClientes(in ni varchar(30),in nom varchar(30), in ape varchar (30), in tel varchar(15), in dir varchar (200))
	Begin
		insert into Clientes(NIT,nombre, apellido, telefono, direccion) values
			(ni,nom, ape, tel, dir);
	end$$
Delimiter ;


-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');


Delimiter $$
create procedure sp_listarClientes()
	begin
		select
			Clientes.clienteId,
            Clientes.NIT,
            Clientes.nombre,
            Clientes.apellido,
            Clientes.telefono,
            Clientes.direccion
				from Clientes;
	end $$
Delimiter ;



Delimiter $$
create procedure sp_eliminarCliente(in cliId int)
	begin 
		delete from Clientes
			where clienteId = cliId;
	end $$
Delimiter ;


-- call sp_EliminarCliente(4);


Delimiter $$
create procedure sp_buscarCliente(in cliId int)
	begin
		select
			Clientes.clienteId,
			Clientes.NIT,
            Clientes.nombre,
            Clientes.apellido,
            Clientes.telefono,
            Clientes.direccion
				from Clientes
					where clienteId = cliId;
	end $$
delimiter ;
        
-- call sp_BuscarCliente(1);      
        
Delimiter $$
create procedure sp_editarCliente(in cliId int,in ni varchar(30), in nom varchar(30), in ape varchar (30), in tel varchar(15), in dir varchar (200))
	begin
		update Clientes
			set
				NIT = ni,
				nombre = nom,
                apellido = ape,
                telefono = tel,
                direccion = dir
					where clienteId = cliId;
	end $$
Delimiter ;

-- call sp_listarCliente();
-- ========================================= CRUD CARGOS ===========================================================================
-- =================================================================================================================================


Delimiter $$
create procedure sp_agregarCargo(in nomCarg varchar(30) , in descripCarg varchar (100)) 
	Begin
		insert into Cargo(nombreCargo,descripcionCargo) values
			(nomCarg,descripCarg);
	end$$
Delimiter ;



-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');
Delimiter $$
create procedure sp_listarCargos()
	begin
		select 
			Cargos.cargoId,
            Cargos.NombreCargo,
            Cargos.descripcionCargo
				from Cargos;
	end $$
Delimiter ;


Delimiter $$
create procedure sp_eliminarCargo(in carId int)
	begin 
		delete from Cargos
			where cargoId = carId;
	end $$
Delimiter ;


-- call sp_EliminarCargo(4);

Delimiter $$
create procedure sp_buscarCargo(in carId int)
	begin
		select 
			Cargos.cargoId,
            Cargos.nombreCargo,
            Cargos.descripcionCargo
				from Cargos
					where cargoId = carId;
	end $$
delimiter ;
-- call sp_BuscarCargo(1);  
Delimiter $$
create procedure sp_editarCargo(in carId int,in nomCarg varchar(30), in descripCarg varchar(100))
	begin
		update cargos
			set 
    		nombreCargo = nomCar,
				descripcionCargo = descCarg
					where cargoId = carId;
	end $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD EMPLEADOS============================================================
-- =========================================================================================================================


Delimiter $$
create procedure sp_agregarEmpleados (in emplId int (11), in nomEmple varchar(30),in apellEmple varchar(30), in suel decimal(10,2),in hrEnt time, hrSal time, in carId int(11), in encargId int(11))
	Begin
		insert into Empleados(nombreEmpleado, apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargoId)
			values(nomEmp,apeEmp,suel,horEnt,carId);
	end$$
delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_listarEmpleado()
	begin
		select
			Empleados.empleadoId,
            Empleados.nombreEmpleado,
            Empleados.apellidoEmpleado,
            Empleados.horaEntrada,
            Empleados.horaSalida,
            Empleados.cargoId
				from Empleados;
	end $$
Delimiter ;

-- Eliminar
Delimiter $$
create procedure sp_eliminarEmpleado(in empId int)
	begin 
		delete from Empleados
			where empleadoId = empId;
	end $$
Delimiter ;


-- call sp_EliminarCargo(4);

-- Buscar
Delimiter $$
create procedure sp_buscarEmpleado(in empId int)
	begin
		select
			Empleados.empleadoId,
            Empleados.nombreEmpleado,
            Empleados.apellidoEmpleado,
            Empleados.horaEntrada,
            Empleados.horaSalida,
            Empleados.cargoId
				from Empleados
                where empleadoId = empId;
	end $$
delimiter ;
 -- call sp_BuscarCargo(1);  
 
 
-- Editar
Delimiter $$
create procedure sp_editarEmpleado(in empId INT, IN nomEmp VARCHAR(30), IN apeEmp VARCHAR(30),IN suel DECIMAL(10,2),IN horEnt TIME, IN horSal TIME, IN carId INT)
	begin
		update Empleados
			set
				nombreEmpleado = nomEmp,
				apellidoEmpleado = apeEmp,
                clienteId = suel,
                horaEntrada = horEnt,
                horaSalida = horSal,
                cargoId = carId
					where empleadoId = empId;
	end $$
Delimiter ;
-- call sp_listarCargo();

-- ===============================================CRUD FACTURAS============================================================
-- =========================================================================================================================

delimiter $$
		create procedure sp_agregarFactura(IN fec date, in hor time,IN cliId int,IN empId int) 
	begin
		insert into Facturas(fecha, hora,numCliente,empleadoId)
			values(fec,hor,numCli,empId);
	end$$
delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_listarFactura()
	begin
		select
			Facturas.facturaId,
            Facturas.fecha,
            Facturas.hora,
            Facturas.clienteId,
            Facturas.empleadoId
				from Facturas;
	end $$
Delimiter ;

Delimiter $$
create procedure sp_eliminarFactura(in facId int)
	begin 
		delete from Facturas
			where facturaId = facId;
	end $$
Delimiter ;

-- call sp_EliminarCargo(4);
-- Buscar
Delimiter $$
create procedure sp_buscarFactura(in facId int)
	begin
		select
			Facturas.facturaId,
            Facturas.fecha,
            Facturas.hora,
            Facturas.clienteId,
            Facturas.empleadoId
				from Facturas
                where facturaId = facId;
	end $$
delimiter ;


-- call sp_BuscarCargo(1);  


    
Delimiter $$
create procedure sp_editarFactura(in facId int,in fec date, in hor time, in cliId INT, in empId INT)
	begin
		update Facturas
			set
				fecha = fec,
				hora = hor,
                clienteId = cliId,
                empleadoId = empId
					where facturaId = facId;
	end $$
Delimiter ;

-- call sp_listarCargo();


-- ===============================================CRUD TICKECTSOPORTE============================================================
-- ============================================================================================================================

Delimiter $$
create procedure sp_AgregarTicket(in descripTick varchar(250), in estat varchar(30), in cliId int, in facId int)
	Begin
		insert into TicketSoporte(descripcionTicket, estatus, clienteId, facturaId) values
			(descripTick, estat, cliId, facId);
	End$$
Delimiter ;


-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');



Delimiter $$
create procedure sp_ListarTicket()
	begin
		select
			TicketSoporte.ticketSoporteId,
			TicketSoporte.descripcionTicket,
			TicketSoporte.estatus,
            TicketSoporte.clienteId,
            TicketSoporte.facturaId
				from TicketSoporte;
	End $$
Delimiter ;


Delimiter $$
create procedure sp_EliminarTicket(in tickId int)
	begin 
		delete from TicketSoporte
			where ticketSoporteId = tickId;
	End $$
Delimiter ;

-- call sp_EliminarCargo(4);

Delimiter $$
create procedure sp_BuscarTicke(in tickId int)
	begin
		select
			TicketSoporte.ticketSoporteId,
            TicketSoporte.descripcionTicket,
            TicketSoporte.estatus,
            TicketSoporte.clienteId,
            TicketSoporte.facturaId
				from TicketSoporte
					where ticketSoporteId = tickId;
	End $$
delimiter ;
     
     
-- call sp_BuscarCargo(1);  

Delimiter $$
Create procedure sp_EditarTicketSoporte(in descripTick varchar(250), in estat varchar(30), in clienteId int, in facId int)
	Begin
		Update TicketSoporte
			set
				descripcionTicket = descTick,
                estatus = estat,
                clienteId = cliId,
                facturaId = facId
					where ticketSoporteId = tickId;
	End $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD DISTRIBUIDORES============================================================
-- ============================================================================================================================


Delimiter $$
create procedure sp_AgregarDistribuidor(in nombDis varchar(30), in dirDis varchar (200), in nitDis varchar(15), in telDis varchar(15), in we varchar(50))
	Begin
		insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) values
			(nombDis, dirDis, nitDis, telDis, we);
	end$$
Delimiter ;



-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');


Delimiter $$
create procedure sp_ListarDistribuidor()
	begin
		select
			Distribuidores.distribuidorId,
			Distribuidores.nombreDistribuidor,
			Distribuidores.direccionDistribuidor,
            Distribuidores.nitDistribuidor,
            Distribuidores.telefonoDistribuidor, 
            Distribuidores.web
				from Distribuidores;
	end $$
Delimiter ;



Delimiter $$
create procedure sp_EliminarDistribuidores(in disId int)
	begin 
		delete from Distribuidores
			where distribuidorId = disId;
	end $$
Delimiter ;

-- call sp_EliminarCargo(4);


Delimiter $$
create procedure sp_BuscarDistribuidores(in disId int)
	begin
		select
			Distribuidores.distribuidorId,
			Distribuidores.nombreDistribuidor,
            Distribuidores.direccionDistribuidor,
            Distribuidores.nitDistribuidor,
            Distribuidores.telefonoDistribuidor,
            Distribuidores.web
				from Distribuidores
					where distribuidorId = disId;
	end $$
delimiter ;
     
-- call sp_BuscarCargo(1);  

     
Delimiter $$
Create procedure sp_EditarDistribuidores(in nomDis varchar(30), in dirDis varchar (200), in nitDis varchar(15), in telDis varchar(15), in we varchar(50))
	Begin
		Update Distribuidores
			set
                nombreDistribuidor = nomDis,
                nitDistribuidor = nitDis,
                telefonoDistribuidor = telDis,
                web = we
					where distribuidorId = disId;
	end $$
Delimiter ;


-- call sp_listarCargo();

-- ===============================================CRUD CATEGORIAPRODUCTOS============================================================
-- ==================================================================================================================================
Delimiter $$
create procedure sp_AgregarCategoriaProducto(in nomCateg varchar(30), in descripCateg varchar (100))
	Begin
		insert into CategoriaProductos(nombreCategoria, descripcioCategoria) values
			(nomCateg, descripCateg);
	end$$
Delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_ListarCategoriaProducto()
	begin
		select
			CategoriaProductos.categoriaProductosId,
			CategoriaProductos.nombreCategoria,
            CategoriaProductos.descripcionCategoria
				from CategoriaProductos;
	end $$
Delimiter ;


Delimiter $$
create procedure sp_EliminarCategoriaProducto(in categId int)
	begin 
		delete from CategoriaProductos
			where categoriaProductosId = categId;
	end $$
Delimiter ;


-- call sp_EliminarCargo(4);


Delimiter $$
create procedure sp_BuscarCategoriaProducto(in categId int)
	begin
		select
			CategoriaProductos.categoriaProductosId,
            CategoriaProductos.nombreCategoria,
            CategoriaProductos.descripcionCategoria
				from CategoriaProductos
					where categoriaProductosId = categId;
	end $$
delimiter ;
     
-- call sp_BuscarCargo(1);  


Delimiter $$
Create procedure sp_EditarCategoriaProducto(in nomCateg varchar(30), in descripCateg varchar (100))
	Begin
		Update CategoriaProductos
			set
				nombreCategoria = nomCateg,
                descripcionCateg = descripCateg
					where categoriaProductosId = categId;
	end $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD PRODUCTOS============================================================
-- =========================================================================================================================

Delimiter 
create procedure sp_AgregarProducto(in nomProd varchar(50), in descripProduc varchar(100), in canStock int, in preciVUnit decimal(10,2), in preciVMay decimal(10,2), in preComp decimal(10,2), in imgProd BLOB, in disId int, in categId int)
	Begin
		insert into Producto(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId) values
			(nomProd, descripProduc, canStock, preciVUnit, preciVMay, preComp, imgProd, imgProd, disId, categId);
	End$$
Delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_ListarProducto()
	begin
		select
			Productos.productoId,
			Productos.nombreProducto,
			Productos.descripcionProducto,
            Productos.cantidadStock,
            Productos.precioVentaUnitario,
            Productos.precioVentaMayor,
            Productos.precioCompra,
            Productos.imagenProducto,
            Productos.distribuidorId,
            Productos.categoriaProductoId
				from Productos;
	End $$
Delimiter ;

-- Eliminar
Delimiter $$
create procedure sp_EliminarProducto(in prodId int)
	begin 
		delete from Productos
			where productoId = prodId;
	End $$
Delimiter ;

-- call sp_EliminarCategoriaProducto(4);

-- Buscar
Delimiter $$
create procedure sp_BuscarProducto(in prodId int)
	begin
		select
			Productos.productoId,
			Productos.nombreProducto,
			Productos.descripcionProducto,
            Productos.cantidadStock,
            Productos.precioVentaUnitario,
            Productos.precioVentaMayor,
            Productos.precioCompra,
            Productos.imagenProducto,
            Productos.distribuidorId,
            Productos.categoriaProductoId
					where productoId = prodId;
	End $$
delimiter ;
     
-- call sp_BuscarCargo(1);  


Delimiter $$
Create procedure sp_EditarProducto(in nomProd varchar(50), in descripProduc varchar(100), in canStock int, in preciVUnit decimal(10,2), in preciVMay decimal(10,2), in preComp decimal(10,2), in imgProd BLOB, in disId int, in categId int)
	Begin
		Update Productos
			set
				nombreProducto = nomProd,
                descripcionProducto = descripProduc,
                cantidadStock = cantStock,
                precioVenteUnitario = preciVUnit,
                precioVentaMayor = preciVMay,
                precioCompra = preComp, 
                imagenProducto = imgProd, 
                distribuidorId = disId,
				categoriaProductoId = categId
					where productoId = prodId;
	End $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD COMPRAS============================================================
-- ======================================================================================================================

Delimiter $$
create procedure sp_AgregarCompra(in fechComp date, in totaComp decimal(10,2))
	Begin
		insert into Compras(fechaCompra, totalCompra) values
			(fecComp, totComp);
	end$$
Delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');


Delimiter $$
create procedure sp_ListarCompra()
	begin
		select
			Compras.compraId,
			Compras.fechaCompra,
            Compras.totalCompra
				from Compras;
	end $$
Delimiter ;



Delimiter $$
create procedure sp_EliminarCompra(in compId int)
	begin 
		delete from Compras
			where compraId = compId;
	end $$
Delimiter ;

-- call sp_EliminarCategoriaProducto(4);

Delimiter $$
create procedure sp_BuscarCompras(in compId int)
	begin
		select
			Compras.compraId,
            Compras.fechaCompra,
            Compras.totalCompra
				from Compras
					where compraId = compId;
	end $$
delimiter ;
   
-- call sp_BuscarCargo(1);  

Delimiter $$
Create procedure sp_EditarCompras(in fechComp date, in totaComp decimal(10,2))
	Begin
		Update Compras
			set
				fechaCompra = fecCompra,
                totalCompra = totComp
					where compraId = compId;
	end $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD PROMOCIONES============================================================
-- ===========================================================================================================================


Delimiter $$
create procedure sp_AgregarPromociones(in promocionId int (11),in precioPromocio decimal(10,2), descripcionPromocion varchar(100),fechaInicio date, fechaFinalizacion date , productoId int (11))
	Begin
		insert into Compras(promocionId,precioPromocio,descripcionPromocion,fechaInicio,fechaFinalizacion,productoId) values
			(promId, preciProm,descripProm,fechaIni, fechaFinali,proId);
	end$$
Delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_ListarCPromociones()
	begin
		select
			Promociones.promocionId,
			Promociones.precioPromocio,
            Promociones.descripcionPromocion,
            Promociones.fechaInicio,
            Promociones.fechaFinalizacion,
            Promociones.productoId
				from Promociones;
	end $$
Delimiter ;


Delimiter $$
create procedure sp_EliminarPromociones(in promId int)
	begin 
		delete from Promociones
			where promocionId = promId;
	end $$
Delimiter ;


-- call sp_EliminarCategoriaProducto(4);

Delimiter $$
create procedure sp_BuscarPromociones(in promId int)
	begin
		select
			Promociones.promocionId,
			Promociones.precioPromocio,
            Promociones.descripcionPromocion,
            Promociones.fechaInicio,
            Promociones.fechaFinalizacion,
            Promociones.productoId
				from Promociones
					where promocionId = promId;
	end $$
delimiter ;

-- call sp_BuscarCargo(1);  
     
Delimiter $$
Create procedure sp_EditarPromociones(in precioPromocio decimal(10,2), descripcionPromocion varchar(100),fechaInicio date, fechaFinalizacion date)
	Begin
		Update Promociones
			set
		
				Promociones = preciProm,
				Promociones = descripProm,
				Promociones = fechaIni,
				Promociones =fechaFinali
			
					where promocionId = promId;
	end $$
Delimiter ;

-- call sp_listarCargo();

-- ===============================================CRUD DETALLECOMPRAS============================================================
-- ==============================================================================================================================

Delimiter $$
create procedure sp_AgregarDetalleCompras(in productoId int(11),in compraId int(11), in cantidadCompra int(11))
	Begin
		insert into DetalleCompras(productoId, compraId, cantidadCompra) values
			(proId, compId,cantCom);
	end$$
Delimiter ;

-- call sp_AgregarCliente('Marcos','Perez', '5748-6942','Ciudad', 'CF');

Delimiter $$
create procedure sp_ListarDetalleCompras()
	begin
		select
			DetalleCompras.proId,
			DetalleCompras.compId,
            DetalleCompras.cantCom
				from DetalleCompras;
	end $$
Delimiter ;


Delimiter $$
create procedure sp_EliminarDetalleCompras(in detCompId int)
	begin 
		delete from DetalleCompras
			where detalleCompraId = detCompId;
	end $$
Delimiter ;

-- call sp_EliminarCategoriaProducto(4);

Delimiter $$
create procedure sp_BuscarDetalleCompras(in detCompId int)
	begin
		select
			DetalleCompras.proId,
			DetalleCompras.compId,
            DetalleCompras.cantCom
				from DetalleCompras
					where detalleCompraId = detCompId;
	end $$
delimiter ;
     

-- call sp_BuscarCargo(1);  


Delimiter $$
Create procedure sp_EditarDetalleCompras(in productoId int(11),in compraId int(11), in cantidadCompra int(11))
	Begin
		Update DetalleCompras
			set
				DetalleCompras = proId,
				DetalleCompras = compId,
				DetalleCompras = cantCom
					where detalleCompraId = detCompId;
	end $$
Delimiter ;

-- call sp_listarCargo();


Delimiter $$
create procedure sp_ListarTicketSoporte()
	Begin
		Select TS.ticketSoporteId, TS.descripcionTicket , TS.estatus ,  
        CONCAT("Id: ",C.clienteId, " | ",C.nombre, " " , C.apellido) As cliente , TS.facturaId from TicketSoporte TS
        join Clientes C on TS.clienteId =  C.clienteId;
    End $$
Delimiter ;



Delimiter $$
create procedure sp_ListarFacturas()
	begin
		select F.facturaId, F.fecha, F.hora ,
        CONCAT("Id: ",C.clienteId, " | ",C.nombre, " " , C.apellido) As cliente ,
        CONCAT("Id: ",E.empleadoId, " | ",E.nombreEmpleado, " " , E.apellidoEmpleado) As empleado, F.total from Facturas F
        join Clientes C on F.clienteId =  C.clienteId
		join Empleados E on F.empleadoId = E.empleadoId;	
	End $$
Delimiter ;