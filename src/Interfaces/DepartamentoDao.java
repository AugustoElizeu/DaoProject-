package Interfaces;

import Entidades.Departamento;

import java.util.List;

public interface DepartamentoDao {
    void insert(Departamento obj);
    void update(Departamento obj);
    void deleteById(Integer id);
    Departamento findById(Integer id);
    List<Departamento> findAll();
}
