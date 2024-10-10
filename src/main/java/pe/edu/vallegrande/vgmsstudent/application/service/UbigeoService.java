package pe.edu.vallegrande.vgmsstudent.application.service;

import org.springframework.stereotype.Service;

import pe.edu.vallegrande.vgmsstudent.application.service.UbigeoService;
import pe.edu.vallegrande.vgmsstudent.domain.model.Ubigeo;
import pe.edu.vallegrande.vgmsstudent.domain.repository.UbigeoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UbigeoService {

    private final UbigeoRepository ubigeoRepository;

    public UbigeoService(UbigeoRepository ubigeoRepository) {
        this.ubigeoRepository = ubigeoRepository;
    }

    public Mono<Ubigeo> create(Ubigeo ubigeo) {
        return ubigeoRepository.save(ubigeo);
    }

    public Mono<Ubigeo> update(String id, Ubigeo ubigeo) {
        return ubigeoRepository.findById(id)
                .flatMap(existingUbigeo -> {
                    existingUbigeo.setDepartment(ubigeo.getDepartment());
                    existingUbigeo.setProvince(ubigeo.getProvince());
                    existingUbigeo.setDistrict(ubigeo.getDistrict());
                    existingUbigeo.setRegion(ubigeo.getRegion());
                    return ubigeoRepository.save(existingUbigeo);
                });
    }

    public Flux<Ubigeo> getAll() {
        return ubigeoRepository.findAll();
    }

    public Mono<Ubigeo> findByUbigeoReniec(String ubigeoReniec) {
        return ubigeoRepository.findByUbigeoReniec(ubigeoReniec);
    }

    public Flux<Ubigeo> findByProvince(String province) {
        return ubigeoRepository.findByProvince(province); 
    }
}
