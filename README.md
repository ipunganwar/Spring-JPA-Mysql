# Spring JPA + Mysql example
- Buat database test
```
mysql> create database test;
```
- Go to https://start.spring.io/ .
```
Project Metadata
Group : com.ipung.training.db
Artifact  : demo-spring-mysql
Dependencies : MySQL , Web, JPA
```
- Generate dan ekstrak , lalu import ke dalam eclipse.
- tambahkan konfigurasi di application.properties. 
```
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.generate-ddl=true
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto=update
```
- Buat package `com.ipung.training.db.model`. 
- buat class User
```
package com.ipung.training.db.model;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	@Column(name="team_name")
	private String teamName;
	
	@Column(name="salary")
	private Integer salary;
  
  //getter
  //setter
 ``` 
- buat package `com.ipung.training.db.repository`. 
- buat interface UsersRepository. 
```
package com.ipung.training.db.repository;
  
  public interface UsersRepository extends JpaRepository<Users, Integer>{

}
```
- tambahkan 'package com.ipung.training.db.resource' . 
- buat class UsersResources
```
package com.ipung.training.db.resource;

@RestController
@RequestMapping(value="/rest/users")
public class UsersResources {
	
	@Autowired
	private UsersRepository ur;
	
	@GetMapping(value="/all")
	private List<Users> getAll(){
		return ur.findAll();	
	}
	
	@PostMapping(value="/load")
	private List<Users> insert(@RequestBody final Users users){
		ur.save(users);
		return ur.findAll();
	}

}
```
- tambahkan di application.properties agar import.sql jalan.
```
spring.jpa.hibernate.ddl-auto=create
```
- Jalankan aplikasi tsb, lalu akses di browser http://localhost:8080/rest/users/all 
```
[ {
  "id" : 1,
  "name" : "Ipung",
  "teamName" : "Test",
  "salary" : 1000
}, {
  "id" : 2,
  "name" : "Adam",
  "teamName" : "Test",
  "salary" : 3000
} ]
```
- jalankan method POST melalui aplikasi Rest Console / Postman.
- Method POST
```
Target : Request URI : http://localhost:8080/rest/users/load
Body : Content-Type : application/json
Request Payload : RAW Body : 
{
  "name" : "tes",
  "teamName" : "baru",
  "salary" : 5000
}
```
```
[{
    "id": 1,
    "name": "Ipung",
    "teamName": "Test",
    "salary": 1000
}, {
    "id": 2,
    "name": "Adam",
    "teamName": "Test",
    "salary": 3000
}, {
    "id": 3,
    "name": "tes",
    "teamName": "baru",
    "salary": 5000
}]
```

  
