package com.backend.blog.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fpid;
    @Column(nullable = false)
    private Integer otp;
    @Column(nullable = false)

    private Date expirationTime;
    @OneToOne
    private User user;

}
