package com.example.CGI_Test.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "filmid")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pealkiri;
    private LocalDateTime kellaeg;
    private int vanusepiirang;
    private String žanr;
}
