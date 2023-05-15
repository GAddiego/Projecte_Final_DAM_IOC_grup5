/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package suport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objectes.XifradorContrasenya;
/**
 *
 * @author aleix
 */
public class ActulitzadorPas {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
        String url = "jdbc:postgresql://192.168.1.225:5432/biblioteca"; // Reemplaza con la URL de tu base de datos
        String usuario = "ioc";
        String contraseña = "ioc1234";
        
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña)) {
            // Descargar la contraseña original de la base de datos
            String sqlDescargar = "SELECT contrasenya FROM usuaris";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlDescargar);
            XifradorContrasenya xC = new XifradorContrasenya();
            while (rs.next()) {
                String contraseñaOriginal = rs.getString("contrasenya");
                
                // Cifrar la contraseña utilizando xC.XifradorString
                byte[] pswd = xC.XifradorString(contraseñaOriginal);
                
                // Subir la contraseña cifrada nuevamente a la base de datos
                String sqlSubir = "UPDATE usuaris SET pswd = ? WHERE contrasenya = ?";
                PreparedStatement pstmt = conn.prepareStatement(sqlSubir);
                pstmt.setBytes(1, pswd);
                pstmt.setString(2, contraseñaOriginal);
                pstmt.executeUpdate();
                
                pstmt.close();
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    

