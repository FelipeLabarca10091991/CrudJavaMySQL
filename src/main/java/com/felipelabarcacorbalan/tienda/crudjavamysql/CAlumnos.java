/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.felipelabarcacorbalan.tienda.crudjavamysql;


import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class CAlumnos {
    
    int a;
    int id; 
    String NombreAlumno; 
    String ApellidoAlumno; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAlumno() {
        return NombreAlumno;
    }

    public void setNombreAlumno(String NombreAlumno) {
        this.NombreAlumno = NombreAlumno;
    }

    public String getApellidoAlumno() {
        return ApellidoAlumno;
    }

    public void setApellidoAlumno(String ApellidoAlumno) {
        this.ApellidoAlumno = ApellidoAlumno;
    }

    //Método para insertar alumno. Recibe 2 parametros JTextField definidos en el form. 
    public void insertarAlumno(JTextField txtNames, JTextField txtLastNames){
    
        //Seteamos nombre y apellido usando los setters de la clase. El parámetro que recibe el setter viene del form.
        setNombreAlumno(txtNames.getText());
        setApellidoAlumno(txtLastNames.getText());
        
        //Instanciamos la Clase Conexion para usar el método estableceConexion()
        CConection con = new CConection();
        con.estableceConexion();
        
        //Creamos un String consulta, donde irá la sentencia SQL genérica: los valores se reemplazan con ? y se asignan más abajo
        String consulta = "insert into Alumnos (nombres,apellidos) values (?,?);";
        
        try{
            
            //Se prepara el envío de la consulta.
            CallableStatement cs = con.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Fallo en la insersión de alumno " + e.toString());
        }
        
        
    }
    
    
}
