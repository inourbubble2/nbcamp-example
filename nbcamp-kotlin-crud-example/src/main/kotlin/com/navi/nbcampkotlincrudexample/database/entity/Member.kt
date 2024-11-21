package com.navi.nbcampkotlincrudpractice.database.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Member(
    var username: String,
    var password: String,
    var email: String,
    val createdAt: LocalDateTime,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)
