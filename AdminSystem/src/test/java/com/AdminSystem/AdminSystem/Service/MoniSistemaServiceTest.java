package com.AdminSystem.AdminSystem.Service;

//import com.AdminSystem.AdminSystem.Model.SystemAlert;
import com.AdminSystem.AdminSystem.Model.SystemMetric;
import com.AdminSystem.AdminSystem.Model.SystemStatus;
import com.AdminSystem.AdminSystem.Repository.SystemAlertRepository;
import com.AdminSystem.AdminSystem.Repository.SystemMetricRepository;
import com.AdminSystem.AdminSystem.Repository.SystemStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoniSistemaServiceTest {

    @InjectMocks
    private MoniSistemaService moniSistemaService;

    @Mock
    private SystemStatusRepository statusRepo;
    @Mock
    private SystemAlertRepository alertRepo;
    @Mock
    private SystemMetricRepository metricRepo;



    @Test
    void checkSystem_ok() {
        when(metricRepo.save(any(SystemMetric.class))).thenReturn(new SystemMetric());
        when(statusRepo.save(any(SystemStatus.class))).thenReturn(new SystemStatus());
        //when(alertRepo.save(any(SystemAlert.class))).thenReturn(new SystemAlert());

        moniSistemaService.checkSystem();

        verify(metricRepo).save(any(SystemMetric.class));
        verify(statusRepo).save(any(SystemStatus.class));
    }

    @Test
    void getLastStatus_ok() {
        SystemStatus status = new SystemStatus(1L, "OK", "details", "now");
        when(statusRepo.findTopByOrderByCheckedAtDesc()).thenReturn(status);

        assertEquals(status, moniSistemaService.getLastStatus());
    }

    @Test
    void getAlerts_ok() {
        when(alertRepo.findAll()).thenReturn(List.of());
        assertNotNull(moniSistemaService.getAlerts());
    }

    @Test
    void getMetrics_ok() {
        when(metricRepo.findAll()).thenReturn(List.of());
        assertNotNull(moniSistemaService.getMetrics());
    }
}