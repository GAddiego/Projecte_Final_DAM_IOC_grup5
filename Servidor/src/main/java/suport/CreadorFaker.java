/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suport;

import com.github.javafaker.Faker;
import objectes.Llibre;
import objectes.UsuariIntern;

/**
 *
 * @author aleix
 */
public class CreadorFaker {
     Faker faker = new Faker(); // Crea una instancia de la clase Faker
     
    public Llibre crearLlibre(){
         Llibre llibre = new Llibre(); // Crea una instancia de la clase Llibre

        
        llibre.setTitol(faker.book().title());
        llibre.setAutor(faker.book().author());
        llibre.setIsbn(faker.code().isbn10());
        llibre.setEditorial(faker.book().publisher());
        llibre.setAnyPublicacio(faker.number().numberBetween(1950, 2023));
        llibre.setDescripcio(faker.lorem().paragraph());
        llibre.setSinopsi(faker.lorem().sentence());
        llibre.setIllustrador(faker.name().fullName());
        llibre.setRutaPortada("ruta_de_la_portada"); 
        llibre.setPagines(faker.number().numberBetween(100, 500));
        llibre.setIdioma(faker.nation().language());
        llibre.setExemplar(faker.number().numberBetween(1, 10));
        llibre.setNota(faker.lorem().sentence(5));
        llibre.setTitolOriginal(faker.book().title());
        llibre.setTraductor(faker.name().fullName());
        
        return llibre;
    }
    
    public UsuariIntern crearUsuariIntern(){
 
        String user = faker.name().username();
        String pass = faker.internet().password(10, 12, true, true, true);
        String rol = faker.options().option("administrador", "usuario", "invitado");
        String nom = faker.name().firstName();
        String primerCognom = faker.name().lastName();
        String segonCognom = faker.name().lastName();
        String email = faker.internet().emailAddress();
        
        UsuariIntern usuari = new UsuariIntern(user,pass,rol,nom,primerCognom,segonCognom,email);
        
        return usuari;
    }
}
