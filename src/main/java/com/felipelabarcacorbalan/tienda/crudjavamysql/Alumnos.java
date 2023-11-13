/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.felipelabarcacorbalan.tienda.crudjavamysql;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Alumnos {
    
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
        Conexion con = new Conexion();
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
    
    public void mostrarAlumnos(JTable dbAlumnos){
    
        Conexion objConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        dbAlumnos.setRowSorter(ordenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        sql = "select * from Alumnos;";
        
        String[] datos = new String[3];
        Statement st;
        
       try{
           st = objConexion.estableceConexion().createStatement();
           
           ResultSet rs = st.executeQuery(sql);
           
           while(rs.next()){
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               
               modelo.addRow(datos);
           }
           
           dbAlumnos.setModel(modelo);
                   
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "No se pudo mostrar registros. Error: " + e.toString());
       }
        
        
    }
    
    public void seleccionarAlumno(JTable dbAlumnos, JTextField txtId, JTextField txtNames, JTextField txtLastNames){
        try{
            int fila = dbAlumnos.getSelectedRow();
            
            if( fila >=0 ){
                txtId.setText(dbAlumnos.getValueAt(fila, 0).toString());
                txtNames.setText(dbAlumnos.getValueAt(fila, 1).toString());
                txtLastNames.setText(dbAlumnos.getValueAt(fila, 2).toString());
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo seleccionar los Alumnos. Error: " +e.toString());
        }
    }

    public void modificarAlumno(JTextField txtId, JTextField txtNames, JTextField txtLastNames){
        
        setId(Integer.parseInt(txtId.getText()));
        setNombreAlumno(txtNames.getText());
        setApellidoAlumno(txtLastNames.getText());
        
        Conexion conexion = new Conexion();
        
        String consulta = "UPDATE Alumnos SET alumnos.nombres =?, alumnos.apellidos = ? WHERE alumnos.id=?;";
        
        try{
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setInt(3, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se modificó el registro. Error: "+ e.toString());
        }
    }

    public void eliminarAlumno(JTextField txtId){
        setId(Integer.parseInt(txtId.getText()));
        
        Conexion conexion = new Conexion();
        
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?;";
        
        try{
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el Alumno");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro. Error: " + e.toString());
        }
    }
}

