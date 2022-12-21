package hn.com.tigo.remision.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogInsertModel {
    private String user;
    private String module;
    private String action;
    private String object;
    private String key;
}
