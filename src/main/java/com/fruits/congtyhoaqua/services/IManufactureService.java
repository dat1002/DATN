package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.ManufactureDTO;
import com.fruits.congtyhoaqua.models.Manufacture;

import java.util.Set;

public interface IManufactureService {
    Manufacture createManufacture(ManufactureDTO manufactureDTO);
    Manufacture editManufacture(Integer idManufacture, ManufactureDTO manufactureDTO);
    Manufacture deleteManufacture(Integer idManufacture);
    Set<Manufacture> getAllManufacture();
    Manufacture getManufacture(Integer idManufacture);
    Set<Manufacture> getManufactureByName(String name);
}
