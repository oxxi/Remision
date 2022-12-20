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
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;

    @Column(name = "ESTADO",length = 1)
    private String status;



    public ReasonModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        ReasonModel model = new ReasonModel();
        model.setId(this.getId());
        model.setDescription(this.getDescription());
        model.setStatus(this.getStatus());
        model.setCreatedBy(this.getCreatedBy());
        model.setCreatedAt(this.getCreatedAt());
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt());

        try{
            model.setCreatedAtString(this.getCreatedAt() == null ? null : this.getCreatedAt().format(formatter));
            model.setModifiedAtString(this.getModifiedAt() == null ? null : this.getModifiedAt().format(formatter));
        }catch (Exception e){
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            model.setCreatedAtString(this.getCreatedAt() != null ? this.getCreatedAt().format(formatterDate) : null);
            model.setModifiedAtString(this.getModifiedAt() != null ? this.getModifiedAt().format(formatterDate):null);
        }


        return model;
    }


}
