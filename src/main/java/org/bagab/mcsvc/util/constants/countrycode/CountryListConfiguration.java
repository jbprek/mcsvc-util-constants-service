package org.bagab.mcsvc.util.constants.countrycode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "countries")
@Data
public class CountryListConfiguration {

    private String csvList;

}
