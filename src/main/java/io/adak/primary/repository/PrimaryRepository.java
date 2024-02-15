package io.adak.primary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.adak.entity.PrimaryEntity;

@Repository
public interface PrimaryRepository extends MongoRepository<PrimaryEntity, String> {

}
