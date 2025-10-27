# MedConnect Mockup Data Guide

This document provides a comprehensive overview of all the mockup data that has been populated in the MedConnect database.

## Database Overview

The MedConnect database has been populated with realistic test data across all modules. This guide will help you understand the data structure and how to test the application.

---

## 1. Admin Users (3 admins)

| Name | Email | Password |
|------|-------|----------|
| admin | admin@medicinedonation.com | admin123 |
| Rajesh Kumar | rajesh@medicinedonation.com | admin@2024 |
| Priya Singh | priya@medicinedonation.com | medadmin123 |

**Role**: Manage NGOs, medicines, and view users.

---

## 2. Medicines (12 medicines)

Common medicines added by different admins:

| Medicine Name | Price | Mfg Date | Exp Date | Admin |
|---|---|---|---|---|
| Paracetamol 500mg | ₹50 | 01-01-2024 | 31-12-2026 | admin |
| Aspirin 100mg | ₹30 | 15-02-2024 | 14-02-2026 | admin |
| Ibuprofen 200mg | ₹40 | 01-03-2024 | 28-02-2026 | admin |
| Cetirizine 10mg | ₹35 | 10-01-2024 | 09-01-2026 | admin |
| Amoxicillin 250mg | ₹120 | 05-02-2024 | 04-02-2026 | Rajesh Kumar |
| Metformin 500mg | ₹80 | 20-01-2024 | 19-01-2026 | Rajesh Kumar |
| Atorvastatin 10mg | ₹150 | 12-03-2024 | 11-03-2026 | Priya Singh |
| Omeprazole 20mg | ₹95 | 08-02-2024 | 07-02-2026 | Priya Singh |
| Vitamin D3 1000IU | ₹60 | 25-01-2024 | 24-01-2026 | admin |
| Cough Syrup 100ml | ₹45 | 01-03-2024 | 28-02-2025 | admin |
| Antacid Gel | ₹55 | 14-02-2024 | 13-02-2025 | Rajesh Kumar |
| Antihistamine Tablet | ₹40 | 20-01-2024 | 19-01-2025 | Priya Singh |

---

## 3. NGO Organizations (6 NGOs)

| ID | Organization Name | Location | Email | Phone | Admin |
|---|---|---|---|---|---|
| NGO001 | Health Care Foundation | Mumbai | ngo1@healthcare.org | 9876543210 | admin |
| NGO002 | Medical Aid Society | Delhi | ngo2@medicalsociety.org | 9876543211 | admin |
| NGO003 | Helping Hands NGO | Bangalore | ngo3@helpinghands.org | 8765432109 | Rajesh Kumar |
| NGO004 | Care India Foundation | Hyderabad | ngo4@careindia.org | 7654321098 | Priya Singh |
| NGO005 | Life Care Organization | Chennai | ngo5@lifecare.org | 9123456789 | admin |
| NGO006 | Community Health Services | Kolkata | ngo6@commhealth.org | 8912345678 | Rajesh Kumar |

**NGO Passwords** (for registration):
- All NGOs: ngo123, ngo456, ngo789, ngo@2024, ngo#123, ngo!456

---

## 4. Executive Representatives (8 executives)

Executives are assigned by NGOs to collect and manage medicines.

| Name | Email | Password | Gender | DOB | Contact | Location |
|---|---|---|---|---|---|---|
| John Executive | john@executive.com | exec123 | Male | 1990-01-15 | 9876543212 | Mumbai |
| Jane Executive | jane@executive.com | exec456 | Female | 1992-02-20 | 9876543213 | Delhi |
| Arun Kumar | arun@executive.com | exec789 | Male | 1988-05-10 | 9123456780 | Bangalore |
| Meena Sharma | meena@executive.com | exec@123 | Female | 1995-07-18 | 8765432100 | Hyderabad |
| Rajesh Patel | rajesh.p@executive.com | exec#456 | Male | 1991-03-25 | 9234567890 | Chennai |
| Priya Verma | priya.v@executive.com | exec!789 | Female | 1993-09-12 | 8934567890 | Kolkata |
| Vikram Singh | vikram@executive.com | exec$123 | Male | 1989-11-08 | 9012345678 | Pune |
| Sneha Gupta | sneha@executive.com | exec%456 | Female | 1994-06-30 | 8712345678 | Ahmedabad |

---

## 5. Users (10 regular users)

Regular users who donate medicines.

| Name | Email | Password | Gender | DOB | Contact | Location |
|---|---|---|---|---|---|---|
| John User | john@user.com | user123 | Male | 1985-01-10 | 9876543214 | Mumbai |
| Jane User | jane@user.com | user456 | Female | 1987-02-15 | 9876543215 | Delhi |
| Amit Singh | amit@user.com | user789 | Male | 1980-05-22 | 8765432101 | Bangalore |
| Deepa Patel | deepa@user.com | user@123 | Female | 1986-03-30 | 9234567801 | Hyderabad |
| Suresh Kumar | suresh@user.com | user#456 | Male | 1982-07-14 | 8912345679 | Chennai |
| Kavya Reddy | kavya@user.com | user!789 | Female | 1989-09-20 | 9567890123 | Kolkata |
| Arjun Nair | arjun@user.com | user$123 | Male | 1984-04-11 | 8567890123 | Pune |
| Sneha Iyer | sneha@user.com | user%456 | Female | 1988-12-05 | 9456789012 | Ahmedabad |
| Ravi Shankar | ravi@user.com | user&789 | Male | 1981-08-19 | 8456789012 | Jaipur |
| Neha Kapoor | neha@user.com | user*123 | Female | 1990-10-28 | 9345678901 | Lucknow |

