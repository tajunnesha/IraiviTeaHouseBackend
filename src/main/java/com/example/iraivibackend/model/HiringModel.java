package com.example.iraivibackend.model;


import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "hiring_applications")
@Data // Idhuve Getter, Setter, RequiredArgsConstructor, toString ellathaiyum generate pannidum
public class HiringModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String phone;
    private String position;
    private String experience;
}
