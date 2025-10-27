-- MedConnect Database Setup
-- Run this script in MySQL to create the required database and tables

-- Create database
CREATE DATABASE IF NOT EXISTS `MedConnect`;
USE `MedConnect`;

-- Admin login table
CREATE TABLE IF NOT EXISTS `adminlogin` (
    `Name` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Name`, `Email`)
);

-- User registration table
CREATE TABLE IF NOT EXISTS `userregistration` (
    `Name` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `Gender` VARCHAR(10) NOT NULL,
    `Date of Birth` DATE NOT NULL,
    `Contact Number` VARCHAR(20) NOT NULL,
    `Location` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Name`, `Email`)
);

-- NGO registration table
CREATE TABLE IF NOT EXISTS `ngoregistrationform` (
    `User Name` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `Confirm Password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`User Name`, `Email`)
);

-- Executive table
CREATE TABLE IF NOT EXISTS `executive` (
    `Name` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `Gender` VARCHAR(10) NOT NULL,
    `Date of Birth` DATE NOT NULL,
    `Contact Number` VARCHAR(20) NOT NULL,
    `Location` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Name`, `Email`)
);

-- Add NGO table (admin managed)
CREATE TABLE IF NOT EXISTS `addngo` (
    `ID` VARCHAR(50) NOT NULL,
    `Name` VARCHAR(255) NOT NULL,
    `Gender` VARCHAR(10) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Contact Number` VARCHAR(20) NOT NULL,
    `Location` VARCHAR(255) NOT NULL,
    `Admin Name` VARCHAR(255) NOT NULL,
    `Admin Email` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID`)
);

-- Add Medicine table (admin managed)
CREATE TABLE IF NOT EXISTS `addmedicine` (
    `Medicine Name` VARCHAR(255) NOT NULL,
    `Price` VARCHAR(50) NOT NULL,
    `Manufacture Date` VARCHAR(50) NOT NULL,
    `Expire Date` VARCHAR(50) NOT NULL,
    `Admin Name` VARCHAR(255) NOT NULL,
    `Admin Email` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Medicine Name`)
);

-- Buy Medicine table (executive purchases)
CREATE TABLE IF NOT EXISTS `buymedicine` (
    `Medicine Name` VARCHAR(255) NOT NULL,
    `Price` VARCHAR(50) NOT NULL,
    `Manufacture Date` VARCHAR(50) NOT NULL,
    `Expiry Date` VARCHAR(50) NOT NULL,
    `Executive Name` VARCHAR(255) NOT NULL,
    `Executive Email` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Medicine Name`, `Executive Name`, `Executive Email`)
);

