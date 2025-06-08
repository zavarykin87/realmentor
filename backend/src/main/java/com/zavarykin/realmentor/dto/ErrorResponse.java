package com.zavarykin.realmentor.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(String message, LocalDateTime timestamp) {
}
