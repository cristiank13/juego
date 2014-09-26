/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoprototipo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cristiancamilo
 */
public class Juegoprototipo {
    
    Connection conexion;
    Statement st;
    
    public void Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // Establecemos la conexión con la base de datos. 
            conexion = DriverManager.getConnection ("jdbc:mysql://127.0.0.1/juego","root","");
    //        JOptionPane.showMessageDialog(null, "Conecto!");
            st=conexion.createStatement();
        } catch (ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Insertar (String Tabla, String Campos, String Valores/*, String CamposValores*/){
        Conectar();
        String sql="INSERT INTO "+Tabla+" ("+Campos+")values("+Valores+")";
        System.out.println(sql);
        try {
            st.executeUpdate(sql);
           
        } catch (SQLException ex) {
            
//            Actualizar(Tabla/*, CamposValores*/);
           
        }
          finally{  
            try{    
                st.close();  
                conexion.close();  
                }catch (Exception e){                 
                    e.printStackTrace();  
                }
        }
    }
    
    public ResultSet Consultar(String sql){
        Conectar();
        ResultSet rs=null;
        try {
            rs= st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Juegoprototipo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void Actualizar (String Tabla, String CamposValores){
        Conectar();
        String sql="UPDATE "+Tabla+" SET "+CamposValores;
        try {
            st.executeUpdate(sql);
           
        } catch (SQLException ex) {
              System.out.println(sql);
              System.out.println(ex);
              
        }

    }
    
    public void Borrar(String Tabla, String id, String Valor){
        Conectar();
        String sql="DELETE FROM "+Tabla+" WHERE "+id+ "="+Valor;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
           
        }
          finally{  
            try{    
                st.close();  
                conexion.close();  
                }catch (Exception e){                 
                    e.printStackTrace();  
                }
        }
    }
    
    
}
