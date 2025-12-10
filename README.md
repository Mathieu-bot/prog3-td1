# Product Management JDBC – TD Project

This project is a simple academic exercise designed to practice:

- Java JDBC basics  
- SQL schema and data initialization  
- Object mapping (Product & Category)  
- Filtering and pagination  
- Dynamic SQL queries  

---

##  Project Structure

```
prog3-td1/
│
├── pom.xml
├── sql/
│   ├── schema.sql        # Creates tables
│   ├── data.sql          # Inserts sample data
│   └── init_db.sql       # Creates DB + user
│
└── src/
    ├── main/java/org/example/
    │   ├── Main.java
    │   ├── database/DBConnection.java
    │   ├── model/
    │   │   ├── Product.java
    │   │   └── Category.java
    │   └── service/DataRetriever.java
    │
    └── test/java/org/example/service/
        └── DataRetrieverTest.java
```

---

##  Requirements

- Java 17+
- PostgreSQL installed locally
- Maven

---

##  Running the Project

1. Create the database and user:

Run `init_db.sql`

2. Create tables:

Run `schema.sql`

3. Insert data:

Run `data.sql`

4. Run the application:

```
mvn clean package
java -jar target/prog3-td1.jar
```

---

##  Running Tests

JUnit tests are located in:

```
src/test/java/org/example/service/DataRetrieverTest.java
```

Execute them with:

```
mvn test
```

These are **integration tests**, meaning they use the real database to validate SQL queries, JDBC logic, joins, filtering, and pagination.

---

##  Notes

- The project uses `ILIKE`, `LIMIT`, and dynamic `WHERE` clauses.
- DataRetriever builds SQL queries dynamically based on provided filters.
- DBConnection manages the PostgreSQL connection.
- Test logic validates structure and consistency.

---


For educational purposes only.
