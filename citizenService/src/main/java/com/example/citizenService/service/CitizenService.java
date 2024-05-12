package com.example.citizenService.service;

import com.example.citizenService.repo.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {
    @Autowired
    CitizenRepo citizenRepo;
}
