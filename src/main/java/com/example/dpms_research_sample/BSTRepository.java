package com.example.dpms_research_sample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BSTRepository extends JpaRepository<BST,Long> {
    // @Query(value = "SELECT * FROM BST u WHERE u.username like %:username% ", nativeQuery = true)
    @Query("SELECT p FROM BST p WHERE p.username = :username ORDER BY p.date DESC")
    public List<BST> search(@Param("username") User username);


    //@Query("SELECT p FROM BST p WHERE p.username LIKE %?1%")
    @Query("SELECT count(p.bloodsugar)FROM BST p WHERE p.username = :username")
    public int Findcount(@Param("username") User username);

    @Query("SELECT SUM(p.bloodsugar)FROM BST p WHERE p.username = :username")
    public float FindSum(@Param("username") User username);

    @Query("SELECT p FROM BST p WHERE p.username = :username ORDER BY p.date DESC")
    public Page<BST>searchList(@Param("username") User username,Pageable pageable);
}
