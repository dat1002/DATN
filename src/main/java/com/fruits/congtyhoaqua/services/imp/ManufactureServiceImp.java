package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.ManufactureDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Manufacture;
import com.fruits.congtyhoaqua.repositories.ManufactureRepository;
import com.fruits.congtyhoaqua.services.IManufactureService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        manufactureRepository.delete(manufacture.get());
        return manufacture.get();
    }

    @Override
    public Set<Manufacture> getAllManufacture() {
        Set<Manufacture> manufactures= new HashSet<>(manufactureRepository.findAll());
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
    public Set<Manufacture> getManufactureByName(String name) {
        Set<Manufacture> manufactures= new HashSet<>(manufactureRepository.findAllByNameContaining(name));
        if(manufactures.isEmpty()){
            throw new NotFoundException("No Manufacture");
        }
        return manufactures;
    }
}
