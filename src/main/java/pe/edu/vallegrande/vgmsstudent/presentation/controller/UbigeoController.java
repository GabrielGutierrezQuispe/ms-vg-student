package pe.edu.vallegrande.vgmsstudent.presentation.controller;

import org.springframework.web.bind.annotation.*;

import pe.edu.vallegrande.vgmsstudent.application.service.UbigeoService;
import pe.edu.vallegrande.vgmsstudent.domain.model.Ubigeo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("public/${api.version}/ubigeo")
public class UbigeoController {
    private UbigeoService ubigeoService;

    public UbigeoController(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    @PostMapping("/create")
    public Mono<Ubigeo> create(@RequestBody Ubigeo ubigeo) {
        return ubigeoService.create(ubigeo);
    }

    @PutMapping("/update/{id}")
    public Mono<Ubigeo> update(@PathVariable String id, @RequestBody Ubigeo ubigeo) {
        return ubigeoService.update(id, ubigeo);
    }

    @GetMapping("/list")
    public Flux<Ubigeo> getAll() {
        return ubigeoService.getAll();
    }

    // Método para buscar por ubigeoReniec
    @GetMapping("/list/ubigeoReniec/{ubigeoReniec}")
    public Mono<Ubigeo> getByUbigeoReniec(@PathVariable String ubigeoReniec) {
        return ubigeoService.findByUbigeoReniec(ubigeoReniec); 
    }

    @PostMapping("/search/reniec/{ubigeoReniec}")
    public Mono<Ubigeo> findByUbigeoReniec(@PathVariable String ubigeoReniec) {
        return ubigeoService.findByUbigeoReniec(ubigeoReniec);
    }

    @GetMapping("/list/provincia/{provincia}")
    public Flux<Ubigeo> getUbigeosByProvince(@PathVariable String provincia) {
        return ubigeoService.findByProvince(provincia);
    }

}
