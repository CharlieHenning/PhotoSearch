/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Owner
 */
public class DBConnector extends HttpServlet {
	Connection conn;
	ResultSet results;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String searchValue = request.getParameter("searchValue");
            try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("nowI'm here");
			
		}

		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://mysql.cs.uwf.edu/CHH7?user=chh7&password=tH4Wes2a");
		} catch (SQLException e) {
			System.out.println("i'm here!");
		}
            if(creatorSearch(searchValue) == true){
            try {
                ResultSet rs = getResults();
             //   ResultSetMetaData rsMetaData = rs.getMetaData();
                while(rs.next()){
                    out.println("Title: " + rs.getString(2) + "<br/>");
                    out.println("Creator Name: " + rs.getString(3) + "<br/>");
                    out.println("Creator Email:<a href='mailto:" + rs.getString(4) + "'>" + rs.getString(4) + "</a> <br/>");
                    out.println("Creator Home page:" + rs.getString(5) + "<br/>");
                    out.println("CopyRight: " + rs.getString(6) + "<br/>");
                    out.println("<img src='" + rs.getString(8) + "' width=" + rs.getString(9) + "' height='" + rs.getString(10) + "'/> <br/>");
                    out.println("Keyword: " + rs.getString(11) + " <br/>");
                   
                                         
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
        
      }      
    }
    public boolean creatorSearch(String searchVariable){
                boolean gotResults = false;
		String qry = "SELECT * from GALLERYCHH7 where creatorname like '%" + searchVariable + "%'";
	try {
		Statement searchCreator = conn.createStatement();
		ResultSet rs = searchCreator.executeQuery(qry);
		if (rs.isBeforeFirst() ) {    
                    gotResults = true;
                    setResults(rs);
                }   

		
                
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("statement executed");
	}
        return gotResults;
}
    	public ResultSet getResults(){
		return results;
	}
	public void setResults(ResultSet theResults){
		results = theResults;
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

}
