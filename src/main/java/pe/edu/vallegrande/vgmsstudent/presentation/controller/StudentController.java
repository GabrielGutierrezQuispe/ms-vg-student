package pe.edu.vallegrande.vgmsstudent.presentation.controller;

import org.springframework.web.bind.annotation.*;

import pe.edu.vallegrande.vgmsstudent.application.service.StudentService;
import pe.edu.vallegrande.vgmsstudent.domain.dto.StudentDTO;
import pe.edu.vallegrande.vgmsstudent.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/management/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Mono<Student> create(@RequestBody StudentDTO studentDTO) {
        return studentService.create(studentDTO)
                .doOnError(e -> {
                    System.err.println("Error al crear el estudiante: " + e.getMessage());
                });
    }

    @PutMapping("/update/{id}")
    public Mono<Student> update(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        return studentService.update(id, studentDTO)
                .doOnError(e -> {
                    System.err.println("Error al actualizar el estudiante: " + e.getMessage());
                });
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
