package com.example.pps.model

import org.springframework.data.jpa.repository.JpaRepository

interface UserRep: JpaRepository<User, Long> {
    fun findByPhone(phone: String): User?
    fun findByLogin(login: String): User?
    fun findByEmail(email: String): User?
    fun existsByPhone(phone: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun existsByLogin(login: String): Boolean
    fun findByOrderByRegisterTimeAsc(): List<User>
}