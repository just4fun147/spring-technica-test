package com.example.springtechnicatest.util

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


class JWTGenerator {
    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
        private val instance: JWTGenerator = JWTGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)
        fun createJWT(id : Long, subject: String): String {
//    fun createJWT(id : Long, name: String,email: String, username:String): String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
//        val subject = JWTSubject(id.toString(),name,username,email)
        val builder: JwtBuilder = Jwts.builder().setId(id.toString())
            .setIssuedAt(now)
//            .setSubject(Gson().toJson(subject))
            .setSubject(subject)
            .setIssuer("technocenter")
            .setAudience("technocenter")
            .signWith(signatureAlgorithm, signingKey)

        val expMills = nowMills + 50000L
        val exp = Date(expMills)
        builder.setExpiration(exp)

        return builder.compact()
    }

    fun decodeJWT(jwt: String): Boolean {
        val claims: Claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).body
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)
        return claims.expiration.before(now)
    }


}

