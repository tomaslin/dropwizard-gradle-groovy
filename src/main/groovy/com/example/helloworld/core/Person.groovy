package com.example.helloworld.core

import com.fasterxml.jackson.annotation.JsonProperty

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
    @JsonProperty
    long id

    @Column(name = "fullName", nullable = false)
    @JsonProperty
    String fullName

    @Column(name = "jobTitle", nullable = false)
    @JsonProperty
    String jobTitle

    public Person(long id, String fullName, String jobTitle) {
        this.id = id
        this.fullName = fullName
        this.jobTitle = jobTitle
    }
}
