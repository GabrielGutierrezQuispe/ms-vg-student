package pe.edu.vallegrande.vgmsstudent.application.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.vallegrande.vgmsstudent.application.service.StudentService;
import pe.edu.vallegrande.vgmsstudent.domain.model.Student;
import pe.edu.vallegrande.vgmsstudent.domain.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Mono<Student> create(Student student) {
        student.setStatus("A");
        log.info("Creando Estudiante: {}", student);
        return studentRepository.save(student);
    }

    public Mono<Student> update(String id, Student student) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setLastNamePaternal(student.getLastNamePaternal());
                    existingStudent.setLastNameMaternal(student.getLastNameMaternal());
                    existingStudent.setNames(student.getNames());
                    existingStudent.setDocumentType(student.getDocumentType());
                    existingStudent.setDocumentNumber(student.getDocumentNumber());
                    existingStudent.setSex(student.getSex());
                    existingStudent.setBirthDate(student.getBirthDate());
                    existingStudent.setBirthCountry(student.getBirthCountry());
                    existingStudent.setUbigeoBirth(student.getUbigeoBirth()); // Asegúrate de que esto sea un Ubigeo
                    existingStudent.setUbigeoResidence(student.getUbigeoResidence()); // Asegúrate de que esto sea un Ubigeo
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setPhoneNumber(student.getPhoneNumber());
                    existingStudent.setMaritalStatus(student.getMaritalStatus());
                    existingStudent.setEducationLevel(student.getEducationLevel());
                    existingStudent.setDisability(student.getDisability());
                    existingStudent.setDisabilityType(student.getDisabilityType());
                    existingStudent.setInternetAccess(student.getInternetAccess());
                    existingStudent.setEmployed(student.getEmployed());
                    existingStudent.setOccupation(student.getOccupation());
                    existingStudent.setNativeLanguage(student.getNativeLanguage());
                    existingStudent.setAddress(student.getAddress());
                    existingStudent.setStatus("A");
                    log.info("Actualizando Estudiante: {}", existingStudent);
                    return studentRepository.save(existingStudent);
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
