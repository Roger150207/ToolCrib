/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author rogeer
 */
public interface DAO<T> {
    List<T> listarTodos() throws SQLException;
}
