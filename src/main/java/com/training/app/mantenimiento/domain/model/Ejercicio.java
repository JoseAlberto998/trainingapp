/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.training.app.mantenimiento.domain.model;

import java.util.UUID;

/**
 *
 * @author josealberto
 */
public class Ejercicio {
    private UUID idEjercicio;
    private String grupoMuscular;

    private Ejercicio(UUID idEjercicio, String grupoMuscular) {
        this.idEjercicio = idEjercicio;
        this.grupoMuscular = grupoMuscular;
    }
    
    public Ejercicio of(String grupoMuscular)
    {
        return new Ejercicio (null, grupoMuscular);
    }
    
    
    public Ejercicio of(UUID id, String grupoMuscular)
    {
        return new Ejercicio (id, grupoMuscular);
    }
    

    public UUID getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(UUID idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }
   
    
    
    
}
