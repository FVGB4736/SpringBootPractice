package com.practice.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	
	
	
	
	
	
	 
	
	//JPA 知道 user_id 對應到 UserEntity 的主鍵，因為：
	//@JoinTable 是在 UserEntity 類中定義的，JPA 知道上下文是 UserEntity。
	//referencedColumnName="id" 明確指向 UserEntity 的 id 欄位，而 id 被 @Id 標記為主鍵。
	//即使不指定 referencedColumnName，JPA 也會默認使用主鍵
	
	//JPA 知道 role_id 對應到 Role 表的主鍵，因為：
	//roles 屬性的類型是 List<Role>，JPA 知道關係的對方是 Role 實體。
	//referencedColumnName="id" 明確指向 Role 的 id 欄位，而 id 被 @Id 標記為 Role 的主鍵。
	//即使不指定 referencedColumnName，JPA 也會默認使用 Role 的主鍵。
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns= {@JoinColumn(name="user_id", referencedColumnName= "id")},
			inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName= "id")}
			)
	private List<Role> roles = new ArrayList<>();

}
