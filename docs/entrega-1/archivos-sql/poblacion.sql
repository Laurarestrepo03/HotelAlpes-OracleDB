--Consumen
insert into consumen (cliente, servicio, fecha, hora, cuenta_pagada) values ('1006', 2, '25/01/01', '16:00:00', 'N');


--Habitaciones
insert into habitaciones (num_habitacion, costo_noche, tipo, hotel) values (101, 500, 1, '732757320-8' );
insert into habitaciones (num_habitacion, costo_noche, tipo, hotel) values (102, 250, 5, '732757320-8');
insert into habitaciones (num_habitacion, costo_noche, tipo, hotel) values (103, 300, 3, '732757320-8' );


--Hoteles
insert into hoteles (nit, nombre) values ('732757320-8', 'Sunset Resort');
insert into hoteles (nit, nombre) values ('816287299-4', 'Grand Hotel');


--Planes consumo
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, descuento_ser_com, valor_descuento_ser) values (1, 'Y', 3, 'Y', 0.4);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, descuento_ser_com, valor_descuento_ser) values (2,'N', 1, 'Y', 0.2);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, descuento_ser_com, valor_descuento_ser) values (3,'Y', 3, 'N', 0);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, descuento_ser_com, valor_descuento_ser) values (4, 'N', 5,'N', 0);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, descuento_ser_com, valor_descuento_ser) values (5, 'Y', 7, 'Y', 0.15);


--Planes disponibles
insert into planes_disponibles(hotel, plan_consumo) values ('732757320-8', 1);
insert into planes_disponibles(hotel, plan_consumo) values ('732757320-8', 2);
insert into planes_disponibles(hotel, plan_consumo) values ('732757320-8', 3);
insert into planes_disponibles(hotel, plan_consumo) values ('732757320-8', 4);
insert into planes_disponibles(hotel, plan_consumo) values ('732757320-8', 5);


--Productos
create sequence id_producto
start with 1
increment by 1;

insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Soda', 2000, 'Y', 4);
insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Bloody Mary', 12000, 'N', 4);
insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Poker', 4000, 'Y', 4);
insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Cerdo asado', 24000, 'Y', 4);
insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Cerdo frito', 20000, 'N', 4);
insert into productos (id_producto, nombre, precio, incluido_plan, servicio) values (id_producto.nextval,'Agua refrescante', 1000, 'Y', 4);


--Reservas
create sequence codigo_reserva
start with 1
increment by 1;

insert into reservas (codigo_reserva, fecha_entrada, fecha_salida, num_huespedes, entro, salio, paz_y_salvo, cliente_reserva, hotel, habitacion, plan_consumo) values 
(codigo_reserva.nextval, '01/01/01', '01/02/01', 10, 'Y', 'N', 'N', '1006', '732757320-8', 101, 1);


--Reservas servicio
create sequence id_reserva_servicio
start with 1
increment by 1;

insert into reservas_servicio (id_reserva_servicio, dia_reserva, hora_inicio, hora_fin, servicio_comercial, servicio_renta, cliente) values (id_reserva_servicio.nextval, '02/01/01', '15:00:00', '16:00:00', null, 14, '1006');


