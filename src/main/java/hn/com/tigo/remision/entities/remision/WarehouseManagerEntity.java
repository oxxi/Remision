package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.WarehouseManagerModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "ENCARGADO_BODEGA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class WarehouseManagerEntity implements Serializable {

    @Id
    @Column(name = "ENCARGADOID",insertable = false,updatable = false)
    private Long id;
    @Column(name = "NOMBRES", length = 50)
    private String name;
    @Column(name = "APELLIDOS", length = 50)
    private String lastName;
    @Column(name = "IDENTIFICACION", length = 15)
    private String dni;
    @Column(name = "USUARIO_CREA", length = 50)
    private String createdBy;
    @Column(name = "FECHA_CREA")
    private LocalDateTime createdAt;
    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;
    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;
    @Column(name = "ESTADO", length = 1)
    private String status;


    public WarehouseManagerModel entityToModel(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        WarehouseManagerModel model = new WarehouseManagerModel();
        model.setId(this.getId());
        model.setName(this.getName());
        model.setLastName(this.getLastName());
        model.setDni(this.getDni());
        model.setCreatedBy(this.getCreatedBy());
        model.setCreatedAt(this.getCreatedAt());
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt());
        model.setStatus(this.getStatus());
        try {
            model.setModifiedAtString(this.getModifiedAt() == null ? null : this.getModifiedAt().format(formatter));
            model.setCreatedAtString(this.getCreatedAt().format(formatter));
        }catch (Exception e) {
            log.info(e.getMessage());
        }


        return model;
    }
}
