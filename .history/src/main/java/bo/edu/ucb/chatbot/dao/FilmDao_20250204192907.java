package bo.edu.ucb.chatbot.dao;

import bo.edu.ucb.chatbot.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FilmDao {

    private DataSource dataSource;

    @Autowired
    public FilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> findByTitle(String title) {
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, " +
                "   f.title, " +
                "   f.description, " +
                "   f.release_year, " +
                "   l.name as language , " +
                "   ol.name as original_language, " +
                "   f.length, " +
                "   f.rating, " +
                "   f.special_features, " +
                "   f.last_update " +
                " FROM film f " +
                "     LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "     LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                " WHERE " +
                "   UPPER(title) LIKE ( ? )" ;

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                ) {
            System.out.println(query);
            pstmt.setString(1, "%"+title.toUpperCase()+ "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public List<Film> findByActor(String actor) {
        String[]full_name=actor.split(" ");
        System.out.println(Arrays.asList(full_name));
        List<Film> result = new ArrayList<>();
        String query = ""+
        "SELECT f.film_id, "+
        "f.title, "+
        "f.description, "+
        "f.release_year, "+
        "l.name as language , "+
        "ol.name as original_language, "+
        "f.length, "+
        "f.rating, "+
        "f.special_features, "+
        "f.last_update, "+
        "a.first_name, "+
        "a.last_name "+
        "FROM film f "+
        "LEFT JOIN language l ON ( f.language_id = l.language_id) "+
        "LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) "+
        "JOIN film_actor fa ON (f.film_id=fa.film_id) "+
        "JOIN actor a ON (a.actor_id=fa.actor_id) "+
        "WHERE "+
        "a.first_name=? AND "+
        "a.last_name=? ";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                ) {
                    pstmt.setString(1, full_name[0]);
                    pstmt.setString(2, full_name[1]);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    public List<Film> findByTitleActor(String titulo,String actor) {
        String[]full_name=actor.split(" ");
        System.out.println(Arrays.asList(full_name));
        List<Film> result = new ArrayList<>();
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, l.name as language , ol.name as original_language, f.length, f.rating, f.special_features, f.last_update, a.first_name, a.last_name FROM film f LEFT JOIN language l ON ( f.language_id = l.language_id) LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) JOIN film_actor fa ON (f.film_id=fa.film_id) JOIN actor a ON (a.actor_id=fa.actor_id) WHERE a.first_name=? AND a.last_name=? AND UPPER(f.title) LIKE ( ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
                ) {
            pstmt.setString(1, full_name[0]);
            pstmt.setString(2, full_name[1]);
            pstmt.setString(3, "%"+titulo.toUpperCase()+ "%");
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setReleaseYear(rs.getShort("release_year"));
                film.setLanguage("language");
                film.setOriginalLanguage("original_language");
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecialFeatures(rs.getString("special_features"));
                java.sql.Date lastUpdate = rs.getDate("last_update");
                film.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                result.add(film);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // TODO gestionar correctamente la excepción
        }
        return result;
    }
    
}
