package pe.edu.vallegrande.vgmsstudent.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vgmsstudent.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Flux<Student> findAllByStatus(String status);
    Mono<Student> findByDocumentNumber(String documentNumber);
}
