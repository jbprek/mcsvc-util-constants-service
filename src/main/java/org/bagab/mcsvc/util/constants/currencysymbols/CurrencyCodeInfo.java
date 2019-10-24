package org.bagab.mcsvc.util.constants.currencysymbols;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "name"})
public class CurrencyCodeInfo {
    private String code;
    private String name;
}
