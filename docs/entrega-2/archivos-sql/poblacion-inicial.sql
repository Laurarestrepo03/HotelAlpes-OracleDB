--Dotaciones
create sequence id_dotacion
start with 1
increment by 1;

insert into dotaciones (id_dotacion, nombre) values (id_dotacion.nextval, 'Cama');
insert into dotaciones (id_dotacion, nombre) values (id_dotacion.nextval, 'Comedor');
insert into dotaciones (id_dotacion, nombre) values (id_dotacion.nextval, 'Cocina');
insert into dotaciones (id_dotacion, nombre) values (id_dotacion.nextval, 'Jacuzzi');

--Tipos habitacion
create sequence id_tipo_habitacion
start with 1
increment by 1;

insert into tipos_habitacion (id_tipo, nombre, capacidad) values (id_tipo_habitacion.nextval, 'Familiar', 6);
insert into tipos_habitacion (id_tipo, nombre, capacidad) values (id_tipo_habitacion.nextval, 'Doble jacuzzi', 3);
insert into tipos_habitacion (id_tipo, nombre, capacidad) values (id_tipo_habitacion.nextval, 'Doble', 3);
insert into tipos_habitacion (id_tipo, nombre, capacidad) values (id_tipo_habitacion.nextval, 'Suite', 7);
insert into tipos_habitacion (id_tipo, nombre, capacidad) values (id_tipo_habitacion.nextval, 'Suite presidencial', 8);


--Dotaciones incluidas
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (1, 1, 4);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (1, 2, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (1, 3, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (2, 1, 2);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (2, 2, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (2, 3, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (3, 1, 3);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (3, 2, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (3, 3, 1);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (5, 1, 2);
insert into dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) values (5, 4, 1);


--Habitaciones
insert into habitaciones (num_habitacion, costo_noche, tipo) values (101, 500, 1);
insert into habitaciones (num_habitacion, costo_noche, tipo) values (102, 250, 5);
insert into habitaciones (num_habitacion, costo_noche, tipo) values (103, 300, 3);


--Productos
create sequence id_producto
start with 1
increment by 1;

insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Soda', 2000);
insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Bloody Mary', 12000);
insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Poker', 4000);
insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Cerdo asado', 24000);
insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Cerdo frito', 20000);
insert into productos (id_producto, nombre, precio) values (id_producto.nextval,'Agua refrescante', 1000);


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


--Servicios
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (1, 9, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (2, 10, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (3, 7, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (4, 7, 'Habitacion', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (5, 18, 'Habitacion', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (6, 9, 'Habitacion', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (7, 21, 'Habitacion', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (8, 25, 'Habitacion', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (9, 23, 'Habitacion', 150000);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (10, 7, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (11, 7, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (12, 15, 'Alojamiento', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (13, 6, 'Por dia', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (14, 50, 'Por dia', 10);
insert into servicios (tipo, capacidad, tipo_cobro, precio) values (15, 6, 'Gratuito', 10);


--Servicios comerciales
insert into servicios_comerciales (tipo, estilo, requiere_reserva) values (4, 'Inglés', 'N');
insert into servicios_comerciales (tipo, estilo, requiere_reserva) values (5, 'Gourmet', 'N');


--Servicios renta
insert into servicios_renta (tipo, costo_por_hora, costo_adicional) values (13, 100, 10);
insert into servicios_renta (tipo, costo_por_hora, costo_adicional) values (14, 50, 0);


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
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1009', 'CC', 'Valentina', 'v.arenas@uniandes.edu.co', 1);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1006', 'CC', 'Laura', 'l.restrepop@uniandes.edu.co', 1);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1007', 'CC', 'Catalina', 'c.espitia@uniandes.edu.co', 1);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1008', 'CC', 'Sergio', 's.cifuentes@uniandes.edu.co', 1);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1012', 'CC', 'Lindsay', 'l.pinto@uniandes.edu.co', 2);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1013', 'CC', 'Yei', 'y.zhang@uniandes.edu.co', 2);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1014', 'CC', 'Leidy', 'l.lozano@uniandes.edu.co', 3);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1015', 'CC', 'Tatiana', 't.vera@uniandes.edu.co', 3);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1016', 'CC', 'Juan', 'j.fajardo@uniandes.edu.co', 3);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1017', 'CC', 'Karol', 'k.martinez@uniandes.edu.co', 3);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1011', 'CC', 'Lucia', 'l.posada@uniandes.edu.co', 4);
insert into usuarios (num_documento, tipo_documento, nombre, correo, tipo) values ('1010', 'CC', 'Juan', 'j.yepes@uniandes.edu.co', 5);


--Consumen
insert into consumen (cliente, servicio, fecha, hora, cuenta_pagada, registrado_por) values ('1006', 2, '01/11/22', '16:00:00', 'Y', '1016');


--Reservas servicio
create sequence id_reserva_servicio
start with 1
increment by 1;

insert into reservas_servicio (id_reserva_servicio, dia_reserva, hora_inicio, hora_fin, servicio_comercial, servicio_renta, cliente) values (id_reserva_servicio.nextval, '31/10/22', '15:00:00', '16:00:00', null, 14, '1006');


--Tipos plan
create sequence id_tipo_plan
start with 1
increment by 1;

insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan principal', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan secundario', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan familiar', 'Y');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan todo incluido', 'N');
insert into tipos_plan(id_tipo, nombre, costo_fijo) values (id_tipo_plan.nextval, 'Plan larga estadia', 'Y');


--Planes consumo
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) values (1, 'Y', 3, 40);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) values (2,'N', 1, 20);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) values (3,'Y', 3, 0);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) values (4, 'N', 5, 0);
insert into planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) values (5, 'Y', 7, 15);


--Reservas
create sequence codigo_reserva
start with 1
increment by 1;

insert into reservas (codigo_reserva, fecha_entrada, fecha_salida, num_huespedes, estado, cliente_reserva, habitacion, plan_consumo) values 
(codigo_reserva.nextval, '28/10/22', '28/10/23', 10, 'Entro', '1006', 101, 1);


commit;
