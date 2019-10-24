package org.bagab.mcsvc.util.constants.countrycode;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"name", "alpha2", "alpha3"})
public class CountryCodeInfo {
    private String name;
    private String alpha2;
    private String alpha3;
}
