package com.yourshop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    private String contact;

    @Column(nullable = false)
    private String email;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    private boolean approved = false;
}