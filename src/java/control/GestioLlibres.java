package control;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;
import persistencia.*;

public class GestioLlibres extends HttpServlet {

    private Connection con;
    private ConfiguracioConnexio dbCon;

    @Override
    public void init() throws ServletException {
        super.init();
        dbCon = new ConfiguracioConnexio(this.getInitParameter("driver"),
                this.getInitParameter("cadenaConnexioInt"),
                this.getInitParameter("usuari"), this.getInitParameter("contrasenya"));
        con = dbCon.getConnexio();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LlibreDao dao = new LlibreDao(con);
        /*try (PrintWriter out = response.getWriter()) {
            out.println(dbCon.esOberta());
        }*/

        String action = request.getParameter("accio");
        switch (action) {
            case "afegir":
                String resposta1 = afegirLlibre(request, response);
                request.setAttribute("afegit", resposta1);
                anarAPagina("afegir.jsp", request, response);
                break;
            case "cercar":
                String resposta2 = cercarTotsLlibres(request, response);
                request.setAttribute("cercat", resposta2);
                anarAPagina("cercarTots.jsp", request, response);
                break;
            case "modificar":
                String resposta3 = modificarLlibre(request, response);
                request.setAttribute("modificat", resposta3);
                anarAPagina("modificar.jsp", request, response);
                break;
            case "eliminar":
                String resposta4 = eliminarLlibre(request, response);
                request.setAttribute("eliminat", resposta4);
                anarAPagina("eliminar.jsp", request, response);
                break;

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void destroy() {
        dbCon.tancar();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.        
    }

    /**
     * Redirecciona la petició(Request) a una altra pàgina
     *
     * @param pagina
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void anarAPagina(String pagina, HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
        if (dispatcher != null) {
            dispatcher.forward(req, res);
        }
    }

    private String afegirLlibre(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LlibreDao dao = null;
        String isbn, titol, autor, editorial, any, estok;
        int anyEdicio, estoc;
        boolean validar = true;

        String resposta = "El llibre s'ha afegit correctament";
        if (!(isbn = req.getParameter("isbn_")).matches("[0-9]{13}")) {
            resposta = "ISBN incorrecte, ha d'estar format per 13 dígits";
            validar = false;
        } else if (!(any = req.getParameter("anyEdicio_")).matches("^[1-9][0-9]{1,3}")) {
            resposta = "Any d'edició incorrecte, ha de ser any entre 1000-2999";
            validar = false;
        } else if (!(estok = req.getParameter("estoc_")).matches("[0-9]{1,3}")) {
            resposta = "Estoc incorrecte";
            validar = false;
        } else if ((titol = req.getParameter("titol_")) == null
                || (autor = req.getParameter("autor_")) == null
                || (editorial = req.getParameter("editorial_")) == null) {
            resposta = "s'han d'emplenar tots els camps";
            validar = false;
        } else {
            anyEdicio = Integer.parseInt(any);
            estoc = Integer.parseInt(estok);

            dao = new LlibreDao(con);
            dao.afegir(new Llibre(isbn, titol, autor, editorial, anyEdicio, estoc));
        }
      
        return resposta;
    }
    
    private String modificarLlibre(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        return null;
    }
    
    private String eliminarLlibre(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        return null;
    }
    
    private String cercarTotsLlibres(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        return null;
    }
}
