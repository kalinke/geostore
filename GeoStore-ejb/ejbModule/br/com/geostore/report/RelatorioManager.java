package br.com.geostore.report;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.Produto;
import br.com.geostore.entity.Usuario;

/**Entidade responsável por gerenciar todas as funcionalidades relacionadas aos relatórios do sistemas
 * @author Frederico Viana Almeida
 * @version 1.0
 * @see AbstractManager
 *
 */
@Name("relatorioManager")
@Scope(ScopeType.CONVERSATION)
@SuppressWarnings("unchecked")
public class RelatorioManager {
	
	@In	FacesMessages facesMessages;
	@In EntityManager entityManager;

	/**
	 *	Método responsável por gerar os relatórios da empresa
	 *
	 */
	public void geraRelatorioListaEmpresas(){
        
		List<Empresa> list = new ArrayList<Empresa>();
		
		list = entityManager.createQuery(" from Empresa ").getResultList();
			
        if (list.isEmpty()) {
            facesMessages.add("Não existem demandas cadastradas");
            return;
        }

        JasperPrint impressao = null;
        Map<String, Object> param = new HashMap<String, Object>();
       
        // Gerando as imagens do relatório
        InputStream cabecalho = this.getClass().getResourceAsStream("/br/com/geostore/report/logoGeostore.png");
        Image imgCabecalho = null;
		try {
			imgCabecalho = ImageIO.read(cabecalho);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        imgCabecalho = gerarImagem(imgCabecalho);
        
        param.put("cabecalho", imgCabecalho);
        try {
            
            InputStream pathJasper;
 	        pathJasper = this.getClass().getResourceAsStream("/br/com/geostore/report/relatorio_lista_empresas.jasper");
            impressao = JasperFillManager.fillReport(pathJasper, param , new JRBeanCollectionDataSource(list) );
            
          //disponibilizar o pdf para download
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            
            JasperExportManager.exportReportToPdfStream(impressao, pdfStream);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_empresas.pdf\"");
            response.setHeader("Cache-Control", "no-cache");
//            JasperViewer.viewReport(impressao,false);
            ServletOutputStream flusher = response.getOutputStream();
            pdfStream.writeTo(flusher);
            flusher.flush();
            flusher.close();
            FacesContext.getCurrentInstance().responseComplete();
            pdfStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	public void geraRelatorioListaLojas(){
        
		List<Loja> list = new ArrayList<Loja>();
		
		list = entityManager.createQuery(" from Loja ").getResultList();
			
        if (list.isEmpty()) {
            facesMessages.add("Não existem dados cadastrados");
            return;
        }

        JasperPrint impressao = null;
        Map<String, Object> param = new HashMap<String, Object>();
       
        // Gerando as imagens do relatório
        InputStream cabecalho = this.getClass().getResourceAsStream("/br/com/geostore/report/logoGeostore.png");
        Image imgCabecalho = null;
		try {
			imgCabecalho = ImageIO.read(cabecalho);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        imgCabecalho = gerarImagem(imgCabecalho);
        
        param.put("cabecalho", imgCabecalho);
        try {
            
            InputStream pathJasper;
 	        pathJasper = this.getClass().getResourceAsStream("/br/com/geostore/report/relatorio_lista_lojas.jasper");
            impressao = JasperFillManager.fillReport(pathJasper, param , new JRBeanCollectionDataSource(list) );
            
          //disponibilizar o pdf para download
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            
            JasperExportManager.exportReportToPdfStream(impressao, pdfStream);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_lista_lojas.pdf\"");
            response.setHeader("Cache-Control", "no-cache");
//            JasperViewer.viewReport(impressao,false);
            ServletOutputStream flusher = response.getOutputStream();
            pdfStream.writeTo(flusher);
            flusher.flush();
            flusher.close();
            FacesContext.getCurrentInstance().responseComplete();
            pdfStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	public void geraRelatorioListaProdutos(){
        
		List<Produto> list = new ArrayList<Produto>();
		
		list = entityManager.createQuery(" from Produto ").getResultList();
			
        if (list.isEmpty()) {
            facesMessages.add("Não existem dados cadastrados");
            return;
        }

        JasperPrint impressao = null;
        Map<String, Object> param = new HashMap<String, Object>();
       
        // Gerando as imagens do relatório
        InputStream cabecalho = this.getClass().getResourceAsStream("/br/com/geostore/report/logoGeostore.png");
        Image imgCabecalho = null;
		try {
			imgCabecalho = ImageIO.read(cabecalho);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        imgCabecalho = gerarImagem(imgCabecalho);
        
        param.put("cabecalho", imgCabecalho);
        try {
            
            InputStream pathJasper;
 	        pathJasper = this.getClass().getResourceAsStream("/br/com/geostore/report/relatorio_lista_produtos.jasper");
            impressao = JasperFillManager.fillReport(pathJasper, param , new JRBeanCollectionDataSource(list) );
            
          //disponibilizar o pdf para download
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            
            JasperExportManager.exportReportToPdfStream(impressao, pdfStream);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_lista_produtos.pdf\"");
            response.setHeader("Cache-Control", "no-cache");
//            JasperViewer.viewReport(impressao,false);
            ServletOutputStream flusher = response.getOutputStream();
            pdfStream.writeTo(flusher);
            flusher.flush();
            flusher.close();
            FacesContext.getCurrentInstance().responseComplete();
            pdfStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	public void geraRelatorioListaUsuarios(){
        
		List<Usuario> list = new ArrayList<Usuario>();
		
		list = entityManager.createQuery(" from Usuario ").getResultList();
			
        if (list.isEmpty()) {
            facesMessages.add("Não existem dados cadastrados");
            return;
        }

        JasperPrint impressao = null;
        Map<String, Object> param = new HashMap<String, Object>();
       
        // Gerando as imagens do relatório
        InputStream cabecalho = this.getClass().getResourceAsStream("/br/com/geostore/report/logoGeostore.png");
        Image imgCabecalho = null;
		try {
			imgCabecalho = ImageIO.read(cabecalho);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        imgCabecalho = gerarImagem(imgCabecalho);
        
        param.put("cabecalho", imgCabecalho);
        try {
            
            InputStream pathJasper;
 	        pathJasper = this.getClass().getResourceAsStream("/br/com/geostore/report/relatorio_lista_usuarios.jasper");
            impressao = JasperFillManager.fillReport(pathJasper, param , new JRBeanCollectionDataSource(list) );
            
          //disponibilizar o pdf para download
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            
            JasperExportManager.exportReportToPdfStream(impressao, pdfStream);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_lista_usuarios.pdf\"");
            response.setHeader("Cache-Control", "no-cache");
//            JasperViewer.viewReport(impressao,false);
            ServletOutputStream flusher = response.getOutputStream();
            pdfStream.writeTo(flusher);
            flusher.flush();
            flusher.close();
            FacesContext.getCurrentInstance().responseComplete();
            pdfStream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	
	/**
	 *	Método responsável por gerar as imagens do relatório em um formato padronizado
	 *
	 */
	 public static BufferedImage gerarImagem(Image image){
	        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
	        Graphics bg = bi.getGraphics();
	        bg.drawImage(image, 0, 0, null);  
	        bg.dispose();
	        return bi;
	 }
	
}

