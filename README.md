### Data migration tool

---

#### About
This application loads csv/xml file with customer and customer-contacts data.
If the data is valid and if it doesn't yet exist in database, it is persisted to db.
In resources folder there are sample files with sample data that may be. 

#### Database configuration
To configure database there is a file dbconfig.properties in resources directory. 

#### CSV/XML with sample data
In resources directory there are 2 sample files with dummy data.

#### Database stucture
Database should have following tables:
- CUSTOMERS
    - ID (INTEGER)
    - NAME (VARCHAR)
    - SURNAME (VARCHAR)
    - Age (INTEGER)
- CONTACTS
    - ID (INTEGER)
    - ID_CUSTOMER (INTEGER)
    - TYPE (INTEGER)
    - CONTACT (VARCHAR)
Column types are obligatory, however **column and table names may be changed in dbconfig.properties file**

#### User Interface
The application, after it's start, provides user with a simple console interface.
Each command begins with "mt" (MT - Migration Tool).
The list of commands that are available will appear immediately after app is launched.
This list can also be shown using command "mt help".