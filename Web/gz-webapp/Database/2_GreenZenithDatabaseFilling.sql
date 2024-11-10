USE GreenZenith;

INSERT INTO `PUser` (`PUsername`, `Email`, `Age`, `Location`, `PasswordUser`, `AdministratorAccess`, `Picture`) VALUES
('carlos_martinez', 'carlos.martinez@gmail.com', 25, 'Mexico City', 'SecurePass!2023', 1, DEFAULT),
('sara_hernandez', 'sara.hernandez@yahoo.com', 30, 'New York', 'Str0ng#Password2023', 0, DEFAULT),
('hiroshi_tanaka', 'hiroshi.tanaka@hotmail.com', 22, 'Tokyo', 'MyP@ssword#2024!', 0, DEFAULT);

INSERT INTO `Plant` (`Name`, `Description`, `Quantity`, `PlantingDate`, `Picture`, `PUser_ID`) VALUES
('Aloe Vera', 'Planta medicinal, ideal para piel y quemaduras', 5, '2023-01-15', DEFAULT, 1),
('Cactus', 'Planta resistente al clima seco, requiere poco riego', 3, '2022-08-20', DEFAULT, 1),
('Lavanda', 'Planta aromática, útil para relajación', 8, '2021-02-10', DEFAULT, 2);

INSERT INTO `PlantSchedule` (`WaterTime`, `Plant_ID`) VALUES
('08:00:00', 1),
('16:00:00', 1),
('09:00:00', 2),
('20:00:00', 1),
('07:30:00', 3);

INSERT INTO `Product` (`Title`, `Description`, `Price`, `Quantity`, `Picture`, `Plant_ID`) VALUES
('Aloe Vera Gel', 'Gel extraído de Aloe Vera, útil para piel', 50.00, '5', DEFAULT, 1),
('Cactus Decorativo', 'Decoración para interiores y exteriores', 30.00, '90', DEFAULT, 2),
('Aceite de Lavanda', 'Aceite esencial de lavanda para aromaterapia', 75.00, '500', DEFAULT, 3);

INSERT INTO `Cart` (`PUser_ID`, `Product_ID`, `Quantity`) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 3, 1);

INSERT INTO `Message` (`Content`, `Sent`, `SenderID`, `RecieverID`) VALUES
('Hola, ¿cómo estás?', '2023-10-05 10:00:00', 1, 2),
('¿Tienes más plantas disponibles?', '2023-10-06 12:15:00', 2, 1),
('¿Qué tipo de cactus vendes?', '2023-10-07 08:30:00', 3, 1);