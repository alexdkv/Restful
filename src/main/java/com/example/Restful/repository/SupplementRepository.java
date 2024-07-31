package com.example.Restful.repository;

import com.example.Restful.model.entity.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
}
