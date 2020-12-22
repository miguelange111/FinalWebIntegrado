package controller;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import dao.pdfdao;
import vo.pdfvo;

import org.apache.commons.io.FilenameUtils; 
import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean(name = "beanAyuda")
@SessionScoped

public class bean {
	

	int codigopdf;
	String nombrepdf;
	private byte[] archivopdf2;
	private pdfdao pdfdao;
	InputStream archivopdf;
	private UploadedFile uploadedFile;
	
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
	public pdfdao getPdfdao() {
		return pdfdao;
	}
	public void setPdfdao(pdfdao pdfdao) {
		this.pdfdao = pdfdao;
	}
	
	/*public bean(int codigopdf, String nombrepdf, UploadedFile uploadedFile) {
		super();
		this.codigopdf = codigopdf;
		this.nombrepdf = nombrepdf; 
		 try {
	            if (uploadedFile.getSize() > 0) {
	                System.out.println(FilenameUtils.getName(uploadedFile.getName()));
	                System.out.println(uploadedFile.getSize());
	                System.out.println(uploadedFile.getContentType());
	                this.archivopdf = uploadedFile.getInputStream();
	            }
	        } catch (Exception ex) {
	            System.out.println("fichero: "+ex.getMessage());
	        }

	}*/

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public String insertar() {
		pdfvo pdf = new pdfvo(getCodigopdf() , getNombrepdf() ,getUploadedFile());
		pdfdao pdf1 = new pdfdao();
		pdf1.Agregar_PdfVO(pdf);
		return "index";
    }

	public String editar() {
		pdfvo pdf = new pdfvo(getCodigopdf() , getNombrepdf() ,getUploadedFile());
		pdfdao pdf1 = new pdfdao();
		pdf1.Modificar_PdfVO2(pdf);
		return "index";
    }
}
