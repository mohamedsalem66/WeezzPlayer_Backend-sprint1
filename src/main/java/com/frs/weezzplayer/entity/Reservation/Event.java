package com.frs.weezzplayer.entity.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Event extends CommunField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;

    private String color;
    @Override
    public String toString(){
        return  "Event [id=" + id + ", title=" + title + ", start=" + startTime
                + ", finish=" + endTime + "]";

    }
}

