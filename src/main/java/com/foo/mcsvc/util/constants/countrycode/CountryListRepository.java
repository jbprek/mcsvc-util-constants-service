package com.foo.mcsvc.util.constants.countrycode;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
/**
 * List of countries is imported in CSV format and stored in immutable collections for lookup
 */
public class CountryListRepository {

    private final CountryListConfiguration configuration;

    private List<CountryCodeInfo> infoList;
    private Map<String, CountryCodeInfo> infoByName;
    private Map<String, CountryCodeInfo> infoByAlpha2;
    private Map<String, CountryCodeInfo> infoByAlpha3;


    public CountryListRepository(CountryListConfiguration configuration) {
        this.configuration = configuration;
    }

    @PostConstruct
    public void init() throws IOException {
        String isoCsvList = configuration.getCsvList();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CountryCodeInfo.class);
        schema = schema.withColumnSeparator(';');
        MappingIterator<CountryCodeInfo> it = mapper.readerFor(CountryCodeInfo.class).with(schema).readValues(isoCsvList);
        infoList = List.copyOf(it.readAll());

        Map<String, CountryCodeInfo> infoByNameTemp = new HashMap<>();
        Map<String, CountryCodeInfo> infoByAlpha2Temp = new HashMap<>();
        Map<String, CountryCodeInfo> infoByAlpha3Temp = new HashMap<>();
        for (CountryCodeInfo info : infoList) {
            infoByNameTemp.put(info.getName(), info);
            infoByAlpha2Temp.put(info.getAlpha2(), info);
            infoByAlpha3Temp.put(info.getAlpha3(), info);
        }
        infoByName = Map.copyOf(infoByNameTemp);
        infoByAlpha2 = Map.copyOf(infoByAlpha2Temp);
        infoByAlpha3 = Map.copyOf(infoByAlpha3Temp);
        log.debug("Loaded country CSV iso codes\n" + isoCsvList);

    }
}
