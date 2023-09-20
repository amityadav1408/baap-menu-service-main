package com.brillio.baapmenu.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorModel {
    private String errorMessage;
}
