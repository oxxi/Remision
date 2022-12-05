package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LogModel {

    private String userName;
    private String date;
    private String time;
    private String screen;
    private String action;
    private String object;
    private String key;
    private String ip;


}
