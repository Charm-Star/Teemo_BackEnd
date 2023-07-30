package com.example.teemo_backend.Repository;

import com.example.teemo_backend.Domain.Entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production,Long> {



    List<Production> findAllByUserId(long longs);
}
