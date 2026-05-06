/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.training.app.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author josealberto
 */
@Entity
@Table(name = "entrenamiento_detalle")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntrenamientoDetalle.findAll", query = "SELECT e FROM EntrenamientoDetalle e"),
    @NamedQuery(name = "EntrenamientoDetalle.findByNombre", query = "SELECT e FROM EntrenamientoDetalle e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EntrenamientoDetalle.findByOrden", query = "SELECT e FROM EntrenamientoDetalle e WHERE e.orden = :orden"),
    @NamedQuery(name = "EntrenamientoDetalle.findByNotas", query = "SELECT e FROM EntrenamientoDetalle e WHERE e.notas = :notas")})
public class EntrenamientoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @jakarta.validation.constraints.Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "orden")
    private int orden;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entrenamientoDetalleId")
    private List<DetalleEjercicios> detalleEjerciciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entrenamientoDetalleId")
    private List<RegistroSesiones> registroSesionesList;
    @JoinColumn(name = "entrenamiento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entrenamiento entrenamientoId;

    public EntrenamientoDetalle() {
    }

    public EntrenamientoDetalle(Object id) {
        this.id = id;
    }

    public EntrenamientoDetalle(Object id, String nombre, int orden) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<DetalleEjercicios> getDetalleEjerciciosList() {
        return detalleEjerciciosList;
    }

    public void setDetalleEjerciciosList(List<DetalleEjercicios> detalleEjerciciosList) {
        this.detalleEjerciciosList = detalleEjerciciosList;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<RegistroSesiones> getRegistroSesionesList() {
        return registroSesionesList;
    }

    public void setRegistroSesionesList(List<RegistroSesiones> registroSesionesList) {
        this.registroSesionesList = registroSesionesList;
    }

    public Entrenamiento getEntrenamientoId() {
        return entrenamientoId;
    }

    public void setEntrenamientoId(Entrenamiento entrenamientoId) {
        this.entrenamientoId = entrenamientoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntrenamientoDetalle)) {
            return false;
        }
        EntrenamientoDetalle other = (EntrenamientoDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.EntrenamientoDetalle[ id=" + id + " ]";
    }
    
}