CREATE TABLE IF NOT EXISTS `donate medicine` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `Medicine Name` VARCHAR(255) NOT NULL,
    `Manufacture Date` VARCHAR(50) NOT NULL,
    `Expiry Date` VARCHAR(50) NOT NULL,
    `Medicine Type` VARCHAR(50) NOT NULL,
    `Number of Tablet` VARCHAR(50) NOT NULL,
    `Donate Time` VARCHAR(50) NOT NULL,
    `Donate Date` VARCHAR(50) NOT NULL,
    `Executive Name` VARCHAR(255) NOT NULL,
    `Executive Email` VARCHAR(255) NOT NULL,
    `User Name` VARCHAR(255) NOT NULL,
    `User Email` VARCHAR(255) NOT NULL,
    `Contact Number` VARCHAR(20) NOT NULL,
    `Flat No` VARCHAR(100) NOT NULL,
    `Street` VARCHAR(255) NOT NULL,
    `Area` VARCHAR(255) NOT NULL,
    `Pin Code` VARCHAR(20) NOT NULL,
    `State` VARCHAR(100) NOT NULL,
    `CityTown` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_donate_lookup` (`Medicine Name`(191), `User Email`(191), `Donate Date`)
);

-- =====================================================
-- MOCKUP DATA INSERTION
-- =====================================================

-- 1. INSERT ADMIN DATA
INSERT INTO `adminlogin` (`Name`, `Email`, `Password`) VALUES 
('admin', 'admin@medicinedonation.com', 'admin123'),
('Rajesh Kumar', 'rajesh@medicinedonation.com', 'admin@2024'),
('Priya Singh', 'priya@medicinedonation.com', 'medadmin123');

-- 2. INSERT MEDICINE DATA
INSERT INTO `addmedicine` (`Medicine Name`, `Price`, `Manufacture Date`, `Expire Date`, `Admin Name`, `Admin Email`) VALUES 
('Paracetamol 500mg', '50', '01-01-2024', '31-12-2026', 'admin', 'admin@medicinedonation.com'),
('Aspirin 100mg', '30', '15-02-2024', '14-02-2026', 'admin', 'admin@medicinedonation.com'),
('Ibuprofen 200mg', '40', '01-03-2024', '28-02-2026', 'admin', 'admin@medicinedonation.com'),
('Cetirizine 10mg', '35', '10-01-2024', '09-01-2026', 'admin', 'admin@medicinedonation.com'),
('Amoxicillin 250mg', '120', '05-02-2024', '04-02-2026', 'Rajesh Kumar', 'rajesh@medicinedonation.com'),
('Metformin 500mg', '80', '20-01-2024', '19-01-2026', 'Rajesh Kumar', 'rajesh@medicinedonation.com'),
('Atorvastatin 10mg', '150', '12-03-2024', '11-03-2026', 'Priya Singh', 'priya@medicinedonation.com'),
('Omeprazole 20mg', '95', '08-02-2024', '07-02-2026', 'Priya Singh', 'priya@medicinedonation.com'),
('Vitamin D3 1000IU', '60', '25-01-2024', '24-01-2026', 'admin', 'admin@medicinedonation.com'),
('Cough Syrup 100ml', '45', '01-03-2024', '28-02-2025', 'admin', 'admin@medicinedonation.com'),
('Antacid Gel', '55', '14-02-2024', '13-02-2025', 'Rajesh Kumar', 'rajesh@medicinedonation.com'),
('Antihistamine Tablet', '40', '20-01-2024', '19-01-2025', 'Priya Singh', 'priya@medicinedonation.com');

-- 3. INSERT NGO DATA
INSERT INTO `addngo` (`ID`, `Name`, `Gender`, `Email`, `Contact Number`, `Location`, `Admin Name`, `Admin Email`) VALUES 
('NGO001', 'Health Care Foundation', 'Male', 'ngo1@healthcare.org', '9876543210', 'Mumbai', 'admin', 'admin@medicinedonation.com'),
('NGO002', 'Medical Aid Society', 'Female', 'ngo2@medicalsociety.org', '9876543211', 'Delhi', 'admin', 'admin@medicinedonation.com'),
('NGO003', 'Helping Hands NGO', 'Male', 'ngo3@helpinghands.org', '8765432109', 'Bangalore', 'Rajesh Kumar', 'rajesh@medicinedonation.com'),
('NGO004', 'Care India Foundation', 'Female', 'ngo4@careindia.org', '7654321098', 'Hyderabad', 'Priya Singh', 'priya@medicinedonation.com'),
('NGO005', 'Life Care Organization', 'Male', 'ngo5@lifecare.org', '9123456789', 'Chennai', 'admin', 'admin@medicinedonation.com'),
('NGO006', 'Community Health Services', 'Female', 'ngo6@commhealth.org', '8912345678', 'Kolkata', 'Rajesh Kumar', 'rajesh@medicinedonation.com');

-- 4. INSERT NGO REGISTRATION DATA
INSERT INTO `ngoregistrationform` (`User Name`, `Email`, `Password`, `Confirm Password`) VALUES 
('Health Care Foundation', 'ngo1@healthcare.org', 'ngo123', 'ngo123'),
('Medical Aid Society', 'ngo2@medicalsociety.org', 'ngo456', 'ngo456'),
('Helping Hands NGO', 'ngo3@helpinghands.org', 'ngo789', 'ngo789'),
('Care India Foundation', 'ngo4@careindia.org', 'ngo@2024', 'ngo@2024'),
('Life Care Organization', 'ngo5@lifecare.org', 'ngo#123', 'ngo#123'),
('Community Health Services', 'ngo6@commhealth.org', 'ngo!456', 'ngo!456');

-- 5. INSERT EXECUTIVE DATA
INSERT INTO `executive` (`Name`, `Email`, `Password`, `Gender`, `Date of Birth`, `Contact Number`, `Location`) VALUES 
('John Executive', 'john@executive.com', 'exec123', 'Male', '1990-01-15', '9876543212', 'Mumbai'),
('Jane Executive', 'jane@executive.com', 'exec456', 'Female', '1992-02-20', '9876543213', 'Delhi'),
('Arun Kumar', 'arun@executive.com', 'exec789', 'Male', '1988-05-10', '9123456780', 'Bangalore'),
('Meena Sharma', 'meena@executive.com', 'exec@123', 'Female', '1995-07-18', '8765432100', 'Hyderabad'),
('Rajesh Patel', 'rajesh.p@executive.com', 'exec#456', 'Male', '1991-03-25', '9234567890', 'Chennai'),
('Priya Verma', 'priya.v@executive.com', 'exec!789', 'Female', '1993-09-12', '8934567890', 'Kolkata'),
('Vikram Singh', 'vikram@executive.com', 'exec$123', 'Male', '1989-11-08', '9012345678', 'Pune'),
('Sneha Gupta', 'sneha@executive.com', 'exec%456', 'Female', '1994-06-30', '8712345678', 'Ahmedabad');

-- 6. INSERT USER DATA
INSERT INTO `userregistration` (`Name`, `Email`, `Password`, `Gender`, `Date of Birth`, `Contact Number`, `Location`) VALUES 
('John User', 'john@user.com', 'user123', 'Male', '1985-01-10', '9876543214', 'Mumbai'),
('Jane User', 'jane@user.com', 'user456', 'Female', '1987-02-15', '9876543215', 'Delhi'),
('Amit Singh', 'amit@user.com', 'user789', 'Male', '1980-05-22', '8765432101', 'Bangalore'),
('Deepa Patel', 'deepa@user.com', 'user@123', 'Female', '1986-03-30', '9234567801', 'Hyderabad'),
('Suresh Kumar', 'suresh@user.com', 'user#456', 'Male', '1982-07-14', '8912345679', 'Chennai'),
('Kavya Reddy', 'kavya@user.com', 'user!789', 'Female', '1989-09-20', '9567890123', 'Kolkata'),
('Arjun Nair', 'arjun@user.com', 'user$123', 'Male', '1984-04-11', '8567890123', 'Pune'),
('Sneha Iyer', 'sneha@user.com', 'user%456', 'Female', '1988-12-05', '9456789012', 'Ahmedabad'),
('Ravi Shankar', 'ravi@user.com', 'user&789', 'Male', '1981-08-19', '8456789012', 'Jaipur'),
('Neha Kapoor', 'neha@user.com', 'user*123', 'Female', '1990-10-28', '9345678901', 'Lucknow');

-- 7. INSERT BUY MEDICINE DATA
INSERT INTO `buymedicine` (`Medicine Name`, `Price`, `Manufacture Date`, `Expiry Date`, `Executive Name`, `Executive Email`) VALUES 
('Paracetamol 500mg', '50', '01-01-2024', '31-12-2026', 'John Executive', 'john@executive.com'),
('Aspirin 100mg', '30', '15-02-2024', '14-02-2026', 'Jane Executive', 'jane@executive.com'),
('Ibuprofen 200mg', '40', '01-03-2024', '28-02-2026', 'Arun Kumar', 'arun@executive.com'),
('Cetirizine 10mg', '35', '10-01-2024', '09-01-2026', 'Meena Sharma', 'meena@executive.com'),
('Amoxicillin 250mg', '120', '05-02-2024', '04-02-2026', 'John Executive', 'john@executive.com'),
('Metformin 500mg', '80', '20-01-2024', '19-01-2026', 'Rajesh Patel', 'rajesh.p@executive.com'),
('Atorvastatin 10mg', '150', '12-03-2024', '11-03-2026', 'Priya Verma', 'priya.v@executive.com'),
('Omeprazole 20mg', '95', '08-02-2024', '07-02-2026', 'Vikram Singh', 'vikram@executive.com'),
('Vitamin D3 1000IU', '60', '25-01-2024', '24-01-2026', 'Sneha Gupta', 'sneha@executive.com'),
('Cough Syrup 100ml', '45', '01-03-2024', '28-02-2025', 'Jane Executive', 'jane@executive.com');

-- 8. INSERT DONATION DATA
INSERT INTO `donate medicine` 
(`Medicine Name`, `Manufacture Date`, `Expiry Date`, `Medicine Type`, `Number of Tablet`, `Donate Time`, `Donate Date`, 
 `Executive Name`, `Executive Email`, `User Name`, `User Email`, `Contact Number`, `Flat No`, `Street`, `Area`, `Pin Code`, `State`, `CityTown`) 
VALUES 
('Paracetamol 500mg', '01-01-2024', '31-12-2026', 'Tablet', '20', '10:30 AM', '01-10-2024', 'John Executive', 'john@executive.com', 'John User', 'john@user.com', '9876543214', '101', 'Marine Drive', 'Colaba', '400005', 'Maharashtra', 'Mumbai'),
('Aspirin 100mg', '15-02-2024', '14-02-2026', 'Tablet', '30', '02:15 PM', '02-10-2024', 'Jane Executive', 'jane@executive.com', 'Jane User', 'jane@user.com', '9876543215', '202', 'India Gate Road', 'New Delhi', '110001', 'Delhi', 'Delhi'),
('Cetirizine 10mg', '10-01-2024', '09-01-2026', 'Tablet', '15', '11:45 AM', '03-10-2024', 'Arun Kumar', 'arun@executive.com', 'Amit Singh', 'amit@user.com', '8765432101', '303', 'Brigade Road', 'Bangalore', '560001', 'Karnataka', 'Bangalore'),
('Ibuprofen 200mg', '01-03-2024', '28-02-2026', 'Tablet', '25', '03:30 PM', '04-10-2024', 'Meena Sharma', 'meena@executive.com', 'Deepa Patel', 'deepa@user.com', '9234567801', '404', 'Banjara Hills Road', 'Hyderabad', '500034', 'Telangana', 'Hyderabad'),
('Amoxicillin 250mg', '05-02-2024', '04-02-2026', 'Capsule', '10', '09:00 AM', '05-10-2024', 'Rajesh Patel', 'rajesh.p@executive.com', 'Suresh Kumar', 'suresh@user.com', '8912345679', '505', 'Mount Road', 'Chennai', '600002', 'Tamil Nadu', 'Chennai'),
('Metformin 500mg', '20-01-2024', '19-01-2026', 'Tablet', '35', '01:00 PM', '06-10-2024', 'Priya Verma', 'priya.v@executive.com', 'Kavya Reddy', 'kavya@user.com', '9567890123', '606', 'Chowringhee Road', 'Kolkata', '700071', 'West Bengal', 'Kolkata'),
('Vitamin D3 1000IU', '25-01-2024', '24-01-2026', 'Capsule', '40', '04:45 PM', '07-10-2024', 'Vikram Singh', 'vikram@executive.com', 'Arjun Nair', 'arjun@user.com', '8567890123', '707', 'Laxmi Road', 'Pune', '411001', 'Maharashtra', 'Pune'),
('Cough Syrup 100ml', '01-03-2024', '28-02-2025', 'Syrup', '2', '10:15 AM', '08-10-2024', 'Sneha Gupta', 'sneha@executive.com', 'Sneha Iyer', 'sneha@user.com', '9456789012', '808', 'Ashram Road', 'Ahmedabad', '380001', 'Gujarat', 'Ahmedabad'),
('Atorvastatin 10mg', '12-03-2024', '11-03-2026', 'Tablet', '18', '02:00 PM', '09-10-2024', 'John Executive', 'john@executive.com', 'Ravi Shankar', 'ravi@user.com', '8456789012', '909', 'MI Road', 'Jaipur', '302001', 'Rajasthan', 'Jaipur'),
('Omeprazole 20mg', '08-02-2024', '07-02-2026', 'Capsule', '12', '11:30 AM', '10-10-2024', 'Jane Executive', 'jane@executive.com', 'Neha Kapoor', 'neha@user.com', '9345678901', '1010', 'Hazratganj', 'Lucknow', '226001', 'Uttar Pradesh', 'Lucknow'),
('Antacid Gel', '14-02-2024', '13-02-2025', 'Gel', '5', '03:15 PM', '11-10-2024', 'Arun Kumar', 'arun@executive.com', 'John User', 'john@user.com', '9876543214', '1111', 'Bandra Worli', 'Mumbai', '400050', 'Maharashtra', 'Mumbai'),
('Antihistamine Tablet', '20-01-2024', '19-01-2025', 'Tablet', '22', '09:45 AM', '12-10-2024', 'Meena Sharma', 'meena@executive.com', 'Deepa Patel', 'deepa@user.com', '9234567801', '1212', 'CP Road', 'Delhi', '110001', 'Delhi', 'Delhi');