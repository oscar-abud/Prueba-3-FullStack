package com.prueba2.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {
    
    //Secreto
    private static final String SECRET_KEY = "mi-clave-secreta";

    //Tiempo de expiracion
    private static final long EXPIRATION_TIME = 360000000;

    //Algoritmo que genera el token al azar
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    //Funcion para general el toke
    public String generateToken(String username){
        return JWT.create()
            .withSubject(username) //Define para quien es el token
            .withIssuer("api-service") //Define el emisor del toke
            .withIssuedAt(new Date()) //Define la fecha y hora en que el token fue generado
            .withExpiresAt(new Date(System.currentTimeMillis()
            +EXPIRATION_TIME)) // Define el tiempo de expiracion del token, usa el tiempo actual mas el tiempo constante (EXPIRATION_TIME)
            .sign(algorithm); //Esto es lo que garantiza que el token no fue modificado
    }

    //Funcion para extraer el nombre del usuario (subject) desde un token JWT valido
    public String extractUsername(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm) // Configura el verificador del token con el algoritmo de firma (por ejemplo HMAC256)
            .withIssuer("api-service") // Especifica que solo aceptará tokens emitidos por "api-service"
            .build() // Construye el validador JWT
            .verify(token); // Verifica y decodifica el token recibido. Si el token no es válido (expirado, mal firmado, etc.), lanza una excepción

            return decodedJWT.getSubject(); // Retorna el "sub" del token (el usuario al que pertenece)
    }

}
