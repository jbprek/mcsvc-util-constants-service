package org.bagab.mcsvc.util.constants.currencysymbols;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "currencies")
@Data
public class CurrencyListConfiguration implements Serializable {

    private String csvList;

}
