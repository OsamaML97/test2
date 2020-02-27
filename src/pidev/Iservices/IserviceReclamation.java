/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Iservices;

import java.sql.SQLException;
import java.util.List;
import Entities.Reclamation;

/**
 *
 * @author daami
 */
public interface IserviceReclamation {

    public void addReclamation (Reclamation R) throws SQLException;

    public List<Reclamation> getReclamation() throws SQLException;

    public Reclamation getById(int id) throws SQLException;

    public void deleteReclamation(Reclamation R) throws SQLException;

    public void deleteReclamation(int id) throws SQLException;

    public void updateReclamation(Reclamation R) throws SQLException;

}
