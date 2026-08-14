## Problem One
- I needed to seed my tables for testing purposes, and wanted to streamline the process so I could get back to work sooner.
### The Prompt
- You are a senior software tester. Your task is to populate the tables defined in TestService.java with 3-4 test records each for testing purposes. Use Statements to insert each record.

### The Output
- Updated TestService.java to seed realistic sample data for all three tables using Statement inserts:
- 4 departments
- 4 users
- 4 reimbursements
- I also aligned the table column names with the app’s DAO expectations (department_id, first_name, last_name, reimbursement_id, etc.) so the test data matches the repository SQL.

```java
stmt.execute("CREATE TABLE IF NOT EXISTS reimbursements (" +
                     "reimbursement_id INT PRIMARY KEY AUTO_INCREMENT, " +
                     "amount DOUBLE NOT NULL, " +
                     "description VARCHAR(255), " +
                     "type VARCHAR(20) NOT NULL, " +
                     "status VARCHAR(20) NOT NULL DEFAULT 'pending', " +
                     "author_id INT NOT NULL, " +
                     "resolver_id INT, " +
                     "FOREIGN KEY (author_id) REFERENCES users(id), " +
                     "FOREIGN KEY (resolver_id) REFERENCES users(id));"
);

try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO departments (department_name) VALUES ('Engineering');");
            stmt.executeUpdate("INSERT INTO departments (department_name) VALUES ('Human Resources');");
            stmt.executeUpdate("INSERT INTO departments (department_name) VALUES ('Finance');");
            stmt.executeUpdate("INSERT INTO departments (department_name) VALUES ('Operations');");

            stmt.executeUpdate("INSERT INTO users (username, password, first_name, last_name, role, department_id) VALUES ('jdoe', 'pass123', 'John', 'Doe', 'employee', 1);");
            stmt.executeUpdate("INSERT INTO users (username, password, first_name, last_name, role, department_id) VALUES ('asmith', 'pass456', 'Alice', 'Smith', 'employee', 2);");
            stmt.executeUpdate("INSERT INTO users (username, password, first_name, last_name, role, department_id) VALUES ('bwayne', 'pass789', 'Bruce', 'Wayne', 'manager', 3);");
            stmt.executeUpdate("INSERT INTO users (username, password, first_name, last_name, role, department_id) VALUES ('mlee', 'pass321', 'Mary', 'Lee', 'manager', 4);");

            stmt.executeUpdate("INSERT INTO reimbursements (amount, description, type, status, author_id, resolver_id) VALUES (125.50, 'Flight to client meeting', 'travel', 'pending', 1, 3);");
            stmt.executeUpdate("INSERT INTO reimbursements (amount, description, type, status, author_id, resolver_id) VALUES (42.00, 'Team lunch', 'food', 'approved', 2, 4);");
            stmt.executeUpdate("INSERT INTO reimbursements (amount, description, type, status, author_id, resolver_id) VALUES (850.00, 'Hotel stay for conference', 'lodging', 'denied', 1, 3);");
            stmt.executeUpdate("INSERT INTO reimbursements (amount, description, type, status, author_id, resolver_id) VALUES (19.99, 'Office supplies', 'other', 'pending', 2, 4);");
        }
```

### Reflection
- After reviewing the code, I can say that these are good insert statements. The AI correctly saw that all of the tables had auto-incrementing primary keys and chose to omit adding the IDs to the inserts. The AI also found that my reimbursement creation code was incomplete and auto completed it for me.
- The only thing I need to change in these lines is the resolver ID for the pending reimbursements. Those should be null, as the reimbursement is still pending.

## Problem Two
- I wanted to easily write test cases for the entirety of the service layer.

### The Prompt
- You are a senior software tester. Your task is to test the service layer of this project by writing test cases for the classes present in the service folder. You should be thorough and check any reasonable edge cases. For your database, TestService.java has an h2 database with preseeded data in it for you to use. Please do not create any redundant test cases, as that may artificially inflate the duration of a test run. Please create the tests within TestService.java

