package pe.edu.vallegrande.vgmsstudent.domain.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String documentType;
    private String documentNumber;
    private String lastNamePaternal;
    private String lastNameMaternal;
    private String names;
    private String sex;
    private String birthDate;
    private String birthCountry;
    private String ubigeoBirth; // Código RENIEC como String
    private String ubigeoResidence; // Código RENIEC como String
    private String email;
    private String phoneNumber;
    private String maritalStatus;
    private String educationLevel;
    private Boolean disability;
    private String disabilityType; // Puede ser null
    private Boolean internetAccess;
    private Boolean employed;
    private String occupation;
    private String nativeLanguage;
    private String address;
    private String status; // Opcional
}
