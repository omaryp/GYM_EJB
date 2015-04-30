drop procedure ingresa_huella;
delimiter //
create procedure ingresa_huella(
			in pcodcli decimal(9,0),
			in pdnicli char(8),
            in pfecreg date, 
            in phuecli blob ,
            in pesthue decimal(1,0),
            in pusureg varchar(10))
begin
	declare correl decimal(10,0) default 0;
    
    select coalesce(max(corhue),0) into correl from tb_huella where codcli = pcodcli;
    
    insert into tb_huella (codcli,corhue,dnicli,huecli,fecreg,usureg,esthue) 
    values(pcodcli,correl+1,pdnicli,phuecli,pfecreg,pusureg,pesthue);    
end;
//
delimiter ;

drop procedure menu_empleado;
delimiter //
create procedure menu_empleado(in pCodEmp decimal(4,0))
begin
	declare perfil decimal(4,0) default 0;
	#seleccionamos perfil
    select CODPER into perfil from tb_empleado_perfil where CODEMP = pCodEmp;
	#abrimos el cursor menus para devolver las opciones de menu del empleado que a inciado sesion
	select * from tb_menu 
		where CODMEN in(select CODMEN from tb_perfil_menu where CODPER = perfil) and
			  ESTMEN = 1;
end;
//
delimiter ;

#valores iniciales para la configuracion

	
INSERT INTO tb_empleado(CODEMP,NOMEMP,APEEMP,DNIEMP,FECNAC,DIREMP,USUEMP,FECREG,USUREG,ESTEMP)
VALUES(1,'Nombre Admin','Apellido Admin','Dniadmin',current_date,'Direccion Admin','ADMIN',current_date,'ADMIN',0);

INSERT INTO tb_usuario(USUEMP,PASEMP,PASENC,CODEMP,CORREL,FECREG,USUREG,ESTUSR)
VALUES('ADMIN','123456',password('123456'),1,1,current_date,'ADMIN',0);

INSERT INTO tb_perfil(CODPER,NOMPER,DESPER,USUREG,FECREG,ESTPER)
VALUES(1,'Administrador','Usuario general del sistema.','ADMIN',current_date,0);

INSERT INTO tb_empleado_perfil(CODEMP,CODPER)VALUES(1,1);

INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(1,'Clientes','cliente.do','Registro de clientes','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(2,'Empleados','empleado.do','Registro de empleados','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(3,'Inscripciones','inscripcion.do','Inscripciones de clientes','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(4,'Modalidades','modalidad.do','Modalidades de pago','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(5,'Pagos','pago.do','Pago de clientes','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(6,'Perfiles','perfil.do','Perfiles de usuarios','ADMIN',0);
INSERT INTO tb_menu(CODMEN,NOMMEN,RUTMEN,DESMEN,USUREG,ESTMEN) VALUES(7,'Servicios','servicio.do','Servicios de clientes','ADMIN',0);

INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,1);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,2);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,3);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,4);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,5);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,6);
INSERT INTO tb_perfil_menu(CODPER,CODMEN) VALUES(1,7);