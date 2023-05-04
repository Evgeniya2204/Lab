package org.example;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Вы не ввели поисковую строку и поле для сортировки");
        }

        List<String> filters = new ArrayList<>();
        List<String> sorts = new ArrayList<>();
        int index = 0;

        while (index < args.length) {
            switch (args[index]) {
                case "-f":
                    filters.add(args[++index]);
                    break;
                case "-s":
                    sorts.add(args[++index]);
                    break;
            }
            index++;
        }

        var csvStream = Main.class.getResourceAsStream("/example.csv");
        var tasks = new CsvToBeanBuilder<DataCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(DataCsv.class)
                .build()
                .parse();

        PrintWriter writer = new PrintWriter("output.txt");

        filters.forEach(f -> {
            String[] filter = f.split("=");
            sorts.forEach(s -> {
                String[] sort = s.split("=");
                if (filter[0].equals("taskName") && sort[0].equals("taskName")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getTaskName), writer);
                } else if (filter[0].equals("taskName") && sort[0].equals("taskType")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getTaskType), writer);
                } else if (filter[0].equals("taskName") && sort[0].equals("author")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getAuthor), writer);
                } else if (filter[0].equals("taskName") && sort[0].equals("executor")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getExecutor), writer);
                } else if (filter[0].equals("taskName") && sort[0].equals("priority")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getPriority), writer);
                } else if (filter[0].equals("taskName") && sort[0].equals("date")) {
                    filterTaskName(tasks, filter, Comparator.comparing(DataCsv::getDate), writer);


                } else if (filter[0].equals("taskType") && sort[0].equals("taskName")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getTaskName), writer);
                } else if (filter[0].equals("taskType") && sort[0].equals("taskType")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getTaskType), writer);
                } else if (filter[0].equals("taskType") && sort[0].equals("author")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getAuthor), writer);
                } else if (filter[0].equals("taskType") && sort[0].equals("executor")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getExecutor), writer);
                } else if (filter[0].equals("taskType") && sort[0].equals("priority")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getPriority), writer);
                } else if (filter[0].equals("taskType") && sort[0].equals("date")) {
                    filterTaskType(tasks, filter, Comparator.comparing(DataCsv::getDate), writer);
                }
            });
        });

        writer.close();
    }

    private static void filterTaskType(List<DataCsv> tasks, String[] filter, Comparator<DataCsv> comparing, PrintWriter writer) {
        tasks.stream()
                .filter(dataCsv -> dataCsv.getTaskType().contains(filter[1]))
                .sorted(comparing)
                .forEach(t -> writer.printf("Тип задачи: %s%nНазвание задачи: %s%nАвтор: %s%nИсполнитель: %s%nПриоритет: %s%nДата создания: %s%n",
                        t.getTaskType(), t.getTaskName(), t.getAuthor(), t.getExecutor(), t.getPriority(), t.getDate()));
    }

    private static void filterTaskName(List<DataCsv> tasks, String[] filter, Comparator<DataCsv> comparing, PrintWriter writer) {
        tasks.stream()
                .filter(dataCsv -> dataCsv.getTaskName().contains(filter[1]))
                .sorted(comparing)
                .forEach(t -> writer.printf("Тип задачи: %s%nНазвание задачи: %s%nАвтор: %s%nИсполнитель: %s%nПриоритет: %s%nДата создания: %s%n",
                        t.getTaskType(), t.getTaskName(), t.getAuthor(), t.getExecutor(), t.getPriority(), t.getDate()));
    }
}