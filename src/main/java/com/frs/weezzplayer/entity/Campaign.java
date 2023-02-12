package com.frs.weezzplayer.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "json", typeClass = com.vladmihalcea.hibernate.type.json.JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = com.vladmihalcea.hibernate.type.json.JsonBinaryType.class)
})
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date date;
    private Boolean isActive;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @Lob
    private String config;

//    @JsonManagedReference

}
