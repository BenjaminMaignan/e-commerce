package com.bmaignan.apistore.customer.dto;

import com.bmaignan.apistore.cart.dto.CartResponseDto;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        CartResponseDto cart
) {
}
