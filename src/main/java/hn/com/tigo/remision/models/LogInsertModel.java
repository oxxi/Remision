package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogInsertModel {
    private String user;
    private String module;
    private String action;
    private String object;
    private String key;
}
