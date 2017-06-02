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
    
        public boolean afegir(Llibre l) {
        boolean afegit = true;
        PreparedStatement pt = null;
        String sentencia = "INSERT INTO Llibres(TITOL, AUTOR, ANYO, ISBN, EDITORIAL, ESTOC)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, l.getTitol());
            pt.setString(2, l.getAutor());
            pt.setInt(3, l.getAnyEdicio());
            pt.setString(4, l.getIsbn());
            pt.setString(5, l.getEditorial());
            pt.setInt(6, l.getEstoc());

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
        String consulta = " SELECT * FROM LLIBREs WHERE isbn='" + isbn + "'";
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

    public boolean modificar(Llibre l) {
        String consulta = "UPDATE Llibres SET TITOL = ?, AUTOR = ?, ANYO = ?, EDITORIAL = ? , ESTOC = ? WHERE ISBN = ? ";
        PreparedStatement ps;
        boolean modificado = false;
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, l.getTitol());
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getAnyEdicio());
            ps.setString(6, l.getIsbn());
            ps.setString(4, l.getEditorial());
            ps.setInt(5, l.getEstoc());
            modificado = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modificado;
    }

    public boolean eliminar(String isbn) {
        boolean eliminat = false;
        String consulta = "DELETE FROM Llibres WHERE ISBN = ?";
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
        String consulta = "SELECT * FROM LLIBRES";
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
