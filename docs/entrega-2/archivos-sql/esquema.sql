CREATE TABLE acompaniantes (
    num_documento  VARCHAR2(255 BYTE) NOT NULL,
    tipo_documento VARCHAR2(255 BYTE) NOT NULL,
    reserva        NUMBER NOT NULL
);

ALTER TABLE acompaniantes
    ADD CONSTRAINT ck_tipo_doc_acom CHECK ( tipo_documento IN ( 'CC', 'CE', 'TI' ) );

ALTER TABLE acompaniantes ADD CONSTRAINT acompaniantes_pk PRIMARY KEY ( num_documento,
                                                                        reserva );

CREATE TABLE catalogo_productos (
    servicio_comercial NUMBER NOT NULL,
    producto           NUMBER NOT NULL
);

ALTER TABLE catalogo_productos ADD CONSTRAINT catalogo_productos_pk PRIMARY KEY ( servicio_comercial,
                                                                                  producto );

CREATE TABLE consumen (
    cliente        VARCHAR2(255 BYTE) NOT NULL,
    servicio       NUMBER NOT NULL,
    fecha          DATE NOT NULL,
    hora           VARCHAR2(255 BYTE) NOT NULL,
    cuenta_pagada  VARCHAR2(1 CHAR) NOT NULL,
    registrado_por VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE consumen
    ADD CONSTRAINT ck_cuenta_pagada CHECK ( cuenta_pagada IN ( 'N', 'Y' ) );

ALTER TABLE consumen
    ADD CONSTRAINT consumen_pk PRIMARY KEY ( cliente,
                                             servicio,
                                             fecha,
                                             hora );

CREATE TABLE dotaciones (
    id_dotacion NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE dotaciones ADD CONSTRAINT dotaciones_pk PRIMARY KEY ( id_dotacion );

CREATE TABLE dotaciones_incluidas (
    tipo_habitacion NUMBER NOT NULL,
    dotacion        NUMBER NOT NULL,
    cantidad        NUMBER NOT NULL
);

ALTER TABLE dotaciones_incluidas ADD CONSTRAINT dotacion_incluida_pk PRIMARY KEY ( dotacion,
                                                                                   tipo_habitacion );

CREATE TABLE habitaciones (
    num_habitacion NUMBER NOT NULL,
    costo_noche    NUMBER NOT NULL,
    tipo           NUMBER NOT NULL
);

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( num_habitacion );

CREATE TABLE planes_consumo (
    tipo                NUMBER NOT NULL,
    genera_descuento    VARCHAR2(1 CHAR) NOT NULL,
    tiempo_estadia      NUMBER NOT NULL,
    valor_descuento_ser NUMBER NOT NULL
);

ALTER TABLE planes_consumo
    ADD CONSTRAINT ck_genera_descuento CHECK ( genera_descuento IN ( 'N', 'Y' ) );

ALTER TABLE planes_consumo ADD CONSTRAINT planes_consumo_pk PRIMARY KEY ( tipo );

CREATE TABLE productos (
    id_producto NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE) NOT NULL,
    precio      REAL NOT NULL
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id_producto );

CREATE TABLE productos_incluidos (
    plan_consumo NUMBER NOT NULL,
    producto     NUMBER NOT NULL
);

ALTER TABLE productos_incluidos ADD CONSTRAINT productos_incluidos_pk PRIMARY KEY ( plan_consumo,
                                                                                    producto );

CREATE TABLE reservas (
    codigo_reserva  NUMBER NOT NULL,
    fecha_entrada   DATE NOT NULL,
    fecha_salida    DATE NOT NULL,
    num_huespedes   NUMBER NOT NULL,
    estado          VARCHAR2(255 BYTE) NOT NULL,
    cliente_reserva VARCHAR2(255 BYTE) NOT NULL,
    habitacion      NUMBER NOT NULL,
    plan_consumo    NUMBER NOT NULL
);

ALTER TABLE reservas
    ADD CONSTRAINT ck_estado CHECK ( estado IN ( 'Entro', 'Por entrar', 'Salio con deuda', 'Salio sin deuda' ) );

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
    tipo_cobro VARCHAR2(255 BYTE) NOT NULL,
    precio     NUMBER NOT NULL
);

ALTER TABLE servicios
    ADD CONSTRAINT ck_tipo_cobro CHECK ( tipo_cobro IN ( 'Alojamiento', 'Gratuito', 'Habitacion', 'Por dia' ) );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( tipo );

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

CREATE TABLE servicios_renta (
    tipo            NUMBER NOT NULL,
    costo_por_hora  NUMBER NOT NULL,
    costo_adicional NUMBER NOT NULL
);

