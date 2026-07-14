/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author James
 * @param <T>
 * @param <Cod>
 */
public interface ICrud<T, Cod> {
    //Definir metodos
    int save(T d);
    int update(T d);
    int deleteByCode(Cod code);
    List<T> findAll();
    T findById(Cod code);
}
