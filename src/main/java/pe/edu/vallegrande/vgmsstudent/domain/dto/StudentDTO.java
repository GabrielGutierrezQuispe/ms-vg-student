package pe.edu.vallegrande.vgmsstudent.domain.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String lastNamePaternal;
    private String lastNameMaternal;
    private String names;
    private String documentType;
    private String documentNumber;
    private String sex;
    private String birthDate;
    private String birthCountry;
    private String ubigeoBirth; // Código RENIEC para el ubigeo de nacimiento
    private String ubigeoResidence; // Código RENIEC para el ubigeo de residencia
    private String email;
    private String phoneNumber;
    private String maritalStatus;
    private String educationLevel;
    private Boolean disability; // Cambiar a Boolean
    private String disabilityType;
    private Boolean internetAccess; // Cambiar a Boolean
    private Boolean employed; // Cambiar a Boolean
    private String occupation;
    private String nativeLanguage;
    private String address; // Agregado el campo address
}
