/**
 * 
 */
package com.test.infraestructure.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.test.domain.Book;
import com.test.infraestructure.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vinicius
 *
 */
@Slf4j
@Configuration
@EnableCassandraRepositories(basePackageClasses = { Book.class, BookRepository.class })
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Autowired
    private transient Environment environment;


    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(this.environment.getProperty("spring.data.cassandra.contact-points"));
        cluster.setPort(Integer.parseInt(this.environment.getProperty("spring.data.cassandra.port")));

        cluster.setKeyspaceCreations(Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(this.environment.getProperty("spring.data.cassandra.keyspace-name"))
                .withSimpleReplication().ifNotExists()));

        log.info("Cluster created with contact points ["
                + this.environment.getProperty("spring.data.cassandra.contact-points") + "] " + "& port ["
                + Integer.parseInt(this.environment.getProperty("spring.data.cassandra.port")) + "].");

        return cluster;
    }
    
    @Override
    protected String getKeyspaceName() {
        return this.environment.getProperty("spring.data.cassandra.keyspace-name");
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    
//    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
//        List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
//        createKeyspaceSpecifications.add(getKeySpaceSpecification());
//        return createKeyspaceSpecifications;
//    }
    
    // Below method creates "my_keyspace" if it doesnt exist.
//    private CreateKeyspaceSpecification getKeySpaceSpecification() {
//        CreateKeyspaceSpecification pandaCoopKeyspace = new CreateKeyspaceSpecification();
//        DataCenterReplication dcr = new DataCenterReplication("dc1", 1L);
//        pandaCoopKeyspace.name(KEYSPACE);
//        pandaCoopKeyspace.ifNotExists(true).createKeyspace().withNetworkReplication(dcr);
//        return pandaCoopKeyspace;
//    }
    
    /*@Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.test"};
    }*/
    

    // @Bean
    // @Override
    // public CassandraMappingContext cassandraMapping() throws
    // ClassNotFoundException {
    // // BasicCassandraMappingContext()
    // return new CassandraMappingContext();
    // }

}