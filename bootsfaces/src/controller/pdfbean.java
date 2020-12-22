package controller;
import java.io.InputStream;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import vo.pdfvo;
import dao.pdfdao;

@ManagedBean(name = "controllerManagedBean")
@SessionScoped
public class pdfbean {

	int codigopdf;
	String nombrepdf;
	private byte[] archivopdf2;
	private pdfdao pdfdao;
	
	private static ArrayList<pdfvo> pdfList;

	public pdfbean() {
		pdfdao =new pdfdao();
		pdfList = pdfdao.Listar_PdfVO();
	}

	public Integer getCodigopdf() {
		return codigopdf;
	}

	public void setCodigopdf(Integer codigopdf) {
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


	public ArrayList<pdfvo> getpdfList() {
		pdfList = pdfdao.Listar_PdfVO();
		return pdfList;
	}
	

}
