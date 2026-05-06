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

/**
 *
 * @author josealberto
 */
@Entity
@Table(name = "registro_sesiones")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroSesiones.findAll", query = "SELECT r FROM RegistroSesiones r"),
    @NamedQuery(name = "RegistroSesiones.findByFechaInicio", query = "SELECT r FROM RegistroSesiones r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "RegistroSesiones.findByFechaFin", query = "SELECT r FROM RegistroSesiones r WHERE r.fechaFin = :fechaFin"),
    @NamedQuery(name = "RegistroSesiones.findByCompletado", query = "SELECT r FROM RegistroSesiones r WHERE r.completado = :completado"),
    @NamedQuery(name = "RegistroSesiones.findByNotas", query = "SELECT r FROM RegistroSesiones r WHERE r.notas = :notas")})
public class RegistroSesiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "completado")
    private Boolean completado;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesionId")
    private List<RegistroEjercicios> registroEjerciciosList;
    @JoinColumn(name = "entrenamiento_detalle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntrenamientoDetalle entrenamientoDetalleId;
    @JoinColumn(name = "atleta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios atletaId;

    public RegistroSesiones() {
    }

    public RegistroSesiones(Object id) {
        this.id = id;
    }

    public RegistroSesiones(Object id, Date fechaInicio) {
        this.id = id;
        this.fechaInicio = fechaInicio;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
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

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<RegistroEjercicios> getRegistroEjerciciosList() {
        return registroEjerciciosList;
    }

    public void setRegistroEjerciciosList(List<RegistroEjercicios> registroEjerciciosList) {
        this.registroEjerciciosList = registroEjerciciosList;
    }

    public EntrenamientoDetalle getEntrenamientoDetalleId() {
        return entrenamientoDetalleId;
    }

    public void setEntrenamientoDetalleId(EntrenamientoDetalle entrenamientoDetalleId) {
        this.entrenamientoDetalleId = entrenamientoDetalleId;
    }

    public Usuarios getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Usuarios atletaId) {
        this.atletaId = atletaId;
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
        if (!(object instanceof RegistroSesiones)) {
            return false;
        }
        RegistroSesiones other = (RegistroSesiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.RegistroSesiones[ id=" + id + " ]";
    }
    
}