--Servicios
insert into servicios (tipo, capacidad, tipo_cobro) values (1, 9, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (2, 10, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (3, 7, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (4, 7, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (5, 18, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (6, 9, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (7, 21, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (8, 25, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (9, 23, 'Habitacion');
insert into servicios (tipo, capacidad, tipo_cobro) values (10, 7, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (11, 7, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (12, 15, 'Alojamiento');
insert into servicios (tipo, capacidad, tipo_cobro) values (13, 6, 'Por dia');
insert into servicios (tipo, capacidad, tipo_cobro) values (14, 50, 'Por dia');
insert into servicios (tipo, capacidad, tipo_cobro) values (15, 6, 'Gratuito');


--Servicios comerciales
insert into serviciones_comerciales (tipo, estilo, requiere_reserva) values (4, 'Inglés', 'N');
insert into serviciones_comerciales (tipo, estilo, requiere_reserva) values (5, 'Gourmet', 'N');

--Servicios renta
insert into servicios_renta (tipo, costo_por_hora, costo_adicional, equipos) values (13, 100, 10, 'Y');
insert into servicios_renta (tipo, costo_por_hora, costo_adicional, equipos) values (14, 50, 0, 'N');


--Tipos habitacion
create sequence id_tipo_habitacion
start with 1
increment by 1;

insert into tipos_habitacion (id_tipo, nombre, dotacion, capacidad) values (id_tipo_habitacion.nextval, 'Familiar', 'Comedor y cocina', 6);
insert into tipos_habitacion (id_tipo, nombre, dotacion, capacidad) values (id_tipo_habitacion.nextval, 'Doble jacuzzi', 'Jacuzzi', 3);
insert into tipos_habitacion (id_tipo, nombre, dotacion, capacidad) values (id_tipo_habitacion.nextval, 'Doble', 'Sin jacuzzi', 3);
insert into tipos_habitacion (id_tipo, nombre, dotacion, capacidad) values (id_tipo_habitacion.nextval, 'Suite', 'Comedor y cocina', 7);
insert into tipos_habitacion (id_tipo, nombre, dotacion, capacidad) values (id_tipo_habitacion.nextval, 'Suite presidencial', 'Comedor y cocina', 8);


--Tipos plan
create sequence id_tipo_plan
start with 1
increment by 1;

insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan principal', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan secundario', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan familiar', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan todo incluido', 'N');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan larga estadia', 'Y');


--Tipos servicio
create sequence id_tipo_servicio
start with 1
increment by 1;

insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Gimnasio');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Piscina');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Internet');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Bar');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Restaurante');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Supermercado');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Tienda joyeria');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Tienda ropa');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Spa');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Lavado');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Planchado');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Embolada');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Salon de reunion');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Salon de conferencia');
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Utensilios');


--Tipos usuario
create sequence id_tipo_usuario
start with 1
increment by 1;

insert into tipos_usuario (id_tipo, nombre) values (id_tipo_usuario.nextval, 'Cliente');
insert into tipos_usuario (id_tipo, nombre) values (id_tipo_usuario.nextval, 'Recepcionista');
insert into tipos_usuario (id_tipo, nombre) values (id_tipo_usuario.nextval, 'Empleado');
insert into tipos_usuario (id_tipo, nombre) values (id_tipo_usuario.nextval, 'Administrador');
insert into tipos_usuario (id_tipo, nombre) values (id_tipo_usuario.nextval, 'Gerente');

--Usuarios
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1009', 'CC', 'Valentina', 'v.arenas@uniandes.edu.co', 1, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1006', 'CC', 'Laura', 'l.restrepop@uniandes.edu.co', 1, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1007', 'CC', 'Catalina', 'c.espitia@uniandes.edu.co', 1, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1008', 'CC', 'Sergio', 's.cifuentes@uniandes.edu.co', 1, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1012', 'CC', 'Lindsay', 'l.pinto@uniandes.edu.co', 2, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1013', 'CC', 'Yei', 'y.zhang@uniandes.edu.co', 2, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1014', 'CC', 'Leidy', 'l.lozano@uniandes.edu.co', 3, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1015', 'CC', 'Tatiana', 't.vera@uniandes.edu.co', 3, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1016', 'CC', 'Juan', 'j.fajardo@uniandes.edu.co', 3, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1017', 'CC', 'Karol', 'k.martinez@uniandes.edu.co', 3, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1011', 'CC', 'Lucia', 'l.posada@uniandes.edu.co', 4, '732757320-8');
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo, hotel ) values ('1010', 'CC', 'Juan', 'j.yepes@uniandes.edu.co', 5, '732757320-8');


commit;
