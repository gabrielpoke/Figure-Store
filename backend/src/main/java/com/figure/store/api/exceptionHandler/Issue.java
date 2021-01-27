package com.figure.store.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@JsonInclude(Include.NON_NULL)
public class Issue {

    private Integer status;
    private OffsetDateTime datetime;
    private String title;
    private List<Field> fields;

    public Issue(Integer status, String title, OffsetDateTime datetime) {
        this.status = status;
        this.title = title;
        this.datetime = datetime;
    }
}
