package persistencia;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class LlibreDao {

    private Connection con;

    public LlibreDao(Connection con) {
        this.con = con;
    }

    /*public boolean afegir(Llibre llib) {
        boolean afegit = true;
        PreparedStatement pt = null;
        String sentencia = "INSERT INTO LLIBRES(ISBN,TITOL,AUTOR,EDITORIAL,ANYEDICIO,ESTOC)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, llib.getIsbn());
            pt.setString(2, llib.getTitol());
            pt.setString(3, llib.getAutor());
            pt.setString(4, llib.getEditorial());
            pt.setInt(5, llib.getAnyEdicio());
            pt.setInt(6, llib.getEstoc());

            if (pt.executeUpdate() == 0) {
                afegit = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        } finally {
            tancarRecurs(pt);
        }

        return afegit;
    }*/
    
    public boolean afegir(Llibre llib) {
        boolean afegit = true;
        PreparedStatement pt = null;
        String sentencia = "INSERT INTO LLIBRESS (ISBN, TITOL, AUTOR, EDITORIAL, ANYO, ESTOC) VALUES (?,?,?,?,?,?)";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, llib.getIsbn());
            pt.setString(2, llib.getTitol());
            pt.setString(3, llib.getAutor());
            pt.setString(4, llib.getEditorial());
            pt.setInt(5, llib.getAnyEdicio());
            pt.setInt(6, llib.getEstoc());

            if (pt.executeUpdate() == 0) {
                afegit = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        } finally {
            tancarRecurs(pt);
        }

        return afegit;
    }

    public Llibre cercarPerISBN(String isbn) {
        String consulta = " SELECT * FROM LLIBRESS WHERE isbn='" + isbn + "'";
        Statement st;
        ResultSet rs;
        Llibre llib = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                llib = new Llibre(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llib;
    }

    public boolean modificar(Llibre llibre) {
        boolean modificat = true;
        PreparedStatement pt = null;
        String sentencia = "UPDATE LLIBRESS SET TITOL = ?, AUTOR = ?, EDITORIAL = ?, ANYO = ?, ESTOC = ? WHERE ISBN = ?";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, llibre.getTitol());
            pt.setString(2, llibre.getAutor());
            pt.setString(3, llibre.getEditorial());
            pt.setInt(4, llibre.getAnyEdicio());
            pt.setInt(5, llibre.getEstoc());
            pt.setString(6, llibre.getIsbn());

            if (pt.executeUpdate() == 0) {
                modificat = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modificat = false;
        } finally {
            tancarRecurs(pt);
        }
        return modificat;
    }

    public boolean eliminar(String isbn) {
        boolean eliminat = false;
        String consulta = "DELETE FROM LLIBRESS WHERE ISBN = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, isbn);
            ps.executeUpdate();
            eliminat = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return eliminat;
    }

    public List<Llibre> cercarTots() {
        String consulta = " SELECT * FROM LLIBRESS";
        Statement st;
        ResultSet rs;
        List<Llibre> llista = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                llista.add(new Llibre(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llista;
    }

    private void tancarRecurs(AutoCloseable r) {
        try {
            r.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(LlibreDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
