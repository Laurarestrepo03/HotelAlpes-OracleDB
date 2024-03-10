CREATE TABLE acompaniantes (
    num_documento  VARCHAR2(255 BYTE) NOT NULL,
    tipo_documento VARCHAR2(255 BYTE) NOT NULL,
    reserva        NUMBER NOT NULL
);

ALTER TABLE acompaniantes
    ADD CONSTRAINT ck_tipo_doc_acom CHECK ( tipo_documento IN ( 'CC', 'CE', 'TI' ) );

ALTER TABLE acompaniantes ADD CONSTRAINT acompaniantes_pk PRIMARY KEY ( num_documento,
                                                                        reserva );

CREATE TABLE consumen (
    cliente       VARCHAR2(255 BYTE) NOT NULL,
    servicio      NUMBER NOT NULL,
    fecha         DATE NOT NULL,
    hora          VARCHAR2(255 BYTE) NOT NULL,
    cuenta_pagada VARCHAR2(1 CHAR) NOT NULL
);

ALTER TABLE consumen
    ADD CONSTRAINT ck_cuenta_pagada CHECK ( cuenta_pagada IN ( 'N', 'Y' ) );

ALTER TABLE consumen
    ADD CONSTRAINT consumen_pk PRIMARY KEY ( cliente,
                                             servicio,
                                             fecha,
                                             hora );

