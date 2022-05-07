package com.example.dpms_research_sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
   // @Query("SELECT u FROM User u WHERE u.email = ?1")
  //  public User findByEmail(String email);
   @Query("SELECT u FROM User u WHERE u.username = ?1")
   public User findByUsername(String username);
}
