package com.example.helloworld.core

import javax.persistence.*

@Entity
@Table(name = "people")
@NamedQueries([
        @NamedQuery(
                name = "com.example.helloworld.core.Person.findAll",
                query = "SELECT p FROM Person p"
        ),
        @NamedQuery(
                name = "com.example.helloworld.core.Person.findById",
                query = "SELECT p FROM Person p WHERE p.id = :id"
        )
])
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id

    @Column(name = "fullName", nullable = false)
    String fullName

    @Column(name = "jobTitle", nullable = false)
    String jobTitle
}
