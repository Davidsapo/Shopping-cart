package com.shopping.cart.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull(message = "First name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name does not match pattern.")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "Last name can not be empty.")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name does not match pattern.")
    private String lastName;

    @Column(nullable = false)
    @NotNull(message = "Email required")
    @Email(message = "Invalid email address.")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    private Cart cart;
}
