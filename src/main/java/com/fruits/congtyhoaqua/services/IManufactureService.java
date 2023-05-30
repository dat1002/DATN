package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.ManufactureDTO;
import com.fruits.congtyhoaqua.models.Manufacture;

import java.util.List;
import java.util.Set;

public interface IManufactureService {
    Manufacture createManufacture(ManufactureDTO manufactureDTO);
    Manufacture editManufacture(Integer idManufacture, ManufactureDTO manufactureDTO);
    Manufacture deleteManufacture(Integer idManufacture);
    List<Manufacture> getAllManufacture(Integer start, Integer size);
    Manufacture getManufacture(Integer idManufacture);
    List<Manufacture> getManufactureByName(String name);
}