---

## 6. Medicine Purchases (10 purchases)

Executives have purchased medicines from the inventory:

| Medicine | Executive | Date |
|---|---|---|
| Paracetamol 500mg | John Executive | - |
| Aspirin 100mg | Jane Executive | - |
| Ibuprofen 200mg | Arun Kumar | - |
| Cetirizine 10mg | Meena Sharma | - |
| Amoxicillin 250mg | John Executive | - |
| Metformin 500mg | Rajesh Patel | - |
| Atorvastatin 10mg | Priya Verma | - |
| Omeprazole 20mg | Vikram Singh | - |
| Vitamin D3 1000IU | Sneha Gupta | - |
| Cough Syrup 100ml | Jane Executive | - |

---

## 7. Medicine Donations (12 donation records)

Users have donated medicines across different cities:

| Medicine | User | Executive | Date | Quantity | Type | Location |
|---|---|---|---|---|---|---|
| Paracetamol 500mg | John User | John Executive | 01-10-2024 | 20 | Tablet | Mumbai |
| Aspirin 100mg | Jane User | Jane Executive | 02-10-2024 | 30 | Tablet | Delhi |
| Cetirizine 10mg | Amit Singh | Arun Kumar | 03-10-2024 | 15 | Tablet | Bangalore |
| Ibuprofen 200mg | Deepa Patel | Meena Sharma | 04-10-2024 | 25 | Tablet | Hyderabad |
| Amoxicillin 250mg | Suresh Kumar | Rajesh Patel | 05-10-2024 | 10 | Capsule | Chennai |
| Metformin 500mg | Kavya Reddy | Priya Verma | 06-10-2024 | 35 | Tablet | Kolkata |
| Vitamin D3 1000IU | Arjun Nair | Vikram Singh | 07-10-2024 | 40 | Capsule | Pune |
| Cough Syrup 100ml | Sneha Iyer | Sneha Gupta | 08-10-2024 | 2 | Syrup | Ahmedabad |
| Atorvastatin 10mg | Ravi Shankar | John Executive | 09-10-2024 | 18 | Tablet | Jaipur |
| Omeprazole 20mg | Neha Kapoor | Jane Executive | 10-10-2024 | 12 | Capsule | Lucknow |
| Antacid Gel | John User | Arun Kumar | 11-10-2024 | 5 | Gel | Mumbai |
| Antihistamine Tablet | Deepa Patel | Meena Sharma | 12-10-2024 | 22 | Tablet | Delhi |

---

## How to Use This Data for Testing

### 1. **Admin Module Testing**
Use admin credentials to:
- Login as: `admin` / `admin123`
- Add/Update/Delete NGOs and Medicines
- View all registered users

### 2. **NGO Module Testing**
Use NGO credentials to:
- Login as: `Health Care Foundation` / `ngo123`
- Assign executives to your NGO
- View collected medicines
- View medicines available in system

### 3. **Executive Module Testing**
Use executive credentials to:
- Login as: `john@executive.com` / `exec123`
- Buy medicines for the NGO
- Collect donated medicines from users
- View assigned users and orders

### 4. **User Module Testing**
Use user credentials to:
- Login as: `john@user.com` / `user123`
- View donated medicines (My Orders)
- Donate new medicines
- View available executives

---

## Database Setup Instructions

### Option 1: Using SQL File (Recommended)
1. Open MySQL Workbench or MySQL Command Line
2. Run the entire `database_setup.sql` file
3. All tables and mockup data will be created automatically

### Option 2: Step by Step
```bash
mysql -u root -p
USE MedConnect;
source C:\path\to\database_setup.sql;
```

### Option 3: Using phpMyAdmin
1. Create database: `MedConnect`
2. Import `database_setup.sql` file
3. Execute the import

---

## Quick Login Credentials Reference

### System Admins
- **admin** / **admin123**
- **Rajesh Kumar** / **admin@2024**
- **Priya Singh** / **medadmin123**

### Sample Users
- **john@user.com** / **user123**
- **jane@user.com** / **user456**

### Sample Executives
- **john@executive.com** / **exec123**
- **jane@executive.com** / **exec456**

### Sample NGOs
- **Health Care Foundation** / **ngo123**
- **Medical Aid Society** / **ngo456**

---

## Data Statistics

- **Total Admins**: 3
- **Total Users**: 10
- **Total Executives**: 8
- **Total NGOs**: 6
- **Total Medicines**: 12
- **Total Donations**: 12
- **Total Purchase Records**: 10

---

## Notes

- All data is realistic and representative of actual usage
- Dates are set to October 2024 for testing
- Phone numbers follow standard Indian format (10 digits)
- Emails use realistic domain names
- Passwords are easy to remember for testing purposes
- Data covers all major Indian cities for geographic variety

---

## Next Steps

1. **Compile the application**: Run `compile_and_run.bat`
2. **Start the application**: It will open the HomePage
3. **Test different modules** using the provided credentials
4. **Verify all features** work with the mockup data

---

*Last Updated: October 2024*
*For any issues or questions, refer to SETUP_GUIDE.md or SOLUTION_GUIDE.md*
