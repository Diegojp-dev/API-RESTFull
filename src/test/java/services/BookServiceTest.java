package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.api.rest.exceptions.RequireObjectIsNullException;
import com.api.rest.model.Book;
import com.api.rest.repositories.BookRepository;
import com.api.rest.services.BookService;
import mocks.MockBook;
import com.api.rest.vo.BookVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;



@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindAll() {
        List<Book> books = input.mockListEntity();

        when(repository.findAll()).thenReturn(books);


        var result = service.finAll();
        var bookOne = result.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author1", bookOne.getAuthor() );
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(1.0, bookOne.getPrice());
        assertEquals("Title1", bookOne.getTitle());

        var bookFour = result.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());
        assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertEquals("Author4", bookFour.getAuthor() );
        assertNotNull(bookFour.getLaunchDate());
        assertEquals(4.0, bookFour.getPrice());
        assertEquals("Title4", bookFour.getTitle());

        var bookSeven = result.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());
        assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
        assertEquals("Author7", bookSeven.getAuthor() );
        assertNotNull(bookSeven.getLaunchDate());
        assertEquals(7.0, bookSeven.getPrice());
        assertEquals("Title7", bookSeven.getTitle());
    }

    @Test
    void testFindById() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author1", result.getAuthor() );
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());
    }

    @Test
    void testCreate() {
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(book)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author1", result.getAuthor() );
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());
    }

    @Test
    void testUpdate() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        Book persisted = book;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.getReferenceById(vo.getKey())).thenReturn(book);
        when(repository.save(book)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author1", result.getAuthor() );
        assertNotNull(result.getLaunchDate());
        assertEquals(1.0, result.getPrice());
        assertEquals("Title1", result.getTitle());
    }

    @Test
    void testDelete() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

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