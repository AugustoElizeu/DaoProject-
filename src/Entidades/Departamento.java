package Entidades;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public Departamento(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Departamento(){

    }
    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //HASHCODE AND EQUALS


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //toString
    public String toString(){
        return "Departament ID "+id+" Nome "+ name;
    }
}
