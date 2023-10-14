package net.javaguides.ems.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 'name' 안 써도 됨
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    private boolean completed;
}
