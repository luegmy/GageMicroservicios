INSERT INTO tb_tipoproducto(cod_tipo,descripcion) VALUES (1,'alumbrado');
INSERT INTO tb_tipoproducto(cod_tipo,descripcion) VALUES (2,'herramientas');
INSERT INTO tb_medida (cod_medida,descripcion,abreviatura) VALUES(58,'Unidad','NIU');

INSERT INTO tb_producto(cod_producto,descripcion,precio_compra,precio_venta,cod_tipo,cod_medida,fecha,existencia)
VALUES(1,'martillo',5.00,10.00,1,58,'2020-12-01',100);