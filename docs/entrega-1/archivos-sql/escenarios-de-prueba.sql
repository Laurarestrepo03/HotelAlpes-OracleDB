--RF1
select * from tipos_usuario;

--RF2
select * from usuarios;

--RF3
select * from tipos_habitacion;

--RF4
select * from habitaciones;

--RF5
insert into tipos_servicio (id_tipo, nombre) values (id_tipo_servicio.nextval, 'Nombre')
select * from servicios;

--RF6
insert into tipos_plan (id_tipo, nombre) values (id_tipo_plan.nextval, 'Nombre')
select * from planes_consumo;

--RF7
select * from reservas;

--RF8
select * from reservas_servicio;

--RF9
select * from reservas;

--RF10
select * from consumen;

--RF11
select * from reservas;

