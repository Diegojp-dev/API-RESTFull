package services;

import com.api.rest.exceptions.RequireObjectIsNullException;
import com.api.rest.model.Person;

import com.api.rest.repositories.PersonRepository;

import com.api.rest.services.PersonService;

import mocks.MockPerson;
import com.api.rest.vo.PersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindAll() {
        List<Person> persons = input.mockListEntity();

        when(repository.findAll()).thenReturn(persons);


        var result = service.findAll();
        var personOne = result.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("FirstName1", personOne.getFirstName() );
        assertEquals("LastName1", personOne.getLastName());
        assertEquals("Address1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());

        var personFour = result.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("FirstName4", personFour.getFirstName() );
        assertEquals("LastName4", personFour.getLastName());
        assertEquals("Address4", personFour.getAddress());
        assertEquals("Male", personFour.getGender());

        var personSeven = result.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("FirstName7", personSeven.getFirstName() );
        assertEquals("LastName7", personSeven.getLastName());
        assertEquals("Address7", personSeven.getAddress());
        assertEquals("Female", personSeven.getGender());
    }

    @Test
    void testFindById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("FirstName1", result.getFirstName() );
        assertEquals("LastName1", result.getLastName());
        assertEquals("Address1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreate() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("FirstName1", result.getFirstName() );
        assertEquals("LastName1", result.getLastName());
        assertEquals("Address1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdate() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.getReferenceById(vo.getKey())).thenReturn(person);
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("FirstName1", result.getFirstName() );
        assertEquals("LastName1", result.getLastName());
        assertEquals("Address1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testDelete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);

    }

    @Test
    void testCreateWithNullBook() {

        Exception exception = assertThrows(RequireObjectIsNullException.class, () -> service.create(null) );
        String expectedMessage = "It is not allowes to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testUpdateWithNullBook() {

        Exception exception = assertThrows(RequireObjectIsNullException.class, () -> service.update(null) );
        String expectedMessage = "It is not allowes to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

}