package com.github.YourSergic1.datasource.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "field_entity")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ElementCollection
    List<Integer> board = new ArrayList<>(9);
}
