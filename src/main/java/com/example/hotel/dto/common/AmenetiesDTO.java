package com.example.hotel.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AmenetiesDTO {
    private List<String> featuredAmeneties;
    private List<String> bathroomAndToileteries;
    private List<String> entertainment;
    private List<String> comforts;
    private List<String> diningDrinkingAndSnacking;
    private List<String> layoutAndFurnishing;
    private List<String> clothingAndLaundry;
}
