package com.example.pps.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @SequenceGenerator(name = "user_sq", sequenceName = "user_sq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sq")
    val id: Long,
    @Column(unique = true)
    var login: String,
    val password: String,
    val birth: LocalDate,
    @Column(unique = true)
    val phone: String,
    @Column(unique = true)
    val email: String,
    val name: String,
    val secondName: String,
    val patronymic: String,
    val registerTime: Double
)
