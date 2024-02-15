package io.adak;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.adak.entity.PrimaryEntity;
import io.adak.entity.SecondaryEntity;
import io.adak.primary.repository.PrimaryRepository;
import io.adak.secondary.repository.SecondaryRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableConfigurationProperties
public class DualMongoConfigApplication implements CommandLineRunner {
	
	@Autowired
	private PrimaryRepository primaryRepository;

	@Autowired
	private SecondaryRepository secondaryRepository;

	public static void main(String[] args) {
		SpringApplication.run(DualMongoConfigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		primaryRepository.save(new PrimaryEntity(null, "Primary database object"));
//		secondaryRepository.save(new SecondaryEntity(null, "Secondary database object")).subscribe();
		secondaryRepository.save(new SecondaryEntity(null, "Secondary database object"));

		List<PrimaryEntity> primaries = primaryRepository.findAll();
		for (PrimaryEntity primary : primaries) {
			System.out.println(primary.toString());
		}

//		Flux<SecondaryEntity> secondaries = secondaryRepository.findAll();
//		secondaries.subscribe(secondary -> System.out.println(secondary.toString()));
		Iterable<SecondaryEntity> secondaries = secondaryRepository.findAll();
		secondaries.forEach(secondary -> System.out.println(secondary.toString()));
	}
}
