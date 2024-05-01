package ua.kiev.cote;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.kiev.cote.dto.MeasurementDTO;
import ua.kiev.cote.dto.MeasurementResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    static Scanner scanner2 = new Scanner(System.in);

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            System.out.println("\nСделайте свой выбор:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addSensor(restTemplate);
                case 2 -> getAllMeasurements(restTemplate);
                case 3 -> System.out.println("Долждивых дней: " + getRainingDayCount(restTemplate));
                case 4 -> System.exit(-1);
            }
        }
    }

    public static void menu() {
        System.out.println("1 - Новый сенсор");
        System.out.println("2 - Посмотреть все измерения");
        System.out.println("3 - Сколько было дождливых дней");
        System.out.println("4 - Выход");
    }

    public static void addSensor(RestTemplate restTemplate) {
        final String url = "http://localhost:8080/sensor/registration";

        System.out.println("Введите название сенсора:");
        String title = scanner2.nextLine();

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("title", title);

        try {
            String response = restTemplate.postForObject(url, jsonData, String.class);
            System.out.println("Response from RestApiServer: " + response);
        } catch (HttpClientErrorException e) {
            System.out.println("Ошибка");
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void getAllMeasurements(RestTemplate restTemplate){
        final String url = "http://localhost:8080/measurement";

        MeasurementResponse response = restTemplate.getForObject(url, MeasurementResponse.class);
        System.out.println("****************************************************************");
        for (MeasurementDTO mdto : response.getMeasurements()){
            System.out.println("Sensor name: " + mdto.getSensor());
            System.out.println("Value: " + mdto.getValue());
            System.out.println("Raining: " + mdto.isRaining());
            System.out.println("****************************************************************");
        }
    }

    public static int getRainingDayCount(RestTemplate restTemplate){
        final String url = "http://localhost:8080/measurement/raindaycount";
        Integer response = restTemplate.getForObject(url, Integer.class);
        if (response == null) return -1;
        return response;
    }
}
