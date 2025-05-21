package com.teamone.tyremonitoring.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tkph_data")
public class TkphData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float qLoaded;     // Load per tyre when loaded
    private float qEmpty;      // Load per tyre when empty
    private int nCycles;       // Number of cycles
    private float cycleLength; // Cycle length in km
    private float hours;       // Operating hours
    private float tkph;        // Calculated TKPH

    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
