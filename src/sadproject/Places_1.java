/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadproject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Homepc
 */
@Entity
@Table(name = "PLACES", catalog = "", schema = "EKSAN")
@NamedQueries({
    @NamedQuery(name = "Places_1.findAll", query = "SELECT p FROM Places_1 p")
    , @NamedQuery(name = "Places_1.findByPlaceid", query = "SELECT p FROM Places_1 p WHERE p.placeid = :placeid")
    , @NamedQuery(name = "Places_1.findBySite", query = "SELECT p FROM Places_1 p WHERE p.site = :site")
    , @NamedQuery(name = "Places_1.findByDistrict", query = "SELECT p FROM Places_1 p WHERE p.district = :district")
    , @NamedQuery(name = "Places_1.findByDivision", query = "SELECT p FROM Places_1 p WHERE p.division = :division")})
public class Places_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PLACEID")
    private BigDecimal placeid;
    @Column(name = "SITE")
    private String site;
    @Column(name = "DISTRICT")
    private String district;
    @Column(name = "DIVISION")
    private String division;

    public Places_1() {
    }

    public Places_1(BigDecimal placeid) {
        this.placeid = placeid;
    }

    public BigDecimal getPlaceid() {
        return placeid;
    }

    public void setPlaceid(BigDecimal placeid) {
        BigDecimal oldPlaceid = this.placeid;
        this.placeid = placeid;
        changeSupport.firePropertyChange("placeid", oldPlaceid, placeid);
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        String oldSite = this.site;
        this.site = site;
        changeSupport.firePropertyChange("site", oldSite, site);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        String oldDistrict = this.district;
        this.district = district;
        changeSupport.firePropertyChange("district", oldDistrict, district);
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        String oldDivision = this.division;
        this.division = division;
        changeSupport.firePropertyChange("division", oldDivision, division);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placeid != null ? placeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Places_1)) {
            return false;
        }
        Places_1 other = (Places_1) object;
        if ((this.placeid == null && other.placeid != null) || (this.placeid != null && !this.placeid.equals(other.placeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sadproject.Places_1[ placeid=" + placeid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
