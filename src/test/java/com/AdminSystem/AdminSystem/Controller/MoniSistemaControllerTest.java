package com.AdminSystem.AdminSystem.Controller;

//import com.AdminSystem.AdminSystem.Model.SystemAlert;
//import com.AdminSystem.AdminSystem.Model.SystemMetric;
import com.AdminSystem.AdminSystem.Model.SystemStatus;
import com.AdminSystem.AdminSystem.Service.MoniSistemaService;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MoniSistemaController.class)
class MoniSistemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoniSistemaService moniSistemaService;

    @Test
    void checkNow_ok() throws Exception {
        mockMvc.perform(post("/api/v1/monitor/chequeo"))
                .andExpect(status().isOk());
    }

    @Test
    void getStatus_ok() throws Exception {
        SystemStatus status = new SystemStatus(1L, "OK", "detalles", "now");
        when(moniSistemaService.getLastStatus()).thenReturn(status);

        mockMvc.perform(get("/api/v1/monitor/estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    void getAlerts_ok() throws Exception {
        when(moniSistemaService.getAlerts()).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/monitor/alertas"))
                .andExpect(status().isOk());
    }

    @Test
    void getMetrics_ok() throws Exception {
        when(moniSistemaService.getMetrics()).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/monitor/metricas"))
                .andExpect(status().isOk());
    }
}