## Problem One
- I need to abide by OOP principles. It is essential to follow SOLID principles as closely as I can to ensure that my code is maintainable and extensible.
### The Prompt
- You are a senior OOP developer. Your task is to audit SOLID principle violations.

### The Output
⚠️ Issues to Address:
1. Single Responsibility Violations
- UserDAOImp - Mixes role-to-class mapping logic. This should be extracted to a factory or strategy pattern
- UserServiceImp - Validation logic scattered throughout methods (could be extracted to a validator)
2. Interface Segregation (I) - Minor issue
- UserDAO interface works, but UserHandler could split authentication and registration into separate interfaces
3. Open/Closed (O) - Needs improvement
- UserDAOImp's hardcoded role checking (if "manager" else "employee") isn't extensible for new roles
- Adding new user roles requires modifying DAO code

### Reflection
- To comply the Single Responsibility Principle, I made a UserFactory class to handle the role-to-class mapping logic. I also created a validation method within the UserServiceImp class to centralize validation logic.
- To address the Open/Closed Principle, the aforementioned UserFactory class allows for easy extension of new user roles without modifying existing code. New roles can be added by simply extending the factory.


## Problem Two
- I need to abide by REST principles. This ensures that my API is stateless, cacheable, and has a uniform interface.

### The Prompt
- You are a senior REST developer. Your task is to audit REST principle violations.

### The Output
| Issue                        | Location                                                   | Problem                                                                              | Impact                                                            | Fix                                                |
| ---------------------------- | ---------------------------------------------------------- | ------------------------------------------------------------------------------------ | ----------------------------------------------------------------- | -------------------------------------------------- |
| Inconsistent Response Format | `ReimbursementHandlerImp.java` line 44–46                  | Returns empty list as plain string instead of JSON array                             | Client receives `"No reimbursements found."` instead of `[]` JSON | Always use `ctx.json()` for consistency            |
| POST Doesn't Return Resource | `ReimbursementHandlerImp.java` line 25                     | `createReimbursement` returns a string message, not the created object               | Client can't get the ID of created reimbursement                  | Return `ctx.json(createdReimbursement)`            |
| Missing Path Validation      | `App.java` line 50, `ReimbursementHandlerImp.java` line 52 | `ctx.pathParam("userId")` passed directly to `Integer.parseInt()` without null check | Invalid path throws `NumberFormatException` instead of HTTP 400   | Add try-catch or validate `pathParam` exists first |

### Reflection
- To address the inconsistent response format, I ensured that all responses are returned as JSON using `ctx.json()`.
- For the POST method, I modified the `createReimbursement` method to return the created reimbursement object instead of a string message, allowing clients to access the ID of the newly created resource.
- Lastly, I added validation checks for path parameters to prevent exceptions and return appropriate HTTP status codes when invalid input is provided.