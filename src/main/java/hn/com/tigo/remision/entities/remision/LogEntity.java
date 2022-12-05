package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.LogModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;


@Table(name = "LOG")
@Getter
@Setter
public class LogEntity implements Serializable {

    @Column(name = "USUARIO", length = 50)
    private String userName;

    @Column(name = "FECHA")
    private LocalDate createdAt;

    @Column(name = "MODULO", length = 50)
    private String module;

    @Column(name = "ACCION",length = 75)
    private String action;

    @Column(name = "OBJETO", length = 50)
    private String object;

    @Column(name = "LLAVE",length = 25)
    private String key;

    @Column(name = "IP",length = 15)
    private String ip;




    public LogModel entityToModel() {
        LogModel model = new LogModel();
        model.setIp(this.getIp());

        model.setKey(this.getKey());
        model.setAction(this.getAction());
        model.setUserName(this.getUserName());
        model.setObject(this.getObject());


        return model;
    }

}
