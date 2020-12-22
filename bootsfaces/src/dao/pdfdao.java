package dao;

import conexion.conectar;
import vo.pdfvo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pdfdao {

    public ArrayList<pdfvo> Listar_PdfVO() {
        ArrayList<pdfvo> list = new ArrayList<pdfvo>();
        conectar conec = new conectar();
        String sql = "SELECT * FROM archivos;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	pdfvo vo = new pdfvo();
                vo.setCodigopdf(rs.getInt(1));
                vo.setNombrepdf(rs.getString(2));
                vo.setArchivopdf2(rs.getBytes(3));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    public ArrayList<pdfvo> Listar_PdfVO2(String nombrepdf) {
        ArrayList<pdfvo> list = new ArrayList<pdfvo>();
        conectar conec = new conectar();
        String sql = "SELECT * FROM archivos WHERE Nombre_documents = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,nombrepdf);
            rs = ps.executeQuery();
            while (rs.next()) {
            	pdfvo vo = new pdfvo();
                vo.setCodigopdf(rs.getInt(1));
                vo.setNombrepdf(rs.getString(2));
                vo.setArchivopdf2(rs.getBytes(3));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }
    
    public void Agregar_PdfVO(pdfvo vo) {
    	conectar conec = new conectar();
        String sql = "INSERT INTO archivos (Id_documents, Nombre_documents, Documents) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getCodigopdf());
            ps.setString(2, vo.getNombrepdf());
            ps.setBlob(3, vo.getArchivopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }


    public void Modificar_PdfVO(pdfvo vo) {
    	conectar conec = new conectar();
        String sql = "UPDATE archivos SET 	Nombre_documents = ?, Documents = ? WHERE Id_documents = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombrepdf());
            ps.setBlob(2, vo.getArchivopdf());
            ps.setInt(3, vo.getCodigopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    public void Modificar_PdfVO2(pdfvo vo) {
    	conectar conec = new conectar();
        String sql = "UPDATE archivos SET 	Documents = ? WHERE Id_documents = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setBlob(1, vo.getArchivopdf());
            ps.setInt(2, vo.getCodigopdf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    public void Eliminar_PdfVO(int id) {
    	conectar conec = new conectar();
        String sql = "DELETE FROM archivos WHERE Id_documents = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }
    
    public static boolean validate(String user, String password) {
    	conectar conec = new conectar();
    	String sql = "Select uname, password from Users where uname = ? and password = ?";
		PreparedStatement ps = null;

		try {
			ps = conec.getConnection().prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
		}
		return false;
	}

}
