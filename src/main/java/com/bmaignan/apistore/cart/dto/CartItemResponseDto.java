package com.bmaignan.apistore.cart.dto;

import com.bmaignan.apistore.article.dto.ArticleResponseDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CartItemResponseDto(
        UUID id,
        UUID cartId,
        ArticleResponseDTO article,
        Integer quantity
) {
}
