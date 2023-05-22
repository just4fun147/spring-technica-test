package com.example.springtechnicatest.entity
import javax.persistence.*

@Entity
@Table(name="tbl_token")
data class Token(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    val id : Long? = null,

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name="token", unique = true)
    val token: String? = null,



)
