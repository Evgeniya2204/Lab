package org.example;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TaskCsv {
    @CsvBindByPosition(position = 0)
    private long id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String performer;
    @CsvBindByPosition(position = 3)
    private String creationDate;


}
