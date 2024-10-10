package pe.edu.vallegrande.vgmsstudent.application.service;
/*
package pe.edu.vallegrande.vgmsstudent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import pe.edu.vallegrande.vgmsstudent.model.Ubigeo;
import pe.edu.vallegrande.vgmsstudent.repository.UbigeoRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class UbigeoImportService {

    @Autowired
    private UbigeoRepository ubigeoRepository;

    public void importUbigeos() {
        try {
            // Cambia el uso de ResourceLoader a ResourceUtils
            File file = ResourceUtils.getFile("classpath:Ubigeos.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Suponiendo que los datos están separados por comas
                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setUbigeoReniec(data[0]); // Ajusta los índices según el CSV
                ubigeo.setDepartment(data[1]);
                ubigeo.setProvince(data[2]);
                ubigeo.setDistrict(data[3]);
                ubigeo.setRegion(data[4]);
                ubigeo.setStatus("A"); // O establece el valor adecuado

                ubigeoRepository.save(ubigeo).subscribe(); // Guarda el ubigeo en la base de datos
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }
    }
}
*/