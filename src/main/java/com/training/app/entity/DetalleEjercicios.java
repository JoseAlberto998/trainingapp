
package com.training.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "detalle_ejercicios")
@jakarta.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEjercicios.findAll", query = "SELECT d FROM DetalleEjercicios d"),
    @NamedQuery(name = "DetalleEjercicios.findByOrden", query = "SELECT d FROM DetalleEjercicios d WHERE d.orden = :orden"),
    @NamedQuery(name = "DetalleEjercicios.findBySeries", query = "SELECT d FROM DetalleEjercicios d WHERE d.series = :series"),
    @NamedQuery(name = "DetalleEjercicios.findByRepeticiones", query = "SELECT d FROM DetalleEjercicios d WHERE d.repeticiones = :repeticiones"),
    @NamedQuery(name = "DetalleEjercicios.findByPesoObjetivo", query = "SELECT d FROM DetalleEjercicios d WHERE d.pesoObjetivo = :pesoObjetivo"),
    @NamedQuery(name = "DetalleEjercicios.findByNotas", query = "SELECT d FROM DetalleEjercicios d WHERE d.notas = :notas")})
public class DetalleEjercicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Lob
    @Column(name = "id")
    private Object id;
    @Basic(optional = false)
    @jakarta.validation.constraints.NotNull
    @Column(name = "orden")
    private int orden;
    @Column(name = "series")
    private Integer series;
    @Column(name = "repeticiones")
    private Integer repeticiones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_objetivo")
    private BigDecimal pesoObjetivo;
    @jakarta.validation.constraints.Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;
    @JoinColumn(name = "ejercicio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ejercicios ejercicioId;
    @JoinColumn(name = "entrenamiento_detalle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EntrenamientoDetalle entrenamientoDetalleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleEjercicioId")
    private List<RegistroEjercicios> registroEjerciciosList;

    public DetalleEjercicios() {
    }

    public DetalleEjercicios(Object id) {
        this.id = id;
    }

    public DetalleEjercicios(Object id, int orden) {
        this.id = id;
        this.orden = orden;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public BigDecimal getPesoObjetivo() {
        return pesoObjetivo;
    }

    public void setPesoObjetivo(BigDecimal pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Ejercicios getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Ejercicios ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public EntrenamientoDetalle getEntrenamientoDetalleId() {
        return entrenamientoDetalleId;
    }

    public void setEntrenamientoDetalleId(EntrenamientoDetalle entrenamientoDetalleId) {
        this.entrenamientoDetalleId = entrenamientoDetalleId;
    }

    @jakarta.xml.bind.annotation.XmlTransient
    public List<RegistroEjercicios> getRegistroEjerciciosList() {
        return registroEjerciciosList;
    }

    public void setRegistroEjerciciosList(List<RegistroEjercicios> registroEjerciciosList) {
        this.registroEjerciciosList = registroEjerciciosList;
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
        if (!(object instanceof DetalleEjercicios)) {
            return false;
        }
        DetalleEjercicios other = (DetalleEjercicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.DetalleEjercicios[ id=" + id + " ]";
    }
    
}
