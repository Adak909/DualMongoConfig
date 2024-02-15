package io.adak.secondary.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.adak.entity.SecondaryEntity;

@Repository	
public interface SecondaryReactiveRepository extends ReactiveMongoRepository<SecondaryEntity, String> {

}
