package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ID;
    private String name;
    private String email;
    private Date data;
    private Double salaray;
    private Departamento dep;

    public Vendedor(){

    }

    public Vendedor(Integer ID, String name, String email, Date data, Double salaray, Departamento dep) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.data = data;
        this.salaray = salaray;
        this.dep = dep;
    }
    //GETTERS AND SETTERS
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getSalaray() {
        return salaray;
    }

    public void setSalaray(Double salaray) {
        this.salaray = salaray;
    }

    public Departamento getDep() {
        return dep;
    }

    public void setDep(Departamento dep) {
        this.dep = dep;
    }
    //HASH CODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(ID, vendedor.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
    //To String

    @Override
    public String toString() {
        return "Vendedor: " +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", data=" + data +
                ", salaray=" + salaray +
                ", dep=" + dep;
    }
}
