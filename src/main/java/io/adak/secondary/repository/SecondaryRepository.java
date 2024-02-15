package io.adak.secondary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.adak.entity.SecondaryEntity;

@Repository
public interface SecondaryRepository extends MongoRepository<SecondaryEntity, String> {

}
