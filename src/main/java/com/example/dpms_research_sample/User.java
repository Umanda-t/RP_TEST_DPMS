package com.example.dpms_research_sample;
import javax.persistence.*;
@Entity
@Table(name = "userdb")
public class User {
   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // private Long id;

    @Id
    @Column(name = "username", nullable = false,unique = true, length = 20)
    private String username;

    @Column(name = "email",nullable = false, unique = true, length = 70)
    private String email;

    @Column(name = "password",nullable = false, length = 60)
    private String password;

    //public Long getId() {return id;}

    //public void setId(Long id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
