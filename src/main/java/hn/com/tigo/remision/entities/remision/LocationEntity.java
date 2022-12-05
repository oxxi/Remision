package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.LocationModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "UBICACION")
@Getter
@Setter
@ToString
public class LocationEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "DIRECCION_COMPLETA")
    private String Address;

    @Column(name = "NOMBRE_CORTO")
    private Long shortName;

    @Column(name = "USUARIO_CREA")
    private String userCreated;

    @Column(name = "FECHA_CREA")
    private LocalDateTime createdAt;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;

    @Column(name = "ESTADO")
    private String status;


    public LocationModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocationModel model = new LocationModel();
        model.setShortCode(this.getShortName().toString());
        model.setId(this.getId());
        model.setFullAddress(this.getAddress());
        model.setCreatedBy(this.getUserCreated());
        model.setCreatedAt(this.getCreatedAt() == null ? null :  this.getCreatedAt().format(formatter));
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt() == null ? null : this.getModifiedAt().format(formatter));
        model.setStatus(this.getStatus());
        return model;
    }

}
