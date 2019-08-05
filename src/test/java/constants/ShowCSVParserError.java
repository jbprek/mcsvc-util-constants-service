package constants;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class ShowCSVParserError {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    @JsonPropertyOrder({"name", "alpha2", "alpha3"})
    public static class MetaInfo {
        private String name;
        private String alpha2;
        private String alpha3;
    }

    @Test
    public void parserTest() throws IOException {

        //Input String with CSC Value
        String inputCSV =
                "name,alpha2,alpha3\n" +
                        "Albania;AL;ALB\n" +
                        "Algeria;DZ;DZA";

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(MetaInfo.class).withColumnSeparator(';').withHeader();
        MappingIterator<MetaInfo> it = mapper.readerFor(MetaInfo.class).with(schema).readValues(inputCSV);
        val infoList = it.readAll();
        System.out.println(infoList);
        assertThat(infoList).contains(MetaInfo.of("Albania", "AL", "ALB"));

    }
}
