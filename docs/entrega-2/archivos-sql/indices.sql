--RFC1 
CREATE INDEX idx_num_habitacion ON habitaciones (num_habitacion);
CREATE INDEX idx_habitacion_reservas ON reservas (habitacion);
CREATE INDEX idx_cliente_consumen ON consumen (cliente);

--RFC2
CREATE INDEX idx_servicio_fecha ON consumen (servicio, fecha);

--RFC3
CREATE INDEX idx_habitacion ON reservas (habitacion);
CREATE INDEX idx_fecha_entrada_salida ON reservas (fecha_entrada, fecha_salida);

--RFC4
CREATE INDEX idx_servicio_fecha ON consumen (servicio, fecha);
CREATE INDEX idx_precio ON servicios (precio);

--RFC5
CREATE INDEX idx_cliente ON consumen (cliente);
CREATE INDEX idx_fecha ON consumen (fecha);
CREATE INDEX idx_servicio ON consumen (servicio);

--RFC6
--6.1
CREATE INDEX idx_fecha_entrada_salida ON reservas (fecha_entrada, fecha_salida);
CREATE INDEX idx_fecha_reserva ON Fechas (fecha_reserva);
--6.2
CREATE INDEX idx_fecha ON consumen (fecha);
--6.3
CREATE INDEX idx_fecha_entrada_salida_diff ON reservas (fecha_entrada, fecha_salida, fecha_salida - fecha_entrada);

--RFC7
CREATE INDEX idx_fecha_entrada_salida ON reservas (fecha_entrada, fecha_salida);
CREATE INDEX idx_cliente ON consumen (cliente);
CREATE INDEX idx_tipo ON servicios (tipo);

--RFC8
CREATE INDEX idx_consumen_fecha_servicio ON consumen (fecha, servicio);

--RFC9
CREATE INDEX idx_servicio ON consumen (servicio);
CREATE INDEX idx_tipo ON usuarios (tipo);
CREATE INDEX idx_cliente ON consumen (cliente);
CREATE INDEX idx_fecha ON consumen (fecha);

--RFC10
CREATE INDEX idx_tipo ON usuarios (tipo);
CREATE INDEX idx_servicio_fecha ON consumen (servicio, fecha);

--RCFC11
--RFC11.1 
CREATE INDEX idx_fecha_salida ON reservas (fecha_salida);
CREATE INDEX idx_fecha_entrada ON reservas (fecha_entrada);
CREATE INDEX idx_habitacion ON reservas (habitacion);
CREATE INDEX idx_fecha ON consumen (fecha);
CREATE INDEX idx_servicio ON consumen (servicio);
--RFC11.2
CREATE INDEX idx_fecha_consumen ON consumen (fecha);
--RFC11.3
CREATE INDEX idx_habitacion_reservas ON reservas (habitacion);
--RFC11.4
CREATE INDEX idx_habitacion_reservas ON reservas (habitacion);
CREATE INDEX idx_fecha_entrada_reservas ON reservas (fecha_entrada);

--RFC12 
CREATE INDEX idx_fecha_reservas ON reservas (fecha_entrada, fecha_salida);
CREATE INDEX idx_fecha_consumen ON consumen (fecha);
CREATE INDEX idx_fecha_reservas_servicio ON reservas_servicio (dia_reserva);
CREATE INDEX idx_precio_servicios ON servicios (precio);
CREATE INDEX idx_hora_reservas_servicio ON reservas_servicio (hora_inicio, hora_fin);
CREATE INDEX idx_tipo_usuarios ON usuarios (tipo);

commit;


