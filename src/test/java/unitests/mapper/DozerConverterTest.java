package unitests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.api.rest.mapper.DozerMapper;
import mocks.MockPerson;
import com.api.rest.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DozerConverterTest {

    MockPerson input;

    @BeforeEach
    public void setUp(){
        input = new MockPerson();
    }

    @Test
    public void parseVOtoEntityTest(){
        Person output = DozerMapper.parseObject(input.mockVO(), Person.class);
       assertEquals(Long.valueOf(0L),output.getId());
       assertEquals("FirstName0",output.getFirstName());
       assertEquals("LastName0",output.getLastName());
       assertEquals("Address0",output.getAddress());
       assertEquals("Male",output.getGender());
    }

    @Test
    public void parseEntitytoVOTest(){
        Person output = DozerMapper.parseObject(input.mockEntity(), Person.class);
        assertEquals(Long.valueOf(0L),output.getId());
        assertEquals("FirstName0",output.getFirstName());
        assertEquals("LastName0",output.getLastName());
        assertEquals("Address0",output.getAddress());
        assertEquals("Male",output.getGender());
    }

    @Test
    public void parseListVOtoListEntity(){
        List<Person> persons = DozerMapper.parseListObjects(input.mockListVO(),Person.class);

        Person personOne = persons.get(1);
        assertEquals(Long.valueOf(1L),personOne.getId());
        assertEquals("FirstName1",personOne.getFirstName());
        assertEquals("LastName1",personOne.getLastName());
        assertEquals("Address1",personOne.getAddress());
        assertEquals("Female",personOne.getGender());

        Person personFour = persons.get(4);
        assertEquals(Long.valueOf(4L),personFour.getId());
        assertEquals("FirstName4",personFour.getFirstName());
        assertEquals("LastName4",personFour.getLastName());
        assertEquals("Address4",personFour.getAddress());
        assertEquals("Male",personFour.getGender());

        Person personSeven = persons.get(7);
        assertEquals(Long.valueOf(7L),personSeven.getId());
        assertEquals("FirstName7",personSeven.getFirstName());
        assertEquals("LastName7",personSeven.getLastName());
        assertEquals("Address7",personSeven.getAddress());
        assertEquals("Female",personSeven.getGender());

    }

    @Test
    public void parseListEntitytoListVO(){
        List<Person> persons = DozerMapper.parseListObjects(input.mockListEntity(),Person.class);

        Person personOne = persons.get(1);
        assertEquals(Long.valueOf(1L),personOne.getId());
        assertEquals("FirstName1",personOne.getFirstName());
        assertEquals("LastName1",personOne.getLastName());
        assertEquals("Address1",personOne.getAddress());
        assertEquals("Female",personOne.getGender());

        Person personFour = persons.get(4);
        assertEquals(Long.valueOf(4L),personFour.getId());
        assertEquals("FirstName4",personFour.getFirstName());
        assertEquals("LastName4",personFour.getLastName());
        assertEquals("Address4",personFour.getAddress());
        assertEquals("Male",personFour.getGender());

        Person personSeven = persons.get(7);
        assertEquals(Long.valueOf(7L),personSeven.getId());
        assertEquals("FirstName7",personSeven.getFirstName());
        assertEquals("LastName7",personSeven.getLastName());
        assertEquals("Address7",personSeven.getAddress());
        assertEquals("Female",personSeven.getGender());

    }

}
