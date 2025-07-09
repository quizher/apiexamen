package com.enrique.apiexamen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String licenseNumber;
    private boolean active;
}
