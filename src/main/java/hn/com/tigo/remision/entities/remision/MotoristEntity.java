package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.MotoristModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "TRANSPORTISTA")
@Entity
@Getter
@Setter
@ToString
public class MotoristEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "ID_AGENCIA_TRANSPORTE", nullable = false)
    private Long idTransportAgency;

    @Column(name = "NOMBRE", length = 30)
    private String name;

    @Column(name = "APELLIDOS", length = 60)
    private String lastName;

    @Column(name = "LICENCIA",length = 15)
    private String licenseNumber;

    @Column(name = "USUARIO_CREA",length = 50)
    private String createdBy;

    @Column(name = "FECHA_CREA")
    private LocalDate createdAt;

    @Column(name = "USUARIO_EDITA",length = 50)
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDate modifiedAt;

    @Column(name = "ESTADO", length = 1)
    private String status;

    @Column(name = "RTN", length = 16)
    private String rtn;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TransportAgencyEntity.class,  optional = false)
    @JoinColumn(name = "ID_AGENCIA_TRANSPORTE",referencedColumnName = "ID", nullable = false, updatable = false, insertable = false)
    private TransportAgencyEntity transportAgencyEntity;


    public MotoristModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        MotoristModel model = new MotoristModel();
        model.setId(this.getId());
        model.setName(this.getName().toUpperCase());
        model.setLastName(this.getLastName().toUpperCase());
        model.setLicenseNumber(this.getLicenseNumber());
        model.setIdTransportAgency(this.getIdTransportAgency());
        model.setTransportAgencyName(this.getTransportAgencyEntity().getName());
        model.setRtn(this.getRtn());
        model.setStatus(this.getStatus());
        model.setCreatedBy(this.getCreatedBy());
        model.setModifiedBy(this.getModifiedBy());
        model.setCreatedAt(this.getCreatedAt().format(formatter)) ;
        model.setModifiedAt(this.getModifiedAt() == null ? "":  this.getModifiedAt().format(formatter));
        return model;
    }

}
