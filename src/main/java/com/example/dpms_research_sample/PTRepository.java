package com.example.dpms_research_sample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PTRepository extends JpaRepository<PT,Long> {
    @Query("SELECT p FROM PT p WHERE p.username = :username ORDER BY p.date DESC")
    public List<PT> psearch(@Param("username") User username);


    @Query("SELECT count(p.systolic)FROM PT p WHERE p.username = :username")
    public int PFindcount(@Param("username") User username);

    @Query("SELECT count(p.diastolic)FROM PT p WHERE p.username = :username")
    public int PDFindcount(@Param("username") User username);

    @Query("SELECT SUM(p.systolic)FROM PT p WHERE p.username = :username")
    public float PFindSum(@Param("username") User username);

    @Query("SELECT SUM(p.diastolic)FROM PT p WHERE p.username = :username")
    public float PDFindSum(@Param("username") User username);

    @Query("SELECT p FROM PT p WHERE p.username = :username ORDER BY p.date DESC")
    public Page<PT> psearchList(@Param("username") User username, Pageable pageable);
}
