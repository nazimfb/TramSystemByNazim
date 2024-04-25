package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.exceptions.AuthException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://192.168.10.164:8000", "http://192.168.10.164:8000/*"})
public class MapController {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @GetMapping("/map")
    public ResponseEntity<String> getGoogleMapsScript() {
        String url = "https://maps.googleapis.com/maps/api/js?key=" + apiKey + "&libraries=drawing&callback=initMap";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboardHtml(Authentication authentication) {
        try {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = Objects.requireNonNull(authorities.stream().findFirst().orElse(null)).getAuthority();

            // Load HTML content based on user's role
            String dashboardHtml;
            if (role.equals("ADMIN")) {
                dashboardHtml = loadHtmlFromResource("management.html");
            } else {
                dashboardHtml = loadHtmlFromResource("map.html");
            }

            return ResponseEntity.ok(dashboardHtml);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    private String loadHtmlFromResource(String resourceName) {
        try {
            Resource resource = new ClassPathResource(resourceName);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new AuthException(e.getMessage());
        }
    }

}
