package hn.com.tigo.remision.entities.remision;


import hn.com.tigo.remision.models.GeneralParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "PARAMETRO_GENERAL")
@Entity
@Getter
@Setter
public class ParameterEntity implements Serializable {

    @Id
    @Column(name = "NOMBRE_PARAMETRO",nullable = false)
    private String parameterName;

    @Column(name = "VALOR_PARAMETRO")
    private String parameterValue;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDate modifiedAt;


    public GeneralParameter entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        GeneralParameter model = new GeneralParameter();
        model.setDescription(this.getDescription());
        model.setName(this.getParameterName());
        model.setValue(this.getParameterValue());
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt(this.getModifiedAt() == null ? null : this.getModifiedAt().format(formatter));
        return model;
    }
}
