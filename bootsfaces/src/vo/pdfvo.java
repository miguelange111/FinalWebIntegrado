package vo;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import conexion.conectar;

import org.apache.commons.io.FilenameUtils; 
import org.apache.myfaces.custom.fileupload.UploadedFile; 

public class pdfvo {
	int codigopdf;
	String nombrepdf;
	private byte[] archivopdf2;
	private UploadedFile uploadedFile;
	InputStream archivopdf;
	
	
	public InputStream getArchivopdf() {
		return archivopdf;
	}


	public void setArchivopdf(InputStream archivopdf) {
		this.archivopdf = archivopdf;
	}


	public int getCodigopdf() {
		return codigopdf;
	}


	public void setCodigopdf(int codigopdf) {
		this.codigopdf = codigopdf;
	}


	public String getNombrepdf() {
		return nombrepdf;
	}


	public void setNombrepdf(String nombrepdf) {
		this.nombrepdf = nombrepdf;
	}


	public byte[] getArchivopdf2() {
		return archivopdf2;
	}


	public void setArchivopdf2(byte[] archivopdf2) {
		this.archivopdf2 = archivopdf2;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public pdfvo() {
	}


	public pdfvo(int codigopdf, String nombrepdf, byte[] archivopdf2) {
		super();
		this.codigopdf = codigopdf;
		this.nombrepdf = nombrepdf;
		this.archivopdf2 = archivopdf2;
	}
	
	public pdfvo(int codigopdf, String nombrepdf, UploadedFile uploadedFile) {
		super();
		this.codigopdf = codigopdf;
		this.nombrepdf = nombrepdf; 
		 try {
	            //if (uploadedFile.getSize() > 0) {
	                System.out.println(FilenameUtils.getName(uploadedFile.getName()));
	                System.out.println(uploadedFile.getSize());
	                System.out.println(uploadedFile.getContentType());
	                this.archivopdf = uploadedFile.getInputStream();
	            //}
	        } catch (Exception ex) {
	            System.out.println("fichero: "+ex.getMessage());
	        }

	}
}
