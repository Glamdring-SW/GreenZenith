-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GreenZenith
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GreenZenith
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GreenZenith` DEFAULT CHARACTER SET utf8 ;
USE `GreenZenith` ;

-- -----------------------------------------------------
-- Table `GreenZenith`.`PUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`PUser` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`PUser` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria del Usuario',
  `PUsername` VARCHAR(50) NOT NULL COMMENT 'Nombre del usuario asignado',
  `Email` VARCHAR(100) NOT NULL COMMENT 'Correo electronico unico asociado a el usuario',
  `Age` INT NOT NULL COMMENT 'Edad del usuario sin considerar meses o dias',
  `Location` VARCHAR(50) NULL COMMENT 'Localizacion del usuario almacenada como una cadena',
  `PasswordUser` VARCHAR(30) NOT NULL COMMENT 'Contraseña del usuario para acceder a la aplicacion y comprobar la validez de credenciales adecuada',
  `AdministratorAccess` TINYINT NOT NULL DEFAULT 0,
  `Picture` MEDIUMBLOB NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `PUsername_UNIQUE` (`PUsername` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GreenZenith`.`Plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`Plant` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`Plant` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(25) NOT NULL,
  `PlantingDate` DATE NOT NULL,
  `Description` TEXT(500) NULL,
  `Quantity` INT NOT NULL,
  `Picture` MEDIUMBLOB NULL,
  `PUser_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Plant_PUser1_idx` (`PUser_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Plant_PUser1`
    FOREIGN KEY (`PUser_ID`)
    REFERENCES `GreenZenith`.`PUser` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GreenZenith`.`PlantSchedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`PlantSchedule` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`PlantSchedule` (
  `WaterTime` TIME NOT NULL,
  `Plant_ID` INT NOT NULL,
  INDEX `fk_PlantSchedule_Plant1_idx` (`Plant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PlantSchedule_Plant1`
    FOREIGN KEY (`Plant_ID`)
    REFERENCES `GreenZenith`.`Plant` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GreenZenith`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`Product` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`Product` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(30) NOT NULL,
  `Description` TEXT(250) NULL,
  `Price` DECIMAL(19,4) NOT NULL,
  `Quantity` VARCHAR(45) NOT NULL,
  `Picture` MEDIUMBLOB NULL,
  `Plant_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Product_Plant1_idx` (`Plant_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Plant1`
    FOREIGN KEY (`Plant_ID`)
    REFERENCES `GreenZenith`.`Plant` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GreenZenith`.`Cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`Cart` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`Cart` (
  `PUser_ID` INT NOT NULL,
  `Product_ID` INT NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`PUser_ID`, `Product_ID`),
  INDEX `fk_PUser_has_Product_Product1_idx` (`Product_ID` ASC) VISIBLE,
  INDEX `fk_PUser_has_Product_PUser1_idx` (`PUser_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PUser_has_Product_PUser1`
    FOREIGN KEY (`PUser_ID`)
    REFERENCES `GreenZenith`.`PUser` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PUser_has_Product_Product1`
    FOREIGN KEY (`Product_ID`)
    REFERENCES `GreenZenith`.`Product` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GreenZenith`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GreenZenith`.`Message` ;

CREATE TABLE IF NOT EXISTS `GreenZenith`.`Message` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Content` TEXT(200) NOT NULL,
  `Sent` DATETIME NOT NULL,
  `SenderID` INT NOT NULL,
  `RecieverID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Message_PUser1_idx` (`SenderID` ASC) VISIBLE,
  INDEX `fk_Message_PUser2_idx` (`RecieverID` ASC) VISIBLE,
  CONSTRAINT `fk_Message_PUser1`
    FOREIGN KEY (`SenderID`)
    REFERENCES `GreenZenith`.`PUser` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Message_PUser2`
    FOREIGN KEY (`RecieverID`)
    REFERENCES `GreenZenith`.`PUser` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
