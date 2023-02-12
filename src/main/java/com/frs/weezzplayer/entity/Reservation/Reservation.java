package com.frs.weezzplayer.entity.Reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.frs.weezzplayer.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reservation extends CommunField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", locale = "fr_TN")
    private Date DateReserve;
    private Long indexReserve;
    @Column(name = "reservation_price")
    private BigDecimal price;
    private Boolean isAvailable = true;
    private Integer status;
    @OneToMany
    private List<Asset> assets;


}