ALTER TABLE servicios_renta
    ADD CONSTRAINT ck_tipo_ser_ren CHECK ( tipo IN ( 13, 14 ) );

ALTER TABLE servicios_renta ADD CONSTRAINT servicios_renta_pk PRIMARY KEY ( tipo );

CREATE TABLE tipos_habitacion (
    id_tipo   NUMBER NOT NULL,
    nombre    VARCHAR2(255 BYTE) NOT NULL,
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
    num_documento  VARCHAR2(255 BYTE) NOT NULL,
    tipo_documento VARCHAR2(255 BYTE) NOT NULL,
    nombre         VARCHAR2(255 BYTE) NOT NULL,
    correo         VARCHAR2(255 BYTE) NOT NULL,
    tipo           NUMBER NOT NULL
);

ALTER TABLE usuarios
    ADD CONSTRAINT ck_tipo_doc CHECK ( tipo_documento IN ( 'CC', 'CE' ) );

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( num_documento );

ALTER TABLE acompaniantes
    ADD CONSTRAINT acom_res_fk FOREIGN KEY ( reserva )
        REFERENCES reservas ( codigo_reserva );

ALTER TABLE catalogo_productos
    ADD CONSTRAINT catprod_prod_fk FOREIGN KEY ( producto )
        REFERENCES productos ( id_producto );

ALTER TABLE catalogo_productos
    ADD CONSTRAINT catprod_sercom_fk FOREIGN KEY ( servicio_comercial )
        REFERENCES servicios_comerciales ( tipo );

ALTER TABLE consumen
    ADD CONSTRAINT con_ser_fk FOREIGN KEY ( servicio )
        REFERENCES servicios ( tipo );

ALTER TABLE consumen
    ADD CONSTRAINT con_usu_fk FOREIGN KEY ( cliente )
        REFERENCES usuarios ( num_documento );

ALTER TABLE consumen
    ADD CONSTRAINT con_usu_fk_2 FOREIGN KEY ( registrado_por )
        REFERENCES usuarios ( num_documento );

ALTER TABLE dotaciones_incluidas
    ADD CONSTRAINT dotin_dot_fk FOREIGN KEY ( dotacion )
        REFERENCES dotaciones ( id_dotacion );

ALTER TABLE dotaciones_incluidas
    ADD CONSTRAINT dotin_tipohab_fk FOREIGN KEY ( tipo_habitacion )
        REFERENCES tipos_habitacion ( id_tipo );

ALTER TABLE habitaciones
    ADD CONSTRAINT hab_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_habitacion ( id_tipo );

ALTER TABLE planes_consumo
    ADD CONSTRAINT plancon_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_plan ( id_tipo );

ALTER TABLE productos_incluidos
    ADD CONSTRAINT prodinc_plancon_fk FOREIGN KEY ( plan_consumo )
        REFERENCES planes_consumo ( tipo );

ALTER TABLE productos_incluidos
    ADD CONSTRAINT prodinc_prod_fk FOREIGN KEY ( producto )
        REFERENCES productos ( id_producto );

ALTER TABLE reservas
    ADD CONSTRAINT res_hab_fk FOREIGN KEY ( habitacion )
        REFERENCES habitaciones ( num_habitacion );

ALTER TABLE reservas
    ADD CONSTRAINT res_plancon_fk FOREIGN KEY ( plan_consumo )
        REFERENCES planes_consumo ( tipo );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT res_ser_usu_fk FOREIGN KEY ( cliente )
        REFERENCES usuarios ( num_documento );

ALTER TABLE reservas
    ADD CONSTRAINT res_usu_fk FOREIGN KEY ( cliente_reserva )
        REFERENCES usuarios ( num_documento );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT resser_sercom_fk FOREIGN KEY ( servicio_comercial )
        REFERENCES servicios_comerciales ( tipo );

ALTER TABLE reservas_servicio
    ADD CONSTRAINT resser_servren_fk FOREIGN KEY ( servicio_renta )
        REFERENCES servicios_renta ( tipo );

ALTER TABLE servicios
    ADD CONSTRAINT ser_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_servicio ( id_tipo );

ALTER TABLE servicios_comerciales
    ADD CONSTRAINT sercom_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE servicios_renta
    ADD CONSTRAINT serren_ser_fk FOREIGN KEY ( tipo )
        REFERENCES servicios ( tipo );

ALTER TABLE usuarios
    ADD CONSTRAINT usu_tipos_fk FOREIGN KEY ( tipo )
        REFERENCES tipos_usuario ( id_tipo );

commit;