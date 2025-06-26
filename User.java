package model;

//package com.rbrickks.property.model;

import jakarta.persistence.*; 
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false, unique = true)
	    private String email;

	    @Column(nullable = false)
	    private String password;

		public CharSequence getPassword() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setPassword(String encode) {
			// TODO Auto-generated method stub
			
		}
//
//		public Object getEmail() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public Object getId() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public Object getUsername() {
//			// TODO Auto-generated method stub
//			return null;
//		}

		public Object getId() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getEmail() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getUsername() {
			// TODO Auto-generated method stub
			return null;
		}

}
