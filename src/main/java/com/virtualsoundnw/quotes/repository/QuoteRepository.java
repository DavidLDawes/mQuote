package com.virtualsoundnw.quotes.repository;

import com.virtualsoundnw.quotes.domain.Quote;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Quote entity.
 */
@SuppressWarnings("unused")
public interface QuoteRepository extends MongoRepository<Quote,String> {

}
