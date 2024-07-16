CREATE TABLE USUARIO
(
    id_usuario NUMBER,
    nombre VARCHAR2(100),
    nombre_usu VARCHAR2(100),
    edad NUMBER,
    DIRECCION varchar2(100),
    puntos NUMBER,
    CONSTRAINT usu_id_PK PRIMARY KEY (id_usuario) 
);

CREATE TABLE LOGIN 
(
    id_usuario NUMBER,
    nombre_usu VARCHAR2(100),
    contrasenya VARCHAR2(200),
    CONSTRAINT log_nom_PK PRIMARY KEY (nombre_usu),
    CONSTRAINT log_id_FK FOREIGN KEY (id_usuario) REFERENCES USUARIO (id_usuario)
);

CREATE TABLE COMPANYIA
(
    id_companyia NUMBER,
    nombre VARCHAR2(100),
    pais VARCHAR2(100),
    cif NUMBER,
    direccion VARCHAR2(100),
    correo VARCHAR2(100),
    CONSTRAINT com_id_PK PRIMARY KEY (id_companyia)
);

CREATE TABLE JUEGO
(
    id_juego NUMBER,
    id_companyia NUMBER,
    nombre VARCHAR2(100),
    categoria VARCHAR2(100),
    tiempo_juego NUMBER,
    precio NUMBER,
    stock NUMBER,
    pegi NUMBER,
    CONSTRAINT jue_id_PK PRIMARY KEY (id_juego),
    CONSTRAINT jue_id_FK FOREIGN KEY (id_companyia) REFERENCES COMPANYIA (id_companyia)
);

select * from USUARIO;
SELECT * FROM LOGIN;
SELECT * FROM JUEGO;
SELECT * FROM VENTAS;

DELETE FROM USUARIO WHERE id_usuario = 2;

ALTER TABLE USUARIO ADD CONSTRAINT usu_nom_UK UNIQUE (nombre_usu);

DROP TABLE COMPANYIA;

ALTER TABLE JUEGO DROP CONSTRAINT jue_id_FK;

CREATE TABLE VENTAS 
(
    ID_VENTA NUMBER,
    ID_USUARIO NUMBER,
    NOMBRE_USUARIO VARCHAR2(100),
    ID_JUEGO NUMBER,
    NOMBRE_JUEGO VARCHAR2(100),
    FECHA DATE,
    PRECIO NUMBER,
    CONSTRAINT id_ven_PK PRIMARY KEY (ID_VENTA),
    CONSTRAINT idUsu_ven_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (id_usuario),
    CONSTRAINT idJue_ven_FK FOREIGN KEY (ID_JUEGO) REFERENCES JUEGO (id_juego)
);
ALTER TABLE VENTAS DROP CONSTRAINT idUsu_ven_FK;
ALTER TABLE VENTAS DROP CONSTRAINT idJue_ven_FK;

SELECT ID_USUARIO FROM USUARIO WHERE NOMBRE_USU = 'marher';
DELETE FROM VENTAS WHERE id_venta != 1;

CREATE TABLE IMAGEN
(
    id_imagen NUMBER,
    id_juego NUMBER,
    imagenes BLOB,
    videos BLOB,
    CONSTRAINT id_img_PK PRIMARY KEY (id_imagen),
    CONSTRAINT idJue_img_FK FOREIGN KEY (id_juego) REFERENCES JUEGO (id_juego)
);