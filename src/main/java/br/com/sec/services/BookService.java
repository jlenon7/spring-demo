package br.com.sec.services;

import br.com.sec.adapters.DozerAdapter;
import br.com.sec.exception.NotFoundException;
import br.com.sec.models.Book;
import br.com.sec.models.vo.BookVO;
import br.com.sec.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookVO findById(Long id) {
        var entity = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("No records found for this ID"));

        return DozerAdapter.parseObject(entity, BookVO.class);
    }

    public BookVO create(BookVO book) {
        var entity = DozerAdapter.parseObject(book, Book.class);

        return DozerAdapter.parseObject(bookRepository.save(entity), BookVO.class);
    }

    public BookVO update(Long id, BookVO book) {
        BookVO entity = findById(id);

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        return create(entity);
    }

    public void delete(Long id) {
        bookRepository.delete(DozerAdapter.parseObject(findById(id), Book.class));
    }

    public List<BookVO> findAll() {
        return DozerAdapter.parseListObjects(bookRepository.findAll(), BookVO.class);
    }

}