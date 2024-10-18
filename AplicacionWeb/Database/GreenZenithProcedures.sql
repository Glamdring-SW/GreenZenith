/*
DELIMITER $$
DROP PROCEDURE IF EXISTS name $$
CREATE PROCEDURE name (IN )
BEGIN
;
END $$
DELIMITER ;
*/

DELIMITER $$
DROP PROCEDURE IF EXISTS insertUser $$
CREATE PROCEDURE insertUser (IN Username NVARCHAR(50), IN Email NVARCHAR(100), IN Age INT, IN Location NVARCHAR(50), IN PasswordUser NVARCHAR(30), IN AdministratorAccess BOOLEAN)
BEGIN
INSERT INTO PUser VALUES (NULL, Username, Email, Age, Location, PasswordUser, AdministratorAccess);
END $$
DROP PROCEDURE IF EXISTS insertUserPicture $$
CREATE PROCEDURE insertUserPicture (IN UserID INT, IN Picture MEDIUMBLOB)
BEGIN
INSERT INTO UserPicture VALUES (UserID, Picture);
END $$

DROP PROCEDURE IF EXISTS insertPlant $$
CREATE PROCEDURE insertPlant (IN PlantName NVARCHAR(25), IN PlantingDate DATE, IN PlantDescription TEXT, IN Quantity INT, IN UserID INT) 
BEGIN
INSERT INTO Plant VALUES (NULL, PlantName, PlantingDate, PlantDescription, Quantity, UserID);
END $$
DROP PROCEDURE IF EXISTS insertPlantPicture $$
CREATE PROCEDURE insertPlantPicture (IN PlantID INT, IN Picture MEDIUMBLOB)
BEGIN
INSERT INTO PlantPicture VALUES (PlantID, Picture);
END $$
DROP PROCEDURE IF EXISTS insertIntoPlantSchedule $$
CREATE PROCEDURE insertIntoPlantSchedule (IN PlantID INT, WaterTime TIME)
BEGIN
INSERT INTO PlantSchedule VALUES (PlantID, WaterTime);
END $$

DROP PROCEDURE IF EXISTS insertProduct $$
CREATE PROCEDURE insertProduct (IN Title NVARCHAR(30), IN ProductDescription TEXT, IN Price DECIMAL(19, 4), IN Quantity INT, IN PlantID INT)
BEGIN
INSERT INTO Product VALUES (NULL, Title, ProductDescription, Price, Quantity, PlantID);
END $$
DROP PROCEDURE IF EXISTS insertProductPicture $$
CREATE PROCEDURE insertProductPicture (IN ProductID INT, IN Picture MEDIUMBLOB)
BEGIN
INSERT INTO ProductPicture VALUES (ProductID, Picture);
END $$

DROP PROCEDURE IF EXISTS insertMessage $$
CREATE PROCEDURE insertMessages (IN Content TEXT, SentDateTime DATETIME, SenderID INT, Reciever INT)
BEGIN
INSERT INTO Messages VALUES (NULL, Content, SentDateTime, SenderID, RecieverID);
END $$

DROP PROCEDURE IF EXISTS insertIntoCart $$
CREATE PROCEDURE insertIntoCart (IN UserID INT, IN ProductID INT)
BEGIN
INSERT INTO Cart VALUES (UserID, ProductID);
END $$

DROP PROCEDURE IF EXISTS deleteUser $$
CREATE PROCEDURE deleteUser (IN UserID INT)
BEGIN
DELETE FROM PUser WHERE ID = UserID;
END $$
DROP PROCEDURE IF EXISTS deleteUserPicture $$
CREATE PROCEDURE deleteUserPicture (IN UserID INT)
BEGIN
DELETE FROM PUserPicture WHERE PUserPicture.UserID = UserID;
END $$

DROP PROCEDURE IF EXISTS deletePlant $$
CREATE PROCEDURE deletePlant (IN PlantID INT) 
BEGIN
DELETE FROM Plant WHERE ID = PlantID;
END $$
DROP PROCEDURE IF EXISTS deletePlantPicture $$
CREATE PROCEDURE deletePlantPicture (IN PlantID INT)
BEGIN
DELETE FROM PlantPicture WHERE PlantPicture.PlantID = PlantID;
END $$
DROP PROCEDURE IF EXISTS deletePlantSchedule $$
CREATE PROCEDURE deletePlantSchedule (IN PlantID INT)
BEGIN
DELETE FROM PlantSchedule WHERE PlantSchedule.PlantID = PlantID;
END $$

DROP PROCEDURE IF EXISTS deleteProduct $$
CREATE PROCEDURE deleteProduct (IN ProductID INT)
BEGIN
DELETE FROM Product WHERE ID = ProductID;
END $$
DROP PROCEDURE IF EXISTS deleteProductPicture $$
CREATE PROCEDURE deleteProductPicture (IN ProductID INT)
BEGIN
DELETE FROM ProductPicture WHERE ProductPicture.ProductID = ProductID;
END $$

DROP PROCEDURE IF EXISTS deleteMessage $$
CREATE PROCEDURE deleteMessages (IN MessageID INT)
BEGIN
DELETE FROM Messages WHERE ID = MessageID;
END $$


DROP PROCEDURE IF EXISTS deleteCart $$
CREATE PROCEDURE deleteCart (IN UserID INT)
BEGIN
DELETE FROM Cart WHERE Cart.UserID = UserID;
END $$
DROP PROCEDURE IF EXISTS deleteCartProduct $$
CREATE PROCEDURE deleteCartProduct (IN UserID INT, IN ProductID INT)
BEGIN
DELETE FROM Cart WHERE Cart.ProductID = ProductID AND Cart.UserID = UserID;
END $$
DELIMITER ;