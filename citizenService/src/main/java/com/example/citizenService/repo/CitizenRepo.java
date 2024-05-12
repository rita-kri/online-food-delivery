package com.example.citizenService.repo;

import com.example.citizenService.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Integer> {
    public List<Citizen> findByVaccinationCenterId(Integer id);
}
