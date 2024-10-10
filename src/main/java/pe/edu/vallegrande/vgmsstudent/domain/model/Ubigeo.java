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
@Document(collection = "ubigeos")
public class Ubigeo {
    @Id
    private String idUbigeo;        // ID del ubigeo
    private String ubigeoReniec;    // Código RENIEC
    private String department;       // Departamento
    private String province;         // Provincia
    private String district;         // Distrito
    private String region;           // Región
    private String status;           // Estado (A, I)
}
