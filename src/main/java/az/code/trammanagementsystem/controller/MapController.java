package az.code.trammanagementsystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MapController {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @GetMapping("/map")
    public ResponseEntity<String> getGoogleMapsScript() {
        String url = "https://maps.googleapis.com/maps/api/js?key=" + apiKey + "&libraries=drawing&callback=initMap";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }
}

