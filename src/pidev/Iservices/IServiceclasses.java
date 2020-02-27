/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Iservices;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author daami
 */
public interface IServiceclasses<C> {
    void ajouter(C c) throws SQLException;
    boolean delete(C c) throws SQLException;
    boolean update(C c) throws SQLException;
    List<C> readAll() throws SQLException;
}


