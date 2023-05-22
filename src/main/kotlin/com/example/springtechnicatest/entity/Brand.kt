package com.example.springtechnicatest.entity

import javax.persistence.*

@Entity
@Table(name="tbl_brand")
data class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_BRAND", nullable = false)
    val id : Long? = null,

    @Column(name = "DESC_BRAND", nullable=false)
    val desc:String? = null
)
