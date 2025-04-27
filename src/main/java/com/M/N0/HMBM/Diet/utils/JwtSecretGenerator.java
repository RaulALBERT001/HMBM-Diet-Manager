package com.M.N0.HMBM.Diet.utils;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;






public class JwtSecretGenerator {
    private static final String ENV_FILE = "C:\\HMBM-Diet-Manager\\.env";
    private static final String JWT_SECRET_NAME = "JWT_SECRET";
    private static final int JWT_SECRET_LENGTH = 64; 

    public static void main(String[] args) {
        String jwtSecret = generateJwtSecret();
        System.out.println("Generated JWT Secret: " + jwtSecret);

        updateEnvFile(jwtSecret);
        System.out.println("JWT Secret updated in " + ENV_FILE);
    }

    private static String generateJwtSecret() {
        byte[] secretBytes = new byte[JWT_SECRET_LENGTH];
        SecureRandom secureRandom = new SecureRandom();

        secureRandom.nextBytes(secretBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(secretBytes);
    }

    private static void updateEnvFile(String jwtSecret) {
        Path envfile = Paths.get(ENV_FILE);
    
        try {
            List<String> updatedLines;
            boolean foundKey = false;
        
            if (Files.exists(envfile)) {
                List<String> lines = Files.readAllLines(envfile);
                updatedLines = new ArrayList<>();
        
                for (String line : lines) {
                    if (line.startsWith(JWT_SECRET_NAME + "=")) {
                        updatedLines.add(JWT_SECRET_NAME + "=" + jwtSecret);
                        foundKey = true;
                    } else {
                        updatedLines.add(line);
                    }
                }
        
                if (!foundKey) {
                    updatedLines.add(JWT_SECRET_NAME + "=" + jwtSecret);
                }
        
            } else {
                updatedLines = new ArrayList<>();
                updatedLines.add(JWT_SECRET_NAME + "=" + jwtSecret);
            }
        
            Files.write(envfile, updatedLines);
            System.out.println(".env updated with success :)!");
        } catch ( IOException e) {
            System.err.println("Error updating .env :( : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
