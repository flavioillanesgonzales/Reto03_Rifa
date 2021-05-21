
use dbRIFA;
GO

insert Into PREMIO
(CODPRE , DESPRE)
Values
('1','Canasta de Viveres - Box de Star Wars'),
('2','Mouse Inalambrico - MI Smart Band 6 XIAOMI'),
('3','USB 60gb de Madera - Audifono Blueetooh + HDMI'),
('4','Pqte. Papel Higienico + 2 Bolzas de Arroz - Pie de Manzana'),
('5','Juego de Platos - Set de Copas'),
('6','Keke + Budin 1kg c/u - 4 Tintas para Impresora')
GO

--Datos: PARTICIPANTE
Insert Into PARTICIPANTE
(NOMPAR,APEPAR,CELPAR,ESTPAR)
Values
('Fatima','Matos','984521102','A'),
('Ivan','Pajares','965889472','A'),
('Kevin','Silva','963200154','A'),
('Edson','Cardenas Arias','985632545','A'),
('Flavio','Illanes Gonzales','938895028','A'),
('Jhanpool','Cupe Perez','985641201','A'),
('Sebastian','Samaniego Torreblanca','982215004','A'),
('Victor','Sandoval Rosales','915426325','A'),
('Giomara','Porta Gimenez','941250015','A'),
('Rigoberto','Alvarado','998631040','A');

select * from PARTICIPANTE;

--Datos: RIFA
Insert Into RIFA
(IDPAR)
Values
('1'),
('1'),
('1'),
('1'),
('1'),
('1'),
('1'),
('1'),
('2'),
('2'),
('2'),
('2'),
('2'),
('2'),
('2'),
('2'),
('2'),
('2'),
('3'),
('3'),
('4'),
('4'),
('5'),
('5'),
('5'),
('6'),
('6'),
('7'),
('7'),
('8'),
('8'),
('8'),
('8'),
('8'),
('8'),
('8'),
('8'),
('9'),
('9'),
('9'),
('9'),
('10'),
('10');

