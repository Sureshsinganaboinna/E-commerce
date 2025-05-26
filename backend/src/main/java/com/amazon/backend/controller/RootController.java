package com.amazon.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

//	@GetMapping("/")
//	public String testAPi() {
//		return "teesting Api's";
//	}
//	
	
	
/*	

	---

	#### Entity Class
	```java
	@Entity
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Table(name = "employees")
	public class Employee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String department;
	}
	```

	---

	#### Repository
	```java
	@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	}
	```

	---

	#### JWT Util
	```java
	@Component
	public class JwtUtil {
	    private static final String SECRET_KEY = "secret";
	    
	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	                .compact();
	    }

	    public boolean validateToken(String token, String username) {
	        return username.equals(getUsername(token)) && !isTokenExpired(token);
	    }

	    public String getUsername(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	    }

	    private boolean isTokenExpired(String token) {
	        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
	        return expiration.before(new Date());
	    }
	}
	```

	---

	#### Service Layer
	```java
	@Service
	@RequiredArgsConstructor
	public class EmployeeService {

	    private final EmployeeRepository employeeRepository;

	    @Cacheable(value = "employees", key = "#id")
	    public Employee getEmployeeById(Long id) {
	        return employeeRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
	    }

	    public Employee saveEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public Employee updateEmployee(Long id, Employee updatedEmployee) {
	        Employee employee = getEmployeeById(id);
	        employee.setName(updatedEmployee.getName());
	        employee.setEmail(updatedEmployee.getEmail());
	        employee.setDepartment(updatedEmployee.getDepartment());
	        return employeeRepository.save(employee);
	    }

	    public void deleteEmployee(Long id) {
	        employeeRepository.deleteById(id);
	    }
	}
	```

	---

	#### Controller Layer
	```java
	@RestController
	@RequestMapping("/employees")
	@RequiredArgsConstructor
	public class EmployeeController {

	    private final EmployeeService employeeService;
	    private final JwtUtil jwtUtil;

	    @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployee(@PathVariable Long id, @RequestHeader("Authorization") String token) {
	        if (jwtUtil.validateToken(token.substring(7), "admin")) {
	            return ResponseEntity.ok(employeeService.getEmployeeById(id));
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    @PostMapping
	    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
	        return ResponseEntity.ok(employeeService.saveEmployee(employee));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
	        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	        employeeService.deleteEmployee(id);
	        return ResponseEntity.noContent().build();
	    }
	}
	```

	---

	### Next Step
	Do you want to move to **FeignClient in Department Service** 🔗 or **AWS S3 File Upload** 📁?


			
			
			
			
			
		
			```

			---

			#### Entity Class
			```java
			@Entity
			@Data
			@AllArgsConstructor
			@NoArgsConstructor
			@Table(name = "departments")
			public class Department {
			    @Id
			    @GeneratedValue(strategy = GenerationType.IDENTITY)
			    private Long id;
			    private String name;
			    private String location;
			}
			```

			---

			#### Repository
			```java
			@Repository
			public interface DepartmentRepository extends JpaRepository<Department, Long> {
			}
			```

			---

			#### FeignClient Interface
			```java
			@FeignClient(name = "employee-service", url = "http://localhost:8080/employees")
			public interface EmployeeClient {

			    @GetMapping("/{id}")
			    Employee getEmployeeById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
			}
			```

			---

			#### Service Layer
			```java
			@Service
			@RequiredArgsConstructor
			public class DepartmentService {

			    private final DepartmentRepository departmentRepository;
			    private final EmployeeClient employeeClient;

			    public Department saveDepartment(Department department) {
			        return departmentRepository.save(department);
			    }

			    public List<Department> getAllDepartments() {
			        return departmentRepository.findAll();
			    }

			    public Employee getEmployeeByDepartment(Long employeeId, String token) {
			        return employeeClient.getEmployeeById(employeeId, token);
			    }
			}
			```

			---

			#### Controller Layer
			```java
			@RestController
			@RequestMapping("/departments")
			@RequiredArgsConstructor
			public class DepartmentController {

			    private final DepartmentService departmentService;

			    @PostMapping
			    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
			        return ResponseEntity.ok(departmentService.saveDepartment(department));
			    }

			    @GetMapping
			    public ResponseEntity<List<Department>> getAllDepartments() {
			        return ResponseEntity.ok(departmentService.getAllDepartments());
			    }

			    @GetMapping("/employee/{id}")
			    public ResponseEntity<Employee> getEmployeeByDepartment(@PathVariable Long id, @RequestHeader("Authorization") String token) {
			        return ResponseEntity.ok(departmentService.getEmployeeByDepartment(id, token));
			    }
			}
			```

			---

			### Next Step
			Do you want to proceed with **AWS S3 File Upload Service** 📁 or **Docker Containerization** 🐳?

			
			
			
			*/
			
			
			
			
			
			
			
			
}







