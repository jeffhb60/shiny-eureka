package com.jeffhb60.backendshoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String id;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private OrderDTO order;
}
