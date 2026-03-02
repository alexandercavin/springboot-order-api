package com.widetech.interview.widetech_interview.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaceOrderRequestDTO {

    @NotBlank
    private String customerName;

    @NotBlank
    private String address;
}
