package ru.sberbank.DSMelnikov;

import ru.sberbank.DSMelnikov.model.City;
import ru.sberbank.DSMelnikov.service.CityService;
import ru.sberbank.DSMelnikov.service.ConsoleService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ConsoleService.showMenuMessage();

        List<City> cities;
        try {
            cities = new ArrayList<>(ConsoleService.scanFile("/Users/a19572679/glossary.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Справочник не найден");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int select = ConsoleService.readInput();

        if (select == 0 || select == 6) return;

        CityService cityService = new CityService();

        cityService.saveAll(cities);


        switch (select) {
            case 1:
                for (City city : cityService.findAllCities()) {
                    System.out.println(city);
                }
                break;
            case 2:
                for (City city : cityService.findAllSortedCities()) {
                    System.out.println(city);
                }
                break;
            case 3:
                for (City city : cityService.findAllSortedByDistrictAndName()) {
                    System.out.println(city);
                }
                break;
            case 4:
                City cityWithMaxPopulation = cityService.findMaxPopulationCity();
                System.out.printf("[%d] = %d", cityWithMaxPopulation.getId(), cityWithMaxPopulation.getPopulation());
                break;
            case 5:
                for (Map.Entry<String, Integer> entry : cityService.findCitiesNumberInRegions().entrySet()) {
                    System.out.printf("[%s] = %d\n", entry.getKey(), entry.getValue());
                }
                break;
            default:
                break;
        }


    }
}
