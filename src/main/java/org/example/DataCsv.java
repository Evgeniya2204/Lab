package org.example;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DataCsv {
    @CsvBindByPosition(position = 0)
    private String taskType;
    @CsvBindByPosition(position = 1)
    private String taskName;
    @CsvBindByPosition(position = 2)
    private String author;
    @CsvBindByPosition(position = 3)
    private String executor;
    @CsvBindByPosition(position = 4)
    private String priority;
    @CsvBindByPosition(position = 5)
    private String date;

}
