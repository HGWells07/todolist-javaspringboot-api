USE todolistexample;
DELIMITER //

DROP PROCEDURE IF EXISTS GetAllTasks//
CREATE PROCEDURE GetAllTasks()
BEGIN
	SELECT *  FROM Tasks;
END //

DROP PROCEDURE IF EXISTS GetAllTasksNames//
CREATE PROCEDURE GetAllTasksNames()
BEGIN
	SELECT Id, TaskName, Done FROM Tasks;
END //

DROP PROCEDURE IF EXISTS GetUndoneTasksNames//
CREATE PROCEDURE GetUndoneTasksNames()
BEGIN
	SELECT Id, TaskName, Done FROM Tasks WHERE Done = 0;
END //

DROP PROCEDURE IF EXISTS SearchAllTasks//
CREATE PROCEDURE SearchAllTasks(IN p_search VARCHAR(255))
BEGIN
	SELECT Id, TaskName, Done FROM Tasks 
    WHERE TaskName LIKE CONCAT("%",p_search,"%") OR Details LIKE CONCAT("%",p_search,"%");
END //

DROP PROCEDURE IF EXISTS SearchUndoneTasks//
CREATE PROCEDURE SearchUndoneTasks(IN p_search VARCHAR(255))
BEGIN
	SELECT Id, TaskName, Done FROM Tasks WHERE Done = 0 AND
    TaskName LIKE CONCAT("%",p_search,"%") OR Details LIKE CONCAT("%",p_search,"%");
END //

DROP PROCEDURE IF EXISTS EditTask//
CREATE PROCEDURE EditTask(
	IN p_id VARCHAR(36),
	IN p_task_name VARCHAR(255),
    IN p_details VARCHAR(1024),
    IN p_due_date DATE
)
BEGIN
	UPDATE Tasks
	SET 
    TaskName = p_task_name, 
    Details = p_details,
    DueDate = p_due_date
    WHERE Id = p_id;
END //

DROP PROCEDURE IF EXISTS ChangeDoneTask//
CREATE PROCEDURE ChangeDoneTask(
	IN p_id VARCHAR(36)
)
BEGIN
	UPDATE Tasks
	SET 
    Done = !Done
    WHERE Id = p_id;
END //

DROP PROCEDURE IF EXISTS DeleteTask//
CREATE PROCEDURE DeleteTask(IN p_id VARCHAR(36))
BEGIN
	DELETE FROM Tasks
    WHERE Id=p_id;
END //

DROP PROCEDURE IF EXISTS GetTask//
CREATE PROCEDURE GetTask(IN p_id VARCHAR(36))
BEGIN
	SELECT *  FROM Tasks
    WHERE Id=p_id;
END //

DROP PROCEDURE IF EXISTS CreateTask//
CREATE PROCEDURE CreateTask(
    IN p_task_name VARCHAR(255),
    IN p_details VARCHAR(1024),
    IN p_due_date DATE
)
BEGIN
	declare v_id VARCHAR(36);
	INSERT INTO Tasks(Id, TaskName, Details, Done, DueDate) values (uuid(), p_task_name, p_details, 0, p_due_date);
	SET v_id = last_insert_id();
    CALL GetTask(v_id);
END //

DELIMITER ;