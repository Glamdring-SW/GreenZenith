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
CREATE PROCEDURE insertMessage (IN Content TEXT, SentDateTime DATETIME, SenderID INT, RecieverID INT)
BEGIN
INSERT INTO Message VALUES (NULL, Content, SentDateTime, SenderID, RecieverID);
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
CREATE PROCEDURE deleteMessage (IN MessageID INT)
BEGIN
DELETE FROM Message WHERE ID = MessageID;
END $$

DROP PROCEDURE IF EXISTS deleteCart $$
CREATE PROCEDURE deleteCart (IN UserID INT)
BEGIN
DELETE FROM Cart WHERE Cart.UserID = UserID;
END $$
DROP PROCEDURE IF EXISTS deleteCartProduct $$
CREATE PROCEDURE deleteCartProduct (IN UserID INT, IN ProductID INT)
BEGIN
DELETE FROM Cart WHERE Cart.ProductID = ProductID AND Cart.UserID = UserID7;
END $$

DROP PROCEDURE IF EXISTS updateUserUsername $$
CREATE PROCEDURE updateUserUsername (IN UserID INT, IN NewUsername NVARCHAR(50))
BEGIN
UPDATE PUser SET PUsername = NewUsername WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS updateUserEmail $$
CREATE PROCEDURE updateUserEmail (IN UserID INT, IN NewEmail NVARCHAR(100))
BEGIN
UPDATE PUser SET Email = NewEmail WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS updateUserAge $$
CREATE PROCEDURE updateUserAge (IN UserID INT, IN NewAge INT)
BEGIN
UPDATE PUser SET Age = newAge WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS updateUserLocation $$
CREATE PROCEDURE updateUserLocation (IN UserID INT, IN NewLocation NVARCHAR(50))
BEGIN
UPDATE PUser SET Location = NewLocation WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS updateUserPassword $$
CREATE PROCEDURE updateUserPassword (IN UserID INT, IN NewPassword NVARCHAR(30))
BEGIN
UPDATE PUser SET PasswordUser = NewPassword WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS setUserAdministratorAccess $$
CREATE PROCEDURE setUserAdministratorAccess (IN UserID INT, IN NewAdministratorAccess BOOLEAN)
BEGIN
UPDATE PUser SET AdministratorAccess = NewAdministratorAccess WHERE ID=UserID;
END $$
DROP PROCEDURE IF EXISTS updateUserPicture $$
CREATE PROCEDURE updateUserPicture (IN UserID INT, IN NewPicture MEDIUMBLOB)
BEGIN
UPDATE UserPicture SET Picture = NewPicture WHERE UserPicture.UserID=UserID;
END $$

DROP PROCEDURE IF EXISTS updatePlantName $$
CREATE PROCEDURE updatePlantName (IN PlantID INT, IN NewPlantName NVARCHAR(25))
BEGIN
UPDATE Plant SET PlantName = NewPlantName WHERE ID=PlantID;
END $$
DROP PROCEDURE IF EXISTS updatePlantingDate $$
CREATE PROCEDURE updatePlantingDate (IN PlantID INT, IN NewPlantingDate DATE)
BEGIN
UPDATE Plant SET PlantingDate = NewPlantingDate WHERE ID=PlantID;
END $$
DROP PROCEDURE IF EXISTS updatePlantDescription $$
CREATE PROCEDURE updatePlantDescription (IN PlantID INT, IN NewPlantDescription TEXT)
BEGIN
UPDATE Plant SET PlantDescription = NewPlantDescription WHERE ID=PlantID;
END $$
DROP PROCEDURE IF EXISTS updatePlantQuantity $$
CREATE PROCEDURE updatePlantQuantity (IN PlantID INT, IN NewQuantity INT)
BEGIN
UPDATE Plant SET Quantity = NewQuantity WHERE ID=PlantID;
END $$
DROP PROCEDURE IF EXISTS updatePlantPicture $$
CREATE PROCEDURE updatePlantPicture (IN PlantID INT, IN NewPicture MEDIUMBLOB)
BEGIN
UPDATE PlantPicture SET Picture = NewPicture WHERE PlantPicture.PlantID=PlantID;
END $$

DROP PROCEDURE IF EXISTS updateProductTitle $$
CREATE PROCEDURE updateProductTitle (IN ProductID INT, IN NewTitle NVARCHAR(30))
BEGIN
UPDATE Product SET Title = NewTitle WHERE ID=ProductID;
END $$
DROP PROCEDURE IF EXISTS updateProductDescription $$
CREATE PROCEDURE updateProductDescription (IN ProductID INT, IN NewProductDescription TEXT)
BEGIN
UPDATE Product SET ProductDescription = NewProductDescription WHERE ID=ProductID;
END $$
DROP PROCEDURE IF EXISTS updateProductPrice $$
CREATE PROCEDURE updateProductPrice (IN ProductID INT, IN NewPrice DECIMAL(19, 4))
BEGIN
UPDATE Product SET Price = NewPrice WHERE ID=ProductID;
END $$
DROP PROCEDURE IF EXISTS updateProductQuantity $$
CREATE PROCEDURE updateProductQuantity (IN ProductID INT, IN NewQuantity INT)
BEGIN
UPDATE Product SET Quantity = NewQuantity WHERE ID=ProductID;
END $$
DROP PROCEDURE IF EXISTS updateProductPicture $$
CREATE PROCEDURE updateProductPicture (IN ProductID INT, IN NewPicture MEDIUMBLOB)
BEGIN
UPDATE ProductPicture SET Picture = NewPicture WHERE ProductPicture.ProductID=ProductID;
END $$
DELIMITER ;