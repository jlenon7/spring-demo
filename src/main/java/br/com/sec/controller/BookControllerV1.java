package br.com.sec.controller;


import br.com.sec.exception.NotFoundException;
import br.com.sec.models.vo.BookVO;
import br.com.sec.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/api/v1/books")
public class BookControllerV1 {
    @Autowired
    private BookService bookService;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> index() {
        List<BookVO> books = bookService.findAll();
        books.forEach(p -> p.add(linkTo(methodOn(BookControllerV1.class).show(p.getKey())).withSelfRel()));

        return books;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO create(@RequestBody BookVO book) {
        BookVO bookVo = bookService.create(book);
        bookVo.add(linkTo(methodOn(BookControllerV1.class).show(bookVo.getKey())).withSelfRel());

        return bookVo;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO show(@PathVariable("id") Long id) throws NotFoundException {
        BookVO bookVo = bookService.findById(id);
        bookVo.add(linkTo(methodOn(BookControllerV1.class).show(id)).withSelfRel());

        return bookVo;
    }

    @PutMapping(value = "/{id}", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO update(@PathVariable("id") Long id, @RequestBody BookVO book) throws NotFoundException {
        BookVO bookVo = bookService.update(id, book);
        bookVo.add(linkTo(methodOn(BookControllerV1.class).show(bookVo.getKey())).withSelfRel());

        return bookVo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        bookService.delete(id);

        return ResponseEntity.ok().build();
    }
}