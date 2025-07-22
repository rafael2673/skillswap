package br.com.skillswap.AuthService.dto;

import br.com.skillswap.AuthService.model.Enum.UserRoles;

public record LoginResponseDTO(String accessToken, String refreshToken, long expiresIn, long refreshExpiresIn, UserRoles role) {
}
