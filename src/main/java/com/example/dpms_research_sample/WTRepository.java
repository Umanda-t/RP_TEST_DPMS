package com.example.dpms_research_sample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WTRepository extends JpaRepository<WT,Long> {

    @Query("SELECT count(w.weight)FROM WT w WHERE w.username = :username")
    public int WFindcount(@Param("username") User username);

    @Query("SELECT SUM(w.weight)FROM WT w WHERE w.username = :username")
    public float WFindSum(@Param("username") User username);

    @Query("SELECT w FROM WT w WHERE w.username = :username ORDER BY w.date DESC")
    public Page<WT> WsearchList(@Param("username") User username, Pageable pageable);
}
