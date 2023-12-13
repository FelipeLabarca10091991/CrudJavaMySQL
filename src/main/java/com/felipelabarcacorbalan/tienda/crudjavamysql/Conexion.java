
package com.felipelabarcacorbalan.tienda.crudjavamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar = null; 
    
    String usuario = "root";
    String password = "1234";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario, password);
            //JOptionPane.showMessageDialog(null,"La conexión se ha realizado con éxito");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error a conectarse en la bd. Error:"+ e.toString());
        }
        return conectar;
        
        
    }
      
    
    
    
    
    
}
