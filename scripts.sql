
create database bdescuela;
use bdescuela;
create table Alumnos(
id int not null auto_increment primary key,
nombres nvarchar(50),
apellidos nvarchar(50)
);

/*Create*/
insert into Alumnos (nombres,apellidos) values ("Felipe","Labarca");
/*Read*/
select * from Alumnos;
/*Update*/
update Alumnos set alumnos.nombres ='Andres', alumnos.apellidos = 'Corbalan' where alumnos.id=1;
/*Delete*/
delete from Alumnos where alumnos.id=2;