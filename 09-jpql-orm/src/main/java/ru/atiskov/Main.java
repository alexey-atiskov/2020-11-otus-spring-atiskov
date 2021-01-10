package ru.atiskov;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.atiskov.dao.BookDao;
import ru.atiskov.domain.Book;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        BookDao bookDao = context.getBean(BookDao.class);
        System.out.println("All authors " + bookDao.count());
        Book title3 = new Book(2, 2, "title3");
        title3.setBookId(3);
        bookDao.insert(title3);
        System.out.println("All count " + bookDao.count());
        Book titleBook = bookDao.getById(3);
        System.out.println("Ivan id: " + titleBook.getBookId() + " name: " + titleBook.getName());
        System.out.println(bookDao.getAll());

        Console.main(args);
    }
}
