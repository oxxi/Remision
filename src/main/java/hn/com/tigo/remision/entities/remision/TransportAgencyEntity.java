package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.TransportAgencyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "AGENCIA_TRANSPORTE")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TransportAgencyEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "NOMBRE",length = 50)
    private String name;

    @Column(name = "RTN",length = 15)
    private String rtn;

    @Column(name = "TELEFONO", length = 10)
    private String phoneNumber;

    @Column(name = "DIRECCION")
    private String address;

    @Column(name = "USUARIO_CREA")
    private String createdBy;

    @Column(name = "FECHA_CREA")
    private LocalDateTime createdAt;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;

    @Column(name = "ESTADO", length = 1)
    private String status;

    @OneToMany(mappedBy = "transportAgencyEntity",targetEntity = VehicleEntity.class, fetch = FetchType.EAGER)
    private Set<VehicleEntity> vehicleEntity;


    public TransportAgencyModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        TransportAgencyModel model  = new TransportAgencyModel();
        model.setId(this.getId());
        model.setName(this.getName());
        model.setPhoneNumber(this.getPhoneNumber());
        model.setAddress(this.getAddress());
        model.setCreatedBy(this.getCreatedBy());
        model.setCreatedAt(this.getCreatedAt());
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt());
        model.setStatus(this.getStatus());
        model.setRtn(this.getRtn());
        model.setModifiedAtString(this.getModifiedAt() == null ? null : this.getModifiedAt().format(formatter));
        model.setCreatedAtString(this.getCreatedAt().format(formatter));
        return model;
    }
}
