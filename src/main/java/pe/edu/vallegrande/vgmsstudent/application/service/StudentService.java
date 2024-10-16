package pe.edu.vallegrande.vgmsstudent.application.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.vallegrande.vgmsstudent.domain.dto.StudentDTO;
import pe.edu.vallegrande.vgmsstudent.domain.model.Student;
import pe.edu.vallegrande.vgmsstudent.domain.model.Ubigeo;
import pe.edu.vallegrande.vgmsstudent.domain.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UbigeoService ubigeoService; // Declarar el UbigeoService

    public StudentService(StudentRepository studentRepository, UbigeoService ubigeoService) {
        this.studentRepository = studentRepository;
        this.ubigeoService = ubigeoService; // Inicializar el UbigeoService
    }

    public Mono<Student> create(StudentDTO studentDTO) {
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
                    student.setStatus("A"); // Establecer el estado como activo

                    log.info("Creando Estudiante: {}", student);
                    return studentRepository.save(student);
                })
                .doOnError(e -> {
                    log.error("Error al crear el estudiante: {}", e.getMessage());
                });
    }

    public Mono<Student> update(String id, StudentDTO studentDTO) {
        Mono<Ubigeo> birthUbigeoMono = ubigeoService.findByUbigeoReniec(studentDTO.getUbigeoBirth());
        Mono<Ubigeo> residenceUbigeoMono = ubigeoService.findByUbigeoReniec(studentDTO.getUbigeoResidence());

        return Mono.zip(birthUbigeoMono, residenceUbigeoMono)
                .flatMap(tuple -> {
                    Ubigeo birthUbigeo = tuple.getT1();
                    Ubigeo residenceUbigeo = tuple.getT2();

                    return studentRepository.findById(id)
                            .flatMap(existingStudent -> {
                                existingStudent.setLastNamePaternal(studentDTO.getLastNamePaternal());
                                existingStudent.setLastNameMaternal(studentDTO.getLastNameMaternal());
                                existingStudent.setNames(studentDTO.getNames());
                                existingStudent.setDocumentType(studentDTO.getDocumentType());
                                existingStudent.setDocumentNumber(studentDTO.getDocumentNumber());
                                existingStudent.setSex(studentDTO.getSex());
                                existingStudent.setBirthDate(studentDTO.getBirthDate());
                                existingStudent.setBirthCountry(studentDTO.getBirthCountry());
                                existingStudent.setUbigeoBirth(birthUbigeo);
                                existingStudent.setUbigeoResidence(residenceUbigeo);
                                existingStudent.setEmail(studentDTO.getEmail());
                                existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
                                existingStudent.setMaritalStatus(studentDTO.getMaritalStatus());
                                existingStudent.setEducationLevel(studentDTO.getEducationLevel());
                                existingStudent.setDisability(studentDTO.getDisability());
                                existingStudent.setDisabilityType(studentDTO.getDisabilityType());
                                existingStudent.setInternetAccess(studentDTO.getInternetAccess());
                                existingStudent.setEmployed(studentDTO.getEmployed());
                                existingStudent.setOccupation(studentDTO.getOccupation());
                                existingStudent.setNativeLanguage(studentDTO.getNativeLanguage());
                                existingStudent.setAddress(studentDTO.getAddress());
                                existingStudent.setStatus("A");

                                log.info("Actualizando Estudiante: {}", existingStudent);
                                return studentRepository.save(existingStudent);
                            });
                })
                .doOnError(e -> {
                    log.error("Error al actualizar el estudiante: {}", e.getMessage());
                });
    }

    public Mono<Student> changeStatus(String id, String status) {
        log.info("Cambiando estado de personal educativo con ID: " + id);
        return studentRepository.findById(id)
                .flatMap( st -> {
                    st.setStatus(status);
                    return studentRepository.save(st);
                });
    }

    public Mono<Student> getById(String id) {
        return studentRepository.findById(id);
    }

    public Flux<Student> finByStatus(String status) {
        return studentRepository.findAllByStatus(status);
    }

    public Mono<Student> getByDocumentNumber(String documentNumber) {
        return studentRepository.findByDocumentNumber(documentNumber);
    }
}