CREATE TABLE habitaciones (
    num_habitacion NUMBER NOT NULL,
    costo_noche    NUMBER NOT NULL,
    tipo           NUMBER NOT NULL,
    hotel          VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( num_habitacion );

CREATE TABLE hoteles (
    nit    VARCHAR2(255 BYTE) NOT NULL,
    nombre VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE hoteles ADD CONSTRAINT hoteles_pk PRIMARY KEY ( nit );

CREATE TABLE planes_consumo (
    tipo                NUMBER NOT NULL,
    genera_descuento    VARCHAR2(1 CHAR) NOT NULL,
    tiempo_estadia      NUMBER NOT NULL,
    descuento_ser_com   VARCHAR2(1 CHAR) NOT NULL,
    valor_descuento_ser NUMBER NOT NULL
);

ALTER TABLE planes_consumo
    ADD CONSTRAINT ck_genera_descuento CHECK ( genera_descuento IN ( 'N', 'Y' ) );

ALTER TABLE planes_consumo
    ADD CONSTRAINT ck_des_ser_com CHECK ( descuento_ser_com IN ( 'N', 'Y' ) );

ALTER TABLE planes_consumo ADD CONSTRAINT planes_consumo_pk PRIMARY KEY ( tipo );

CREATE TABLE planes_disponibles (
    hotel        VARCHAR2(255 BYTE) NOT NULL,
    plan_consumo NUMBER NOT NULL
);

ALTER TABLE planes_disponibles ADD CONSTRAINT planes_disponibles_pk PRIMARY KEY ( hotel,
                                                                                  plan_consumo );

CREATE TABLE productos (
    id_producto   NUMBER NOT NULL,
    nombre        VARCHAR2(255 BYTE) NOT NULL,
    precio        REAL NOT NULL,
    incluido_plan VARCHAR2(1 CHAR) NOT NULL,
    servicio      NUMBER NOT NULL
);

ALTER TABLE productos
    ADD CONSTRAINT ck_incluido_plan CHECK ( incluido_plan IN ( 'N', 'Y' ) );

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id_producto );

CREATE TABLE reservas (
    codigo_reserva  NUMBER NOT NULL,
    fecha_entrada   DATE NOT NULL,
    fecha_salida    DATE NOT NULL,
    num_huespedes   NUMBER NOT NULL,
    entro           VARCHAR2(1 CHAR) NOT NULL,
    salio           VARCHAR2(1 CHAR) NOT NULL,
    paz_y_salvo     VARCHAR2(1 CHAR) NOT NULL,
    cliente_reserva VARCHAR2(255 BYTE) NOT NULL,
    hotel           VARCHAR2(255 BYTE) NOT NULL,
    habitacion      NUMBER NOT NULL,
    plan_consumo    NUMBER NOT NULL
);

ALTER TABLE reservas
    ADD CONSTRAINT ck_entro CHECK ( entro IN ( 'N', 'Y' ) );

ALTER TABLE reservas
    ADD CONSTRAINT ck_salio CHECK ( salio IN ( 'N', 'Y' ) );

ALTER TABLE reservas
    ADD CONSTRAINT ck_paz_y_salvo CHECK ( paz_y_salvo IN ( 'N', 'Y' ) );

ALTER TABLE reservas ADD CONSTRAINT reservas_pk PRIMARY KEY ( codigo_reserva );

CREATE TABLE reservas_servicio (
    id_reserva_servicio NUMBER NOT NULL,
    dia_reserva         DATE NOT NULL,
    hora_inicio         VARCHAR2(255 BYTE) NOT NULL,
    hora_fin            VARCHAR2(255 BYTE) NOT NULL,
    servicio_comercial  NUMBER,
    servicio_renta      NUMBER,
    cliente             VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE reservas_servicio ADD CONSTRAINT reservas_servicio_pk PRIMARY KEY ( id_reserva_servicio );

CREATE TABLE servicios (
    tipo       NUMBER NOT NULL,
    capacidad  NUMBER NOT NULL,
    tipo_cobro VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE servicios
    ADD CONSTRAINT ck_tipo_cobro CHECK ( tipo_cobro IN ( 'Alojamiento', 'Gratuito', 'Habitacion', 'Por dia' ) );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( tipo );

CREATE TABLE servicios_bienestar (
    tipo    NUMBER NOT NULL,
    horario VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE servicios_bienestar
    ADD CONSTRAINT ck_tipo_ser_bien CHECK ( tipo IN ( 1, 2, 3 ) );

ALTER TABLE servicios_bienestar ADD CONSTRAINT servicios_bienestar_pk PRIMARY KEY ( tipo );

CREATE TABLE servicios_comerciales (
    tipo             NUMBER NOT NULL,
    estilo           VARCHAR2(255 BYTE) NOT NULL,
    requiere_reserva VARCHAR2(1 CHAR) NOT NULL
);

ALTER TABLE servicios_comerciales
    ADD CONSTRAINT ck_tipo_ser_com CHECK ( tipo IN ( 4, 5, 6, 7, 8,
                                                     9 ) );

ALTER TABLE servicios_comerciales
    ADD CONSTRAINT ck_requiere_reserva CHECK ( requiere_reserva IN ( 'N', 'Y' ) );

ALTER TABLE servicios_comerciales ADD CONSTRAINT servicios_comerciales_pk PRIMARY KEY ( tipo );

CREATE TABLE servicios_limpieza (
    tipo             NUMBER NOT NULL,
    cantidad_prendas NUMBER NOT NULL
);

ALTER TABLE servicios_limpieza
    ADD CONSTRAINT ck_tipo_ser_lim CHECK ( tipo IN ( 10, 11, 12 ) );

ALTER TABLE servicios_limpieza ADD CONSTRAINT servicios_limpieza_pk PRIMARY KEY ( tipo );

CREATE TABLE servicios_ofrecidos (
    hotel    VARCHAR2(255 BYTE) NOT NULL,
    servicio NUMBER NOT NULL
);

ALTER TABLE servicios_ofrecidos ADD CONSTRAINT servicios_ofrecidos_pk PRIMARY KEY ( hotel,
                                                                                    servicio );

CREATE TABLE servicios_prestamo (
    tipo                NUMBER NOT NULL,
    elemento_prestado   VARCHAR2(255 BYTE) NOT NULL,
    devolucion_correcta VARCHAR2(1 CHAR) NOT NULL
);

ALTER TABLE servicios_prestamo
    ADD CONSTRAINT ck_tipo_ser_pre CHECK ( tipo IN ( 15 ) );

ALTER TABLE servicios_prestamo
    ADD CONSTRAINT ck_dev_cor CHECK ( devolucion_correcta IN ( 'N', 'Y' ) );

ALTER TABLE servicios_prestamo ADD CONSTRAINT servicios_prestamo_pk PRIMARY KEY ( tipo,
                                                                                  elemento_prestado );

CREATE TABLE servicios_renta (
    tipo            NUMBER NOT NULL,
    costo_por_hora  NUMBER NOT NULL,
    costo_adicional NUMBER NOT NULL,
    equipos         VARCHAR2(1 CHAR) NOT NULL
);

ALTER TABLE servicios_renta
    ADD CONSTRAINT ck_tipo_ser_ren CHECK ( tipo IN ( 13, 14 ) );

ALTER TABLE servicios_renta
    ADD CONSTRAINT ck_equipos CHECK ( equipos IN ( 'N', 'Y' ) );

ALTER TABLE servicios_renta ADD CONSTRAINT servicios_renta_pk PRIMARY KEY ( tipo );

CREATE TABLE tipos_habitacion (
    id_tipo   NUMBER NOT NULL,
    nombre    VARCHAR2(255 BYTE) NOT NULL,
    dotacion  VARCHAR2(255 BYTE) NOT NULL,
    capacidad NUMBER NOT NULL
);

ALTER TABLE tipos_habitacion ADD CONSTRAINT tipos_habitacion_pk PRIMARY KEY ( id_tipo );

CREATE TABLE tipos_plan (
    id_tipo    NUMBER NOT NULL,
    nombre     VARCHAR2(255 BYTE) NOT NULL,
    costo_fijo VARCHAR2(1 CHAR) NOT NULL
);

ALTER TABLE tipos_plan
    ADD CONSTRAINT ck_costo_fijo CHECK ( costo_fijo IN ( 'N', 'Y' ) );

ALTER TABLE tipos_plan ADD CONSTRAINT tipos_plan_pk PRIMARY KEY ( id_tipo );

CREATE TABLE tipos_servicio (
    id_tipo NUMBER NOT NULL,
    nombre  VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE tipos_servicio ADD CONSTRAINT tipos_servicio_pk PRIMARY KEY ( id_tipo );

CREATE TABLE tipos_usuario (
    id_tipo NUMBER NOT NULL,
    nombre  VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE tipos_usuario ADD CONSTRAINT tipos_usuario_pk PRIMARY KEY ( id_tipo );

CREATE TABLE usuarios (
    num_documento VARCHAR2(255 BYTE) NOT NULL,
    nombre        VARCHAR2(255 BYTE) NOT NULL,
    correo        VARCHAR2(255 BYTE) NOT NULL,
    tipo          NUMBER NOT NULL,
    hotel         VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( num_documento );

ALTER TABLE acompaniantes
    ADD CONSTRAINT acom_res_fk FOREIGN KEY ( reserva )
        REFERENCES reservas ( codigo_reserva );

ALTER TABLE consumen
    ADD CONSTRAINT con_ser_fk FOREIGN KEY ( servicio )
        REFERENCES servicios ( tipo );

ALTER TABLE consumen
    ADD CONSTRAINT con_usu_fk FOREIGN KEY ( cliente )
        REFERENCES usuarios ( num_documento );

ALTER TABLE habitaciones
    ADD CONSTRAINT hab_hot_fk FOREIGN KEY ( hotel )
        REFERENCES hoteles ( nit );

ALTER TABLE habitaciones
    ADD CONSTRAINT hab_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_habitacion ( id_tipo );

ALTER TABLE planes_disponibles
    ADD CONSTRAINT plan_dis_hot_fk FOREIGN KEY ( hotel )
        REFERENCES hoteles ( nit );

ALTER TABLE planes_consumo
    ADD CONSTRAINT plancon_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_plan ( id_tipo );

ALTER TABLE planes_disponibles
    ADD CONSTRAINT plandis_plancon_fk FOREIGN KEY ( plan_consumo )
        REFERENCES planes_consumo ( tipo );

ALTER TABLE productos
    ADD CONSTRAINT prod_sercom_fk FOREIGN KEY ( servicio )
        REFERENCES servicios_comerciales ( tipo );

ALTER TABLE reservas
    ADD CONSTRAINT res_hab_fk FOREIGN KEY ( habitacion )
        REFERENCES habitaciones ( num_habitacion );

ALTER TABLE reservas
    ADD CONSTRAINT res_hot_fk FOREIGN KEY ( hotel )
        REFERENCES hoteles ( nit );

ALTER TABLE reservas
    ADD CONSTRAINT res_plancon_fk FOREIGN KEY ( plan_consumo )
        REFERENCES planes_consumo ( tipo );

ALTER TABLE reservas
    ADD CONSTRAINT res_usu_fk FOREIGN KEY ( cliente_reserva )
        REFERENCES usuarios ( num_documento );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT resser_sercom_fk FOREIGN KEY ( servicio_comercial )
        REFERENCES servicios_comerciales ( tipo );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT resser_servren_fk FOREIGN KEY ( servicio_renta )
        REFERENCES servicios_renta ( tipo );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT res_ser_usu_fk FOREIGN KEY ( cliente )
        REFERENCES usuarios ( num_documento );

ALTER TABLE servicios_ofrecidos
    ADD CONSTRAINT ser_ofre_ser_fk FOREIGN KEY ( servicio )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios
    ADD CONSTRAINT ser_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_servicio ( id_tipo );

ALTER TABLE servicios_bienestar
    ADD CONSTRAINT serbien_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios_comerciales
    ADD CONSTRAINT sercom_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios_limpieza
    ADD CONSTRAINT serlim_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios_ofrecidos
    ADD CONSTRAINT serofre_hot_fk FOREIGN KEY ( hotel )
        REFERENCES hoteles ( nit );

ALTER TABLE servicios_prestamo
    ADD CONSTRAINT serpre_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios_renta
    ADD CONSTRAINT serren_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE usuarios
    ADD CONSTRAINT usu_hot_fk FOREIGN KEY ( hotel )
        REFERENCES hoteles ( nit );

ALTER TABLE usuarios
    ADD CONSTRAINT usu_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_usuario ( id_tipo );

commit;