package pe.edu.vallegrande.vgmsstudent.presentation.controller;

import org.springframework.web.bind.annotation.*;

import pe.edu.vallegrande.vgmsstudent.application.service.StudentService;
import pe.edu.vallegrande.vgmsstudent.application.service.UbigeoService;
import pe.edu.vallegrande.vgmsstudent.domain.dto.StudentDTO;
import pe.edu.vallegrande.vgmsstudent.domain.model.Student;
import pe.edu.vallegrande.vgmsstudent.domain.model.Ubigeo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/management/${api.version}/student")
public class StudentController {

    private final StudentService studentService;
    private final UbigeoService ubigeoService;

    public StudentController(StudentService studentService, UbigeoService ubigeoService) {
        this.studentService = studentService;
        this.ubigeoService = ubigeoService;
    }

    @PostMapping("/create")
    public Mono<Student> create(@RequestBody StudentDTO studentDTO) {
        Mono<Ubigeo> birthUbigeoMono = ubigeoService.findByUbigeoReniec(studentDTO.getUbigeoBirth());
        Mono<Ubigeo> residenceUbigeoMono = ubigeoService.findByUbigeoReniec(studentDTO.getUbigeoResidence());

        return Mono.zip(birthUbigeoMono, residenceUbigeoMono)
                .flatMap(tuple -> {
                    Ubigeo birthUbigeo = tuple.getT1(); 
                    Ubigeo residenceUbigeo = tuple.getT2(); 

                    Student student = new Student();
                    student.setLastNamePaternal(studentDTO.getLastNamePaternal());
                    student.setLastNameMaternal(studentDTO.getLastNameMaternal());
                    student.setNames(studentDTO.getNames());
                    student.setDocumentType(studentDTO.getDocumentType());
                    student.setDocumentNumber(studentDTO.getDocumentNumber());
                    student.setSex(studentDTO.getSex());
                    student.setBirthDate(studentDTO.getBirthDate());
                    student.setBirthCountry(studentDTO.getBirthCountry());
                    student.setUbigeoBirth(birthUbigeo);
                    student.setUbigeoResidence(residenceUbigeo); 
                    student.setEmail(studentDTO.getEmail());
                    student.setPhoneNumber(studentDTO.getPhoneNumber());
                    student.setMaritalStatus(studentDTO.getMaritalStatus());
                    student.setEducationLevel(studentDTO.getEducationLevel());
                    student.setDisability(studentDTO.getDisability());
                    student.setDisabilityType(studentDTO.getDisabilityType());
                    student.setInternetAccess(studentDTO.getInternetAccess());
                    student.setEmployed(studentDTO.getEmployed());
                    student.setOccupation(studentDTO.getOccupation());
                    student.setNativeLanguage(studentDTO.getNativeLanguage());
                    student.setAddress(studentDTO.getAddress()); 

                    return studentService.create(student);
                })
                .doOnError(e -> {
                    System.err.println("Error al crear el estudiante: " + e.getMessage());
                });
    }

    @PutMapping("/update/{id}")
    public Mono<Student> update(@PathVariable String id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @PutMapping("/activate/{id}")
    public Mono<Student> activate(@PathVariable String id) {
        return studentService.changeStatus(id, "A");
    }

    @PutMapping("/inactive/{id}")
    public Mono<Student> deactivate(@PathVariable String id) {
        return studentService.changeStatus(id, "I");
    }

    @GetMapping("/list/{id}")
    public Mono<Student> getById(@PathVariable String id) {
        return studentService.getById(id);
    }


    @GetMapping("/list/active")
    public Flux<Student> getAllActive() {
        return studentService.finByStatus("A");
    }

    @GetMapping("/list/inactive")
    public Flux<Student> getAllInactive() {
        return studentService.finByStatus("I");
    }

    @GetMapping("/list/document/{documentNumber}")
    public Mono<Student> getByDocumentNumber(@PathVariable String documentNumber) {
        return studentService.getByDocumentNumber(documentNumber);
    }
}
