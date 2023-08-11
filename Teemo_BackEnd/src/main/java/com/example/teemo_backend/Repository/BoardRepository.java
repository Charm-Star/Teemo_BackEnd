package com.example.teemo_backend.Repository;

import com.example.teemo_backend.Domain.Entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
