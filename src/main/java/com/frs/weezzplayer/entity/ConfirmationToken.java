package com.frs.weezzplayer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false,nullable = false)
    private LocalDateTime expireAt;



    @OneToOne
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             User user
                            ) {
        this.token = token;
        this.createdAt = createdAt;
        this.expireAt = expiredAt;
        this.user=user;
    }
    public boolean isExpired(){
        return  getExpireAt().isBefore(LocalDateTime.now());
    }




}
