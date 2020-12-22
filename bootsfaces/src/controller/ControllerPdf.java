package controller;

import conexion.conectar;
import dao.pdfdao;
import vo.pdfvo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.sun.faces.context.ExternalContextImpl;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "controllerPdfAyuda")
@SessionScoped
public class ControllerPdf {
	public static final String INICIO = "/bootsfaces/faces/index.xhtml";
    public static final String INSERT_OR_EDIT = "/Pagina2.jsp";

    String estado = null;
    pdfdao pdfdao;
    int id_pdf = -1;
    String forward = "";
    
    public ControllerPdf() {
        pdfdao = new pdfdao();
    }
    
    public void delete(String id) {
        int studentId = Integer.parseInt(id);
        pdfdao.Eliminar_PdfVO(studentId);
    }
    
    public void verPdf(pdfvo datos) {
    	
    	int Id_documents = datos.getCodigopdf();
    	conectar con = new conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;

        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        ExternalContext externalContext = facesContext.getExternalContext(); 
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse(); 
        
        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletResponse response =(HttpServletResponse)context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline=filename=file.pdf");
        
        try {

            int id = Id_documents;
            ps = con.getConnection().prepareStatement("SELECT Documents FROM archivos WHERE Id_documents = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);
            String name = datos.getNombrepdf();
         // Initialize response. 
            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide. 
            response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
            response.setHeader("Content-Disposition", "attachment; filename=" + "\"" + name  + ".pdf\"");
            //response.setHeader("Content-disposition", "attachment; filename=\"name.pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead. 

           // Write file to response. 
            OutputStream output = response.getOutputStream(); 
            output.write(b); 
            output.close(); 

            // Inform JSF to not take the response in hands. 
            facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed. 
            //bos.close();
 
           /* response.getOutputStream().write(b);
            
            response.setContentLength(b.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(b, 0, b.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();*/
            
       
//           HttpServletResponse response = getServletResponse();
    
            /*response.setContentType(null);
            response.setContentLength(b.length);
            String nome = "algo";
			response.addHeader("Content-Disposition", "attachment; filename=" + "\"" + nome  + "\"");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(b, 0, b.length);
            outputStream.flush();
            outputStream.close();*/
            
     
            ps.close();
            rs.close();
            con.desconectar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    public void eliminar(pdfvo datos) {
    	int Id_documents = datos.getCodigopdf();
    	pdfdao pdf = new pdfdao();
    	pdf.Eliminar_PdfVO(Id_documents);
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(INICIO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*public String editar (pdfvo datos) {
    	int Id_documents = datos.getCodigopdf();
    	String nombre = datos.getNombrepdf();
    	return "editar?faces-redirect=true&Id_documents="+Id_documents+"&nombrepdf="+nombre+"&datos="+datos;
    }*/   
}
