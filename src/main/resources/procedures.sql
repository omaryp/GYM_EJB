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