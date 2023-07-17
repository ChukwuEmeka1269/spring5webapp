package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoostrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BoostrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address orelope = new Address("22 idimu road", "Egbeda", "Lagos", "1110011");
        Publisher jendol = new Publisher("Jendol Publishers", orelope);
        publisherRepository.save(jendol);

        Author benson = new Author("Benson", "Swag");
        Book ddd = new Book("Domain Driven Design", "bsn12345");

        benson.getBooks().add(ddd);
        ddd.getAuthors().add(benson);
        ddd.setPublisher(jendol);
        jendol.getBooks().add(ddd);


        authorRepository.save(benson);
        bookRepository.save(ddd);

        Author burna = new Author("Damini", "Ogulu");
        Book edd = new Book("Event Driven Design", "bna12345");

        burna.getBooks().add(edd);
        edd.getAuthors().add(burna);
        edd.setPublisher(jendol);
        jendol.getBooks().add(edd);

        authorRepository.save(burna);
        bookRepository.save(edd);

        System.out.println("Bootstrapping application data..........");
        System.out.println("Number of book in database: " + bookRepository.count());
        System.out.println("Publisher count : " + publisherRepository.count());
        System.out.println("Jendol Publisher Address: " + publisherRepository.getPublisherAddress("Jendol Publishers"));
        System.out.println("Number of books published by Jendol Publishers : " + publisherRepository.getCountOfBooksByPublisher("Jendol Publishers"));

    }
}
