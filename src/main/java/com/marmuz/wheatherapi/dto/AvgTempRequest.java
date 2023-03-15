package com.marmuz.wheatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AvgTempRequest {

    private LocalDate from;
    private LocalDate to;
}