### The Output
- Added service-layer tests directly inside TestService.java, covering:
- DepartmentService valid lookup and invalid ID
- UserService valid login, wrong-password rejection, duplicate username rejection, invalid department rejection, successful registration
- ReimbursementService create, update, resolve approval flow, and status filtering
- I kept the cases focused and non-redundant, using the existing H2 seeded data in setup.
```java
@Test
    public void departmentService_queryDepartmentByDepartmentId_returnsDepartment() {
        Department department = departmentService.queryDepartmentByDepartmentId(1);

        assertNotNull(department);
        assertEquals(1, department.getDepartmentId());
        assertEquals("Engineering", department.getDepartmentName());
    }

    @Test
    public void departmentService_queryDepartmentByDepartmentId_rejectsNonPositiveId() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> departmentService.queryDepartmentByDepartmentId(0)
        );

        assertTrue(exception.getMessage().contains("negative or zero"));
    }

    @Test
    public void userService_login_acceptsValidCredentials_andRejectsWrongPassword() {
        User validUser = userService.login("jdoe", "pass123");
        User invalidUser = userService.login("jdoe", "wrongPassword");

        assertNotNull(validUser);
        assertEquals("John", validUser.getFirstName());
        assertNull(invalidUser);
    }

    @Test
    public void userService_register_rejectsDuplicateUsername_andInvalidDepartment() {
        User duplicateUser = new Employee(0, "jdoe", "AnotherPass1!", "Jane", "Doe", 1);
        IllegalArgumentException duplicateException = assertThrows(
                IllegalArgumentException.class,
                () -> userService.register(duplicateUser)
        );
        assertTrue(duplicateException.getMessage().contains("Username already exists"));

        User invalidDepartmentUser = new Manager(0, "newmanager", "AnotherPass1!", "Sam", "Lane", 999);
        IllegalArgumentException departmentException = assertThrows(
                IllegalArgumentException.class,
                () -> userService.register(invalidDepartmentUser)
        );
        assertTrue(departmentException.getMessage().contains("Department ID does not exist"));
    }

    @Test
    public void userService_register_createsUser_withHashedPassword() {
        User created = userService.register(new Employee(0, "newemployee", "StrongPass1!", "Nina", "Hart", 1));

        assertNotNull(created);
        assertEquals("newemployee", created.getUsername());
        assertNotEquals("StrongPass1!", created.getPassword());

        User loggedIn = userService.login("newemployee", "StrongPass1!");
        assertNotNull(loggedIn);
        assertEquals("Nina", loggedIn.getFirstName());
    }

    @Test
    public void reimbursementService_createReimbursement_setsPendingStatus_andAuthorId() {
        Reimbursement reimbursement = new Reimbursement(0, 200.00, "Client travel", Type.TRAVEL, null, 0, 0);
        User author = new Employee(1, "jdoe", "pass123", "John", "Doe", 1);

        Reimbursement created = reimbursementService.createReimbursement(reimbursement, author);

        assertNotNull(created);
        assertEquals(Status.PENDING, created.getStatus());
        assertEquals(author.getUserId(), created.getAuthorId());
    }

    @Test
    public void reimbursementService_updateReimbursement_rejectsNonPositiveId_andClosedClaims() {
        Reimbursement pending = new Reimbursement(0, 75.00, "Fuel receipt", Type.TRAVEL, Status.PENDING, 0, 0);
        Reimbursement created = reimbursementService.createReimbursement(pending, new Employee(1, "jdoe", "pass123", "John", "Doe", 1));

        created.setDescription("Fuel and parking");
        created.setAmount(90.00);
        Reimbursement updated = reimbursementService.updateReimbursement(created);

        assertEquals("Fuel and parking", updated.getDescription());
        assertEquals(90.00, updated.getAmount());

        IllegalArgumentException invalidIdException = assertThrows(
                IllegalArgumentException.class,
                () -> reimbursementService.updateReimbursement(new Reimbursement(0, 10, "Bad", Type.FOOD, Status.PENDING, 1, 0))
        );
        assertTrue(invalidIdException.getMessage().contains("Reimbursement ID cannot be negative or zero"));
    }

    @Test
    public void reimbursementService_resolveReimbursement_rejectsPendingStatus_andApprovesClaim() {
        User manager = new Manager(3, "bwayne", "pass789", "Bruce", "Wayne", 3);

        IllegalArgumentException pendingException = assertThrows(
                IllegalArgumentException.class,
                () -> reimbursementService.resolveReimbursement(1, manager, Status.PENDING)
        );
        assertTrue(pendingException.getMessage().contains("PENDING"));

        Reimbursement approved = reimbursementService.resolveReimbursement(1, manager, Status.APPROVED);
        assertEquals(Status.APPROVED, approved.getStatus());
        assertEquals(manager.getUserId(), approved.getResolverId());
    }

    @Test
    public void reimbursementService_queryReimbursementsByAuthorId_filtersByStatus() {
        List<Reimbursement> allByAuthor = reimbursementService.queryReimbursementsByAuthorId(1, null);
        List<Reimbursement> pendingByAuthor = reimbursementService.queryReimbursementsByAuthorId(1, Status.PENDING);

        assertEquals(2, allByAuthor.size());
        assertEquals(1, pendingByAuthor.size());
        assertEquals(Status.PENDING, pendingByAuthor.get(0).getStatus());
    }
```

### Reflection
- The tests cover most of the basic functionality I was hoping to cover in the unit tests. I would have liked the AI to cover edge cases as well, but I feel as if edge cases fall under the category of invalid inputs well enough that I don't see the immediate need to alter anything.