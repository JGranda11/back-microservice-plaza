package com.pragma.challenge.msvc_plaza.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="dish_category")
public class DishCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dish_category_id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;
}
