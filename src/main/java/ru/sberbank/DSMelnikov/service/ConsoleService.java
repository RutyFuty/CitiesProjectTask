package ru.sberbank.DSMelnikov.service;

import ru.sberbank.DSMelnikov.model.City;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleService {
    public static void showMenuMessage() {
        System.out.print("Выберете требуемое действие:\n" +
                "1) Список городов.\n" +
                "2) Отсортированный список городов по названию города.\n" +
                "3) Отсортированный список городов по федеральному округу и названию.\n" +
                "4) Найти город с наибольшим количеством жителей\n" +
                "5) Определить количество городов в развезе регионов\n" +
                "6) Выйти\n");
    }

    public static List<City> scanFile(String file) throws IOException {
        List<City> cities = new ArrayList<>();

        StringBuilder data = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {

            String str;
            while ((str = br.readLine()) != null) {
                data.append(str);
            }
        }

        String[] dataArray = data.toString().split(";");

        if (dataArray.length % 6 != 0) {
            throw new IOException("Некорректный размер справочника");
        }

        for (int i = 0; i < dataArray.length; i = i + 6) {
            cities.add(new City(
                    Integer.parseInt(dataArray[i]),
                    dataArray[i + 1],
                    dataArray[i + 2],
                    dataArray[i + 3],
                    Integer.parseInt(dataArray[i + 4]),
                    Integer.parseInt(dataArray[i + 5])
            ));
        }

        return cities;
    }

    public static int readInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int i = Integer.parseInt(bufferedReader.readLine());
            if (!(i >= 1 && i <= 6)) {
                System.out.println("Введите корректное число (от 1 до 6)");
                return readInput();
            }
            return i;
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный выбор");
            return 0;
        }
    }
}
