package com.navi.nbcampkotlincrudpractice.database.entity

import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Todo(
    var title: String,
    var content: String,
    var completed: Boolean,
    val createdAt: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val createdBy: Member,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)
