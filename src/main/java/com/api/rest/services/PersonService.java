package com.api.rest.services;

import com.api.rest.controllers.PersonController;
import com.api.rest.exceptions.RequireObjectIsNullException;
import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.mapper.DozerMapper;
import com.api.rest.model.Person;
import com.api.rest.repositories.PersonRepository;
import com.api.rest.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonVO findById(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity,PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll(){
        List<PersonVO> listVO = DozerMapper.parseListObjects(repository.findAll(),PersonVO.class);
        for(PersonVO vo : listVO){
            vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        }
        return listVO;
    }

    public PersonVO create (PersonVO vo){
        if(vo == null) throw new RequireObjectIsNullException();
        Person entity = DozerMapper.parseObject(vo,Person.class);
        vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class)).withSelfRel());
        return vo;
    }

    public PersonVO update (PersonVO vo){
        if(vo == null) throw new RequireObjectIsNullException();
        Person entity = repository.getReferenceById(vo.getKey());
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());
        vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}
