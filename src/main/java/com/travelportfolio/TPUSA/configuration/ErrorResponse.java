package com.travelportfolio.TPUSA.configuration;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String code, String message, String path) {

  public ErrorResponse(String code, String message) {
    this(LocalDateTime.now(), code, message, null);
  }

  public ErrorResponse withPath(String path) {
    return new ErrorResponse(timestamp, code, message, path);
  }
}
