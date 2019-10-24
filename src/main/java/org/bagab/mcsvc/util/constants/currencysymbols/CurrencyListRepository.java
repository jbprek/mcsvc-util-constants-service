package org.bagab.mcsvc.util.constants.currencysymbols;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CurrencyListRepository {
    @Getter
    private final List<CurrencyCodeInfo> infoList = new ArrayList<>();
    @Getter
    private final Map<String, CurrencyCodeInfo> infoByName = new HashMap<>();
    @Getter
    private final Map<String, CurrencyCodeInfo> infoByCode = new HashMap<>();

    private final CurrencyListConfiguration currencyListConfiguration;

    public CurrencyListRepository(CurrencyListConfiguration currencyListConfiguration) {
        this.currencyListConfiguration = currencyListConfiguration;
    }

    @PostConstruct
    public void init() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CurrencyCodeInfo.class);
        schema = schema.withColumnSeparator(';');
        MappingIterator<CurrencyCodeInfo> it = mapper.readerFor(CurrencyCodeInfo.class).with(schema).readValues(currencyListConfiguration.getCsvList());
        infoList.addAll(it.readAll());
        for (CurrencyCodeInfo info : infoList) {
            infoByName.put(info.getName(), info);
            infoByCode.put(info.getCode(), info);
        }
        log.debug("Loaded Currency CSV iso codes\n" + currencyListConfiguration.getCsvList());

    }
}
