# Spring-JPA-Mysql
Spring JPA + Mysql example
<ul>
<li>
<p>Buat database test</p>
<pre><code>  mysql> create database test;
</code></pre>
</li>
<li>
<p>Go to https://start.spring.io/ .</p>
<pre><code>
Project Metadata
Group : com.ipung.training.db
Artifact  : demo-spring-mysql
Dependencies : MySQL , Web, JPA
</code></pre>
</li>
<li>
<p>Generate dan ekstrak , lalu import ke dalam eclipse.</p>
</li>
<li>
<p>tambahkan konfigurasi di application.properties. </p>
<pre><code>
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.generate-ddl=true
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto=update
</code></pre>
</li>
<li>
<p>Buat package com.ipung.training.db.model. </p>
</li>
<li>
<p>buat class User
<pre><code>
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
 </code></pre>
</li> 
<li> 
<p>buat package com.ipung.training.db.repository. </p>
</li>
<li> 
<p>buat interface UsersRepository. </p>
<pre><code>
package com.ipung.training.db.repository;
  
  public interface UsersRepository extends JpaRepository<Users, Integer>{

}
</code></pre>
</li>
<li>
<p>tambahkan package com.ipung.training.db.resource. </p>
</li>
<li>
<p>buat class UsersResources
<pre><code>
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
</code></pre>
</li>
<li>
<p>tambahkan di application.properties agar import.sql jalan. </p>
<pre><code>
spring.jpa.hibernate.ddl-auto=create
</code></pre>
</li>
<li>
<p>Jalankan aplikasi tsb, lalu akses di browser http://localhost:8080/rest/users/all </p>
<pre><code>
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
</code></pre>
<li>
<p>jalankan method POST melalui aplikasi Rest Console / Postman. </p>
</li>
<li>
<p>Method POST
<pre><code>
Target : Request URI : http://localhost:8080/rest/users/load
Body : Content-Type : application/json
Request Payload : RAW Body : 
{
  "name" : "tes",
  "teamName" : "baru",
  "salary" : 5000
}
</code></pre>
<pre><code>
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
</code></pre>
<li>
</ul>
  
