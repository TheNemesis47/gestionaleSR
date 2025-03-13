//package org.gestionale.gestionalesr.config.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//
//    // La chiave sicura in formato Base64, letta da application.properties (o variabile d'ambiente)
//    @Value("${jwt.secret}")
//    private String base64SecretKey;
//
//    // Durata del token in millisecondi (ad esempio, 1 ora)
//    private static final long TOKEN_VALIDITY = 3600000;
//
//    /**
//     * Genera il token JWT a partire dai dettagli dell'utente.
//     */
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    /**
//     * Verifica se il token è valido: controlla il subject e la scadenza.
//     */
//    public boolean validateToken(String token, String username) {
//        try {
//            final String extractedUsername = extractUsername(token);
//            return (extractedUsername.equals(username) && !isTokenExpired(token));
//        } catch (Exception e) {
//            // Token malformato o firma non valida
//            return false;
//        }
//    }
//
//    /**
//     * Estrae lo username (subject) dal token.
//     */
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    /**
//     * Estrae la data di scadenza dal token.
//     */
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    /**
//     * Metodo generico per estrarre un singolo claim dal token.
//     */
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    /**
//     * Estrae tutti i Claims (il payload) dal token.
//     * In questo metodo usiamo Jwts.parser() come richiesto.
//     */
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//    /**
//     * Converte la stringa Base64 in una chiave sicura da usare per firmare/verificare i token.
//     */
//    private Key getSignInKey() {
//        // Decodifica la chiave Base64 in un array di byte
//        byte[] keyBytes = Decoders.BASE64.decode(base64SecretKey);
//        // Crea la chiave HMAC-SHA256 (assicurandosi che keyBytes abbia almeno 32 byte)
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//}
