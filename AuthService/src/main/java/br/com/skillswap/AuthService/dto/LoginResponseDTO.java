package br.com.skillswap.AuthService.dto;

public record LoginResponseDTO(String accessToken, String refreshToken, long expiresIn, long refreshExpiresIn) {
}
