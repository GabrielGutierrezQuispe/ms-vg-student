package pe.edu.vallegrande.vgmsstudent.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private String id;

    private String documentType;          // Tipo de documento de identidad
    private String documentNumber;        // Número de documento de identidad
    private String lastNamePaternal;     // Apellido paterno
    private String lastNameMaternal;      // Apellido materno
    private String names;                 // Nombres
    private String sex;                   // Sexo
    private String birthDate;             // Fecha de nacimiento
    private String birthCountry;          // País de nacimiento

    private Ubigeo ubigeoBirth;            // Ubigeo de nacimiento
    private Ubigeo ubigeoResidence;        // Ubigeo de residencia

    private String email;                  // Correo electrónico
    private String phoneNumber;            // Número de teléfono
    private String maritalStatus;          // Estado civil
    private String educationLevel;         // Grado de instrucción

    private Boolean disability;             // Discapacidad (true/false)
    private String disabilityType;         // Tipo de discapacidad
    private Boolean internetAccess;         // Acceso a Internet (true/false)
    private Boolean employed;               // Trabaja (true/false)
    private String occupation;             // Ocupación/Puesto de trabajo
    private String nativeLanguage;         // Lengua originaria

    private String address;               
    private String status;           
}