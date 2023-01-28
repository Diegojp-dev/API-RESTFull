package com.api.rest.controllers;

import com.api.rest.services.BookService;
import com.api.rest.util.MediaType;
import com.api.rest.vo.BookVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookService service;

    @Operation(summary = "Find a Book",description = "Find a Book" , tags = {"book"},
    responses = {
            @ApiResponse(description = "Success" , responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public BookVO findById(@PathVariable(value = "id")Long id){
        return service.findById(id);
    }

    @Operation(summary = "Find a books", description = "Find a books",tags = {"books"},
    responses = {
            @ApiResponse(description = "Success",responseCode = "200" ,
                    content =  {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                    )}),
            @ApiResponse(description = "Bad Request" , responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public List<BookVO> findAll(){
        return service.finAll();
    }

    @Operation(description = "Insertion a book", summary = "Insertion a book", tags = {"book"},
    responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML} )
    public BookVO create (@RequestBody BookVO vo){
        return service.create(vo);
    }

    @Operation(description = "Updated a Book", summary = "Updated a book",tags = {"book"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
            @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
    })
    @PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public BookVO update (@RequestBody BookVO vo){
        return service.update(vo);
    }

    @Operation(description = "delete a book", summary = "delete a book", tags = {"book"},
    responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized" , responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable(value = "id")Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
