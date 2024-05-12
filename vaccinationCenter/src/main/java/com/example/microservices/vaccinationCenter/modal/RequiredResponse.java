package com.example.microservices.vaccinationCenter.modal;

import com.example.microservices.vaccinationCenter.entity.VaccinationCenter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredResponse {
    private VaccinationCenter center;
    private List<Citizen> citizens;
}
