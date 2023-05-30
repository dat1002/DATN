package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.ManufactureDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Manufacture;
import com.fruits.congtyhoaqua.repositories.ManufactureRepository;
import com.fruits.congtyhoaqua.services.IManufactureService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManufactureServiceImp implements IManufactureService {
    @Autowired private ManufactureRepository manufactureRepository;

    @Override
    public Manufacture createManufacture(ManufactureDTO manufactureDTO) {
        // thieu try catch
        Manufacture manufacture = new Manufacture();
        Convert.fromManufactureDTOToManufacture(manufactureDTO,manufacture);
        return manufactureRepository.save(manufacture);
    }

    @Override
    public Manufacture editManufacture(Integer idManufacture, ManufactureDTO manufactureDTO) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(idManufacture);
        if(manufacture.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        return manufactureRepository.save(Convert.fromManufactureDTOToManufacture(manufactureDTO,manufacture.get()));
    }

    @Override
    public Manufacture deleteManufacture(Integer idManufacture) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(idManufacture);
        if(manufacture.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        manufacture.get().setStatus(false);
        manufactureRepository.save(manufacture.get());
        return manufacture.get();
    }

    @Override
    public List<Manufacture> getAllManufacture(Integer start, Integer size) {
        List<Manufacture> manufactures= new ArrayList<>(manufactureRepository.selectAll(start,size));
        if(manufactures.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        return manufactures;
    }

    @Override
    public Manufacture getManufacture(Integer idManufacture) {
        Optional<Manufacture> manufacture = manufactureRepository.findById(idManufacture);
        if(manufacture.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        return manufacture.get();
    }

    @Override
    public List<Manufacture> getManufactureByName(String name) {
        List<Manufacture> manufactures= new ArrayList<>(manufactureRepository.findAllByNameContaining(name));
        if(manufactures.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        return manufactures;
    }
}
