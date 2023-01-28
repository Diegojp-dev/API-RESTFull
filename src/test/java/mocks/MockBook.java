package mocks;

import com.api.rest.model.Book;
import com.api.rest.vo.BookVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity(Integer number){
        Book entity = new Book();
        entity.setId(number.longValue());
        entity.setAuthor("Author"+number);
        entity.setLaunchDate(new Date());
        entity.setPrice(number.doubleValue());
        entity.setTitle("Title"+number);
        return entity;
    }

    public BookVO mockVO(Integer number){
        BookVO entity = new BookVO();
        entity.setKey(number.longValue());
        entity.setAuthor("Author"+number);
        entity.setLaunchDate(new Date());
        entity.setPrice(number.doubleValue());
        entity.setTitle("Title"+number);
        return entity;
    }

    public List<Book> mockListEntity(){
        List<Book> books = new ArrayList<>();
        for(int i = 0; i < 14; i++ ){
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockListVO(){
        List<BookVO> books = new ArrayList<>();
        for(int i = 0; i < 14; i++ ){
            books.add(mockVO(i));
        }
        return books;
    }

}
