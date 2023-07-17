package guru.springframework.spring5webapp.repository;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    @Query("SELECT p.address FROM Publisher p WHERE p.name= :name")
    Address getPublisherAddress(String name);

    @Query("SELECT COUNT(DISTINCT b) FROM Publisher p JOIN p.books b WHERE p.name = :name")
    Long getCountOfBooksByPublisher(String name);
}
