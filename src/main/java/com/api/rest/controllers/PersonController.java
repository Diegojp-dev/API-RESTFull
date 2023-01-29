package com.api.rest.controllers;

import com.api.rest.services.PersonService;
import com.api.rest.util.MediaType;
import com.api.rest.vo.BookVO;
import com.api.rest.vo.PersonVO;
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
@RequestMapping(value = "/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @CrossOrigin(origins = "htpp://localhost:8080")
    @Operation(summary = "Find a person",description = "Find a person" , tags = {"person"},
            responses = {
                    @ApiResponse(description = "Success" , responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content",responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable(value = "id")Long id){
        return service.findById(id);
    }

    @Operation(summary = "Find all persons",description = "Find all persons" , tags = {"person"},
            responses = {
                    @ApiResponse(description = "Success" , responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema( schema = @Schema(implementation = BookVO.class))
                                    )}),
                    @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
            })
    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public List<PersonVO> findAll(){
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080" )
    @Operation(summary = "created new person",description = "created new person" , tags = {"person"},
            responses = {
                    @ApiResponse(description = "Created" , responseCode = "201", content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
            })
    @PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
    consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO vo){
        return service.create(vo);
    }

    @Operation(summary = "updates a person",description = "updates a person" , tags = {"person"},
            responses = {
                    @ApiResponse(description = "Success" , responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
            })
    @PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
    consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO vo){
        return service.update(vo);
    }

    @Operation(summary = "delete a person",description = "delete a person" , tags = {"person"},
            responses = {
                    @ApiResponse(description = "No content",responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
                    @ApiResponse(description = "unauthorized" , responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found" , responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error" , responseCode = "500", content = @Content)
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable(value = "id")Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
