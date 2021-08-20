package com.shopping.cart.entity;

import com.shopping.cart.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        name = "email_unique",
        columnNames = "email"))
public class User {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Cart cart;
}
