package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.repositories.DojoRepository;

@Service
public class DojoService {
    private final DojoRepository dojoRepository;
    
    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }
    
    public List<Dojo> allDojos() {
        return dojoRepository.findAll();
    }
    
    public Dojo createDojo(Dojo d) {
        return dojoRepository.save(d);
    }
    
    public Dojo findDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if(optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }
    
    public void deleteDojo(Long id) {
    	dojoRepository.deleteById(id);
    }
    
    public Dojo updateDojo(Long id, String name, Ninja ninja) {
    	Dojo updatedDojo = findDojo(id);
    	if(updatedDojo!=null) {
    		updatedDojo.setName(name);
    		if(ninja!=null) {
    			List<Ninja> ninjaList = updatedDojo.getNinjas();
    			ninjaList.add(ninja);
    			updatedDojo.setNinjas(ninjaList);
    		}
    		dojoRepository.save(updatedDojo);
    	}
    	return updatedDojo;
    }
    
}