package edu.goit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import edu.goit.parser.converter.CsvPriceConverter;
import edu.goit.parser.converter.CsvTimestampConverter;
import edu.goit.parser.converter.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Crypto implements Serializable {
    private static final long serialVersionUID = 1L;

    @CsvBindByName(column = "symbol", required = true)
    @JsonProperty("symbol")
    private String symbol;
    @CsvCustomBindByName(column = "timestamp", required = true, converter = CsvTimestampConverter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;
    @JsonProperty("price")
    @CsvCustomBindByName(column = "price", required = true, converter = CsvPriceConverter.class)
    private Double price;
}
