package pe.edu.vallegrande.vgmsstudent.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vgmsstudent.domain.model.Ubigeo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UbigeoRepository extends ReactiveMongoRepository<Ubigeo, String> {
    Mono<Ubigeo> findByUbigeoReniec(String ubigeoReniec); // Método para buscar un ubigeo por su código
    Flux<Ubigeo> findByProvince(String province);
}
