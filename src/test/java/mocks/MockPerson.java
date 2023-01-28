package mocks;

import com.api.rest.model.Person;
import com.api.rest.vo.PersonVO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonVO mockVO(){
        return mockVO(0);
    }

    public Person mockEntity( Integer number){
        Person entity = new Person();
        entity.setId(number.longValue());
        entity.setFirstName("FirstName"+number);
        entity.setLastName("LastName"+number);
        entity.setAddress("Address"+number);
        entity.setGender((number % 2 == 0) ? "Male": "Female");
        return entity;
    }

    public PersonVO mockVO(Integer  number){
        PersonVO entity = new PersonVO();
        entity.setKey(number.longValue());
        entity.setFirstName("FirstName"+number);
        entity.setLastName("LastName"+number);
        entity.setAddress("Address"+number);
        entity.setGender((number % 2 == 0) ? "Male" : "Female");
        return entity;
    }

    public List<Person> mockListEntity(){
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 14; i++ ){
            persons.add(mockEntity(i));
        }
        return  persons;
    }

    public List<PersonVO> mockListVO(){
        List<PersonVO>persons = new ArrayList<>();
        for(int i = 0; i < 14; i++ ){
            persons.add(mockVO(i));
        }
        return persons;
    }

}
