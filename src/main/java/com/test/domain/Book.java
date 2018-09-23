/**
 * 
 */
package com.test.domain;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author vinicius
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(value = "BOOK")
public class Book {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;

    @PrimaryKeyColumn(name = "title", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String title;
    private String name;
    private String author;
    private String subject;

    // @PrimaryKeyColumn(name = "publisher", ordinal = 2, type =
    // PrimaryKeyType.PARTITIONED)
    // private String publisher;

}
