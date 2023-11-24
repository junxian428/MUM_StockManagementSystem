package com.alibou.security.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.security.mapper.TokenMapper;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {


    @Autowired
    private TokenMapper tokenMapper;

    public ManagementController(TokenMapper tokenMapper){
        this.tokenMapper = tokenMapper;

    }  

    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    public String get() {
        return "GET:: management controller";
    }

    private String extractBearerToken(String authorizationHeader) {
        // Check if the Authorization header is not null and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token after "Bearer "
            return authorizationHeader.substring(7);
        }
        return null; // Return null if the header is not in the expected format
    }
    
    @GetMapping("/validate/account")
    public String fetchData(@RequestHeader(name = "Authorization") String authorizationHeader) {
        try {
            // Extract the Bearer token from the Authorization header
            String accessToken = extractBearerToken(authorizationHeader);

            // Retrieve the role based on the access token
            String role = tokenMapper.getRoleByTokenValue(accessToken);

            // Your logic to handle the role
            System.out.println("Received role: " + role);

            return "Your response data";
        } catch (Exception e) {
            // Handle and log the exception
            System.err.println("Error processing request: " + e.getMessage());
            return "Error processing request";
        }
    }


    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
