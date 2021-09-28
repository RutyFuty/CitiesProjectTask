package ru.sberbank.DSMelnikov.service;

import ru.sberbank.DSMelnikov.dao.CityDao;
import ru.sberbank.DSMelnikov.model.City;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityService {

    private CityDao cityDao = new CityDao();

    public CityService() {
    }

    public void saveAll(List<City> cities) {
        cityDao.saveAll(cities);
    }

    public List<City> findAllCities() {
        return cityDao.findAll();
    }

    public List<City> findAllSortedCities() {
        return cityDao.findAllSorted();
    }

    public List<City> findAllSortedByDistrictAndName() {
        return cityDao.findAllSortedByDistrictAndName();
    }

    public City findMaxPopulationCity() {
        return findAllCities().stream().max(Comparator.comparing(City::getPopulation)).get();
    }

    public Map<String, Integer> findCitiesNumberInRegions() {
        List<City> cities = findAllCities();
        Map<String, Integer> citiesMap = new HashMap<>();
        for (City city : cities) {
            if (citiesMap.containsKey(city.getRegion())) {
                citiesMap.computeIfPresent(city.getRegion(), (key, value) -> value + 1);
            } else {
                citiesMap.put(city.getRegion(), 1);
            }
        }
        return citiesMap;
    }

}
