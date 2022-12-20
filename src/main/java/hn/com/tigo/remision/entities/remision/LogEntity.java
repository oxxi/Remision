package hn.com.tigo.remision.entities.remision;

import hn.com.tigo.remision.models.LogModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Table(name = "LOG")
@Getter
@Setter
public class LogEntity implements Serializable {

    @Column(name = "USUARIO", length = 50)
    private String userName;

    @Column(name = "FECHA")
    private LocalDateTime createdAt;

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
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm:ss a");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LogModel model = new LogModel();
        model.setIp(this.getIp());
        model.setKey(this.getKey());
        model.setAction(this.getAction());
        model.setUserName(this.getUserName());
        model.setObject(this.getObject());
        model.setScreen(this.getModule());
        model.setDate(this.getCreatedAt() !=null ? this.getCreatedAt().format(formatterDay) : null);
        try{
            model.setTime(this.getCreatedAt()!=null ? this.getCreatedAt().format(formatterTime) : null );
        }catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

}
