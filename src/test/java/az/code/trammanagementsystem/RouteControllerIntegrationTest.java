package az.code.trammanagementsystem;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.services.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;


//@SpringBootTest
//@AutoConfigureMockMvc
public class RouteControllerIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RouteService routeService;
//
//    @Test
//    @WithMockUser(authorities = "ADMIN")
//    public void testCreateRoute() throws Exception {
//        Route route = new Route();
//        Mockito.when(routeService.createRoute(Mockito.any(Route.class))).thenReturn(route);
//
//        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJ0b2tlblR5cGUiOiJhY2Nlc3MiLCJzdWIiOiJuYXppbWJ1ZGFxbGlAZ21haWwuY29tIiwiaWF0IjoxNzEzODgwMjkyLCJleHAiOjE3MTM4ODA4OTJ9.6C7befX9uySEzU94qDSkbQCjdBl-t5eytCdb_M-dxtQ";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/routes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + jwtToken)
//                        .content("{\"waypoints\": [{\"latitude\": 40.7128, \"longitude\": -74.0060}]}"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//    }
}
