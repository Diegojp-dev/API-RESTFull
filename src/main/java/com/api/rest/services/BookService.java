package com.api.rest.services;

import com.api.rest.controllers.BookController;
import com.api.rest.exceptions.RequireObjectIsNullException;
import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.mapper.DozerMapper;
import com.api.rest.model.Book;
import com.api.rest.repositories.BookRepository;
import com.api.rest.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookVO findById(Long id){
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO vo = DozerMapper.parseObject(book, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public List<BookVO> finAll(){
        List<BookVO> books = DozerMapper.parseListObjects(repository.findAll(),BookVO.class);
        for(BookVO vo : books){
            vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        }
        return books;
    }

    public BookVO create (BookVO vo){
        if(vo == null) throw new RequireObjectIsNullException();
        Book entity = DozerMapper.parseObject(vo,Book.class);
        BookVO persisted = DozerMapper.parseObject(repository.save(entity),BookVO.class);
        persisted.add(linkTo(methodOn(BookController.class).findById(persisted.getKey())).withSelfRel());
        return persisted;
    }

    public BookVO update(BookVO vo){
        if(vo == null) throw new RequireObjectIsNullException();
        Book entity = repository.getReferenceById(vo.getKey());
        entity.setAuthor(vo.getAuthor());
        entity.setLaunchDate(vo.getLaunchDate());
        entity.setPrice(vo.getPrice());
        entity.setTitle(vo.getTitle());
        vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete (Long id){
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!\""));
        repository.delete(entity);
    }

}
