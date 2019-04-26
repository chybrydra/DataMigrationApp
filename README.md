### Data migration tool

---

[about](#about) |
[db configuration](#dbconf) |
[launch app](#launch) |
[sample files](#samples) | 
[database structure](#schema) |
[user interface](#ui)  |

---

#### <a name="about"></a> About
This application, written in Java 8, loads csv/xml file with customer and customer-contacts data.
If the data is valid and if it doesn't yet exist in database, it is persisted to db.
In resources folder there are sample files with sample data that may be.  

#### <a name="dbconf"></a> Database configuration
To configure database there is a file dbconfig.properties in resources directory. 

#### <a name="samples"></a> CSV/XML with sample data
In resources directory there are 2 sample files with dummy data.

#### <a name="schema"></a> Database stucture
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

#### <a name="ui"></a> User Interface
The application, after it's start, provides user with a simple console interface.
Each command begins with "mt" (MT - Migration Tool).
The list of commands that are available will appear immediately when the app is launched.
This list of commands can also be shown using command "mt help".

#### <a name="launch"></a> How to run this app
There are a few ways to launch this app.

Options 2-4 require maven to be installed ("mvn -version" command should display maven version)

1. First run option is to use IDE (IntelliJ for instance).
2. Another one is to:
    1. Open bash
    2. Go to project root (you are in the right place if you can see "src", "pom.xml" and "README.md")
    3. Run command: "mvn clean package exec:java"
3. If you're using Windows, you can follow instructions below:
    1. Open bash
    2. Go to project root (you are in the right place if you can see "src", "pom.xml" and "README.md")
    3. Run command: "mvn clean package" which will build the project
    4. Go to just created target folder and find run-cmd.bat or run-sh.bat file and double click it. The first one will launch app in Windows cmd, the second one will launch git bash using %gitdirectory%/Git/bin/sh.exe  
    
     *Achtung! This one may not work properly if your file names/directories use characters that are not in ASCII table. E.g. if you want to migrate C:/śląsk/klienty.csv, then sh.exe and cmd may not be able to recognize ś and ą letters. In this case, you can use the approach presented in next point.*    
4. If there are problems with previous launch methods (f.e. due to non-ascii characters in your file names) then you should:
    1. Launch git bash using %git-directory%/Git/git-bash.exe file
    2. Go to project root (you are in the right place if you can see "src", "pom.xml" and "README.md")
    3. Run command: "mvn clean package"
    4. Run command: "export MAVEN_OPTS=-Dfile.encoding=utf-8"
    5. Run command: "mvn exec:java"