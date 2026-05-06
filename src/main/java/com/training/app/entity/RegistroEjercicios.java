/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.training.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author josealberto
 */
@Entity
@Table(name = "registro_ejercicios")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroEjercicios.findAll", query = "SELECT r FROM RegistroEjercicios r"),
    @NamedQuery(name = "RegistroEjercicios.findBySeriesCompletadas", query = "SELECT r FROM RegistroEjercicios r WHERE r.seriesCompletadas = :seriesCompletadas"),
    @NamedQuery(name = "RegistroEjercicios.findByRepeticionesReales", query = "SELECT r FROM RegistroEjercicios r WHERE r.repeticionesReales = :repeticionesReales"),
    @NamedQuery(name = "RegistroEjercicios.findByPesoReal", query = "SELECT r FROM RegistroEjercicios r WHERE r.pesoReal = :pesoReal"),
    @NamedQuery(name = "RegistroEjercicios.findByNotas", query = "SELECT r FROM RegistroEjercicios r WHERE r.notas = :notas")})
public class RegistroEjercicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Column(name = "series_completadas")
    private Integer seriesCompletadas;
    @Column(name = "repeticiones_reales")
    private Integer repeticionesReales;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_real")
    private BigDecimal pesoReal;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;
    @JoinColumn(name = "detalle_ejercicio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DetalleEjercicios detalleEjercicioId;
    @JoinColumn(name = "sesion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegistroSesiones sesionId;

    public RegistroEjercicios() {
    }

    public RegistroEjercicios(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getSeriesCompletadas() {
        return seriesCompletadas;
    }

    public void setSeriesCompletadas(Integer seriesCompletadas) {
        this.seriesCompletadas = seriesCompletadas;
    }

    public Integer getRepeticionesReales() {
        return repeticionesReales;
    }

    public void setRepeticionesReales(Integer repeticionesReales) {
        this.repeticionesReales = repeticionesReales;
    }

    public BigDecimal getPesoReal() {
        return pesoReal;
    }

    public void setPesoReal(BigDecimal pesoReal) {
        this.pesoReal = pesoReal;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public DetalleEjercicios getDetalleEjercicioId() {
        return detalleEjercicioId;
    }

    public void setDetalleEjercicioId(DetalleEjercicios detalleEjercicioId) {
        this.detalleEjercicioId = detalleEjercicioId;
    }

    public RegistroSesiones getSesionId() {
        return sesionId;
    }

    public void setSesionId(RegistroSesiones sesionId) {
        this.sesionId = sesionId;
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
        if (!(object instanceof RegistroEjercicios)) {
            return false;
        }
        RegistroEjercicios other = (RegistroEjercicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.RegistroEjercicios[ id=" + id + " ]";
    }
    
}
