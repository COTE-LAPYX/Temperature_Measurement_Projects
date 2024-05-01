package ua.kiev.cote;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final String[] sensorTitle = {"Sensor1", "Sensor2", "Sensor3"};
        final String url = "http://localhost:8080/measurement/add";
        RestTemplate restTemplate = new RestTemplate();
        float value;
        int count = 0;
        boolean rain = true;
        int index = 0;

        while (true){
            value = (new Random().nextFloat() * 65 - 30);

            if(count%3 == 0){
                rain = new Random().nextBoolean();
            }

            if (index % sensorTitle.length == 0){
                index = 0;
            }

            Map<String, Object> jsonData = new HashMap<>();
            jsonData.put("value", value);
            jsonData.put("raining", rain);
            jsonData.put("sensor", Map.of("title", sensorTitle[index]));

            try {
                restTemplate.postForObject(url, jsonData, String.class);
                System.out.println("Измерение успешно добавлено.");
                count++;
            } catch (HttpClientErrorException e){
                System.out.println("Ошибка");
                System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                throw new RuntimeException();
            }

            index++;
        }
    }
}
