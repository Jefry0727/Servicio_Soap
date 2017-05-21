CREATE TABLE cliente
  (
    id   SERIAL NOT NULL ,
    cedula VARCHAR (11) NOT NULL ,
    id_crm VARCHAR (45) NOT NULL
  ) ;
ALTER TABLE cliente ADD CONSTRAINT cliente_PK PRIMARY KEY ( id ) ;
ALTER TABLE cliente ADD CONSTRAINT cliente__UN UNIQUE ( cedula ) ;
CREATE TABLE compra
  (
    id       SERIAL NOT NULL ,
    fecha_compra DATE NOT NULL ,
    valor_total  FLOAT NOT NULL ,
    cliente_id   INTEGER NOT NULL,
	numero_compra  VARCHAR (20) NOT NULL
  ) ;
ALTER TABLE compra ADD CONSTRAINT compra_PK PRIMARY KEY ( id ) ;
CREATE TABLE item_compra
  (
    id        SERIAL  NOT NULL ,
    id_producto    VARCHAR (45) NOT NULL ,
    cantidad       INTEGER NOT NULL ,
    valor_producto FLOAT ,
    compra_id      INTEGER NOT NULL
  ) ;
ALTER TABLE item_compra ADD CONSTRAINT item_compra_PK PRIMARY KEY ( id ) ;
ALTER TABLE compra ADD CONSTRAINT compra_cliente_FK FOREIGN KEY ( cliente_id ) REFERENCES cliente ( id ) ;
ALTER TABLE item_compra ADD CONSTRAINT item_compra_compra_FK FOREIGN KEY ( compra_id ) REFERENCES compra ( id ) ;


-- SECUENCIAS

-- disparador para crear cliente

  CREATE SEQUENCE ClienteSequencia;
  
  CREATE OR REPLACE FUNCTION ClienteFuncion()
  RETURNS "trigger" AS
$BODY$
    BEGIN
    New.id:=nextval('ClienteSequencia');
    Return NEW;
    END;
 $BODY$
  LANGUAGE 'plpgsql' VOLATILE;

  CREATE TRIGGER ClienteTrigger
  BEFORE INSERT
  ON cliente
  FOR EACH ROW
  EXECUTE PROCEDURE ClienteFuncion();
  
  
 -- disparador para compra 
  
  CREATE SEQUENCE CompraSequencia;
  
  CREATE OR REPLACE FUNCTION CompraFuncion()
  RETURNS "trigger" AS
$BODY$
    BEGIN
    New.id:=nextval('CompraSequencia');
    Return NEW;
    END;
 $BODY$
  LANGUAGE 'plpgsql' VOLATILE;

  CREATE TRIGGER CompraTrigger
  BEFORE INSERT
  ON compra
  FOR EACH ROW
  EXECUTE PROCEDURE CompraFuncion();
  
  
  -- disparador itemcompra
   CREATE SEQUENCE ItemSequencia;
  
  CREATE OR REPLACE FUNCTION ItemFuncion()
  RETURNS "trigger" AS
$BODY$
    BEGIN
    New.id:=nextval('ItemSequencia');
    Return NEW;
    END;
 $BODY$
  LANGUAGE 'plpgsql' VOLATILE;

  CREATE TRIGGER CompraTrigger
  BEFORE INSERT
  ON item_compra
  FOR EACH ROW
  EXECUTE PROCEDURE ItemFuncion();
  