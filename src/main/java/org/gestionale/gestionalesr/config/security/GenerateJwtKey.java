//package org.gestionale.gestionalesr.config.security;
//
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Encoders;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//
//public class GenerateJwtKey {
//    public static void main(String[] args) {
//        // Genera una chiave sicura per HS256
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        // Converte la chiave in Base64
//        String base64Key = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("Salva questa chiave in modo sicuro (ad es. in una variabile d'ambiente):");
//        System.out.println(base64Key);
//    }
//}
