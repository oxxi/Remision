package hn.com.tigo.remision.entities.remision;


import hn.com.tigo.remision.models.ReasonModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "MOTIVO")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReasonEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "USUARIO_CREA")
    private String createdBy;
    @Column(name = "FECHA_CREA")
    private LocalDate createdAt;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDate modifiedAt;

    @Column(name = "ESTADO",length = 1)
    private String status;



    public ReasonModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ReasonModel model = new ReasonModel();
        model.setId(this.getId());
        model.setDescription(this.getDescription());
        model.setStatus(this.getStatus());
        model.setCreatedBy(this.getCreatedBy());
        model.setCreatedAt(this.getCreatedAt());
        model.setCreatedAtString(this.getCreatedAt().format(formatter));
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt());
        model.setModifiedAtString(this.getModifiedAt().format(formatter));
        return model;
    }


}
