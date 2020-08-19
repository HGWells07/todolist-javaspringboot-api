#CALL CreateTask('Crear aplicación de Angular', 'Crear una API REST en Spring Boot y hacer una app front end en framework de Angular', '2020-08-19');
#CALL CreateTask('Junta con Paco de Autobot', 'Ver correcciones de diseño y negocio', '2020-08-20');
#CALL CreateTask('Ser feliz', '', NULL);

#CALL EditTask('846eb30e-e172-11ea-a376-283a4d6162b7', 'Ser aún más feliz', 'Ver una película', '2020-08-31');

#Call ChangeCompleteTask('9cb31825-e172-11ea-a376-283a4d6162b7');
USE todolistexample;
SELECT Tasks.id FROM Tasks WHERE Tasks.id = 'a1cf437f-e19d-11ea-a376-283a4d6162b7' LIMIT 1;

#CALL DeleteTask('8464bf26-e172-11ea-a376-283a4d6162b7');


