package persistencia;

import java.sql.*;

public class ConfiguracioConnexio {

    private Connection con = null;
    private String driver;
    private String cadenaConnexio;
    private String usuari;
    private String contrasenya;
    private boolean esOberta = false;

    public ConfiguracioConnexio() {
        driver="oracle.jdbc.driver.OracleDriver";
//        cadenaConnexio = "jdbc:oracle:thin:@192.168.180.10:1521:INSLAFERRERI";
        cadenaConnexio = "jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI";
        usuari = "AITOR_ROMERO_CONNECTION";
        contrasenya = "1234";
        //usuari = "aberga2";
        //contrasenya = "1234";
    }

    public ConfiguracioConnexio(String driver,String cadenaConnexio, String usuari, String contrasenya) {
        this.driver=driver;
        this.cadenaConnexio = cadenaConnexio;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    /**
     * Carrega la classe OracleDriver de la llibreria jdbc per a Oracle, obté
     * una instància de la classe Connection, amb la connexió oberta amb el SGBD
     * a la BD indicada a la cadena de connexió.
     *
     * @return torna true si s'estableix la connexió i false en cas contrari.
     */
    public Connection getConnexio() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadenaConnexio, usuari, contrasenya);
            esOberta = true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

    /**
     * Comprova si l'objecte Connection s'ha creat i si la connexió és oberta.
     * Si és oberta la tanca.
     *
     * @return true si la connexió estava oberta i es tanca, false en cas de no
     * estar creat l'objecte Connection o que ja estava tancada.
     */
    public boolean tancar() {
        boolean tancat = true;
        try {
            if (con != null && !con.isClosed()) {
                con.close();                
            }
            esOberta = false;
        } catch (SQLException ex) {
            System.out.println("Connexió ja tancada.");
            tancat = false;
        }
        return tancat;
    }

    public boolean esOberta() {
        return esOberta;
    }
    

}
