package com.technokratos.api;

import com.technokratos.dto.TokenPairRequest;
import com.technokratos.dto.TokenPairResponse;
import com.technokratos.dto.response.ExceptionResponse;
import com.technokratos.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/token")
public interface JwtTokenApi {

    @Operation(summary = "Get user info using token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User info",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UserResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Token is incorrect, token date expired, " +
                    "user, that token belongs, not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            )
                    }
            )
    })
    @GetMapping(value = "/user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> userInfoByToken(@RequestParam String token);

    @Operation(summary = "Refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New token",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = UserResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "401", description = "Token is incorrect, token date expired, " +
                    "user, that token belongs, not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ExceptionResponse.class)
                            )
                    }
            )
    })
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TokenPairResponse> updateTokens(@RequestBody TokenPairRequest tokenPairRequest);
}
