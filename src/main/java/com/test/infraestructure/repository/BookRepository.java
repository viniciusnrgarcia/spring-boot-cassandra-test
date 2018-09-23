/**
 * 
 */
package com.test.infraestructure.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.domain.Book;

/**
 * @author vinicius
 *
 */
@Repository
public interface BookRepository extends CassandraRepository<Book, UUID> {

    @Query("select * from book where title = ?0 and publisher=?1 ")
    Iterable<Book> findByTitleAndPublisher(final String title, final String publisher);

}
