
package com.training.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "ejercicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicios.findAll", query = "SELECT e FROM Ejercicios e"),
    @NamedQuery(name = "Ejercicios.findByNombre", query = "SELECT e FROM Ejercicios e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejercicios.findByDescripcion", query = "SELECT e FROM Ejercicios e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Ejercicios.findByGrupoMuscular", query = "SELECT e FROM Ejercicios e WHERE e.grupoMuscular = :grupoMuscular"),
    @NamedQuery(name = "Ejercicios.findByCreatedAt", query = "SELECT e FROM Ejercicios e WHERE e.createdAt = :createdAt")})
public class Ejercicios implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "grupo_muscular")
    private String grupoMuscular;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicioId")
    private List<DetalleEjercicios> detalleEjerciciosList;

    public Ejercicios() {
    }

    public Ejercicios(Object id) {
        this.id = id;
    }

    public Ejercicios(Object id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlTransient
    public List<DetalleEjercicios> getDetalleEjerciciosList() {
        return detalleEjerciciosList;
    }

    public void setDetalleEjerciciosList(List<DetalleEjercicios> detalleEjerciciosList) {
        this.detalleEjerciciosList = detalleEjerciciosList;
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
        if (!(object instanceof Ejercicios)) {
            return false;
        }
        Ejercicios other = (Ejercicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.app.entity.Ejercicios[ id=" + id + " ]";
    }
    
}
