/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.training.app.entity;

import java.io.Serializable;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josealberto
 */
@Entity
@Table(name = "entrenamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrenamiento.findAll", query = "SELECT e FROM Entrenamiento e"),
    @NamedQuery(name = "Entrenamiento.findByNombre", query = "SELECT e FROM Entrenamiento e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Entrenamiento.findByFechaInicio", query = "SELECT e FROM Entrenamiento e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Entrenamiento.findByFechaFin", query = "SELECT e FROM Entrenamiento e WHERE e.fechaFin = :fechaFin"),
    @NamedQuery(name = "Entrenamiento.findByActivo", query = "SELECT e FROM Entrenamiento e WHERE e.activo = :activo"),
    @NamedQuery(name = "Entrenamiento.findByCreatedAt", query = "SELECT e FROM Entrenamiento e WHERE e.createdAt = :createdAt")})
public class Entrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "atleta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios atletaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entrenamientoId")
    private List<EntrenamientoDetalle> entrenamientoDetalleList;

    public Entrenamiento() {
    }

    public Entrenamiento(Object id) {
        this.id = id;
    }

    public Entrenamiento(Object id, String nombre, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Usuarios getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Usuarios atletaId) {
        this.atletaId = atletaId;
    }

    @XmlTransient
    public List<EntrenamientoDetalle> getEntrenamientoDetalleList() {
        return entrenamientoDetalleList;
    }

    public void setEntrenamientoDetalleList(List<EntrenamientoDetalle> entrenamientoDetalleList) {
        this.entrenamientoDetalleList = entrenamientoDetalleList;
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
        if (!(object instanceof Entrenamiento)) {
            return false;
        }
        Entrenamiento other = (Entrenamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.Entrenamiento[ id=" + id + " ]";
    }
    
}
