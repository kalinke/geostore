package br.com.geostore.controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.EmpresaDAO;
import br.com.geostore.dao.StatusEmpresaDAO;
import br.com.geostore.entity.Empresa;
import br.com.geostore.entity.StatusEmpresa;
import br.com.geostore.entity.UnidadeFederacao;
import br.com.geostore.entity.Usuario;
import br.com.geostore.validator.DocumentoValidator;

@Name("empresaController")
@Scope(ScopeType.CONVERSATION)
public class EmpresaController {

	@In(create=true) private EmpresaDAO empresaDAO;
	@In(create=true) private StatusEmpresaDAO statusEmpresaDAO;
	
	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
	private UnidadeFederacao unidadeFederacao = new UnidadeFederacao();	
	private Empresa empresa = new Empresa();
	private Long idEmpresa;
	
	public EmpresaController() {
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}

	public String nova(){
		this.empresa = new Empresa();
		this.unidadeFederacao = new UnidadeFederacao();	
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.empresa = empresaDAO.buscarPorId(idEmpresa);
		this.unidadeFederacao = this.empresa.getEndereco().getCidade().getUnidadeFederacao();
		
		return "EDITAR";
	}


	public String cancelar(){	
		return "CANCELAR";
	}
	
	
	public String salvar(){		
		try{			
			validar();	
					
			empresaDAO.salvar(empresa);		
			return "SALVAR";

		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}		
	}
	
	public void validar() throws Exception{				
		
		if(!DocumentoValidator.validarCNPJCPF(empresa.getDocumento()))
			throw new RuntimeException("CNPJ digitado é inválido!");			
		
		
		if (empresaDAO.buscarPorCNPJ(empresa))
			throw new RuntimeException("CNPJ digitado já existe!");			
		
		
		if(empresa.getEndereco().getCidade()==null || empresa.getEndereco().getCidade().getDescricao().isEmpty())
			throw new RuntimeException("Selecione uma Cidade!");		
					
	}	
	
	
	
	public String remover() throws Exception{		
		empresaDAO.excluir(empresa);
		return "EXCLUIR";
	}

	
public void buscarCoordenadas() throws Exception{
		
		String status = "";
        String latitude = "";           
        String longitude = "";
         
        String endereco = "";
        String sURL = "";
 
        if(empresa.getEndereco().getLogradouro()!=null) endereco+= empresa.getEndereco().getLogradouro();
        if(empresa.getEndereco().getNumeroLogradouro()!=null) endereco += " " + empresa.getEndereco().getNumeroLogradouro();
        if(empresa.getEndereco().getBairro() != null) endereco += " " + empresa.getEndereco().getBairro()  ;
        if(empresa.getEndereco().getCEP() != null)	endereco += " " + empresa.getEndereco().getCEP();
        if(empresa.getEndereco().getCidade() != null )	endereco += " " + empresa.getEndereco().getCidade().getDescricao() + " " + empresa.getEndereco().getCidade().getUnidadeFederacao().getDescricao();

        
        
        
        endereco = java.net.URLEncoder.encode(endereco, "UTF-8");
        
        sURL = "http://maps.google.com/maps/api/geocode/xml?address=" + endereco + "&language=pt-BR&sensor=false";         
        
		URL url = new URL(sURL);
		InputStream inputXML = url.openStream();			
		
		String xml = "";
		
		if (inputXML != null) {
			Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            
            Reader reader = new BufferedReader(new InputStreamReader(inputXML, "UTF-8"));
            int n;
            
            while ((n = reader.read(buffer)) != -1) {
            	writer.write(buffer, 0, n);
            }            
         
            inputXML.close();          
            xml =  writer.toString();
            

            
            int statusInicio = xml.indexOf("<status>");
            int statusFim = xml.indexOf("</status>");
            
            int latInicio = xml.indexOf("<lat>");
            int latFim = xml.indexOf("</lat>");
            
            int lngInicio = xml.indexOf("<lng>");
            int lngFim = xml.indexOf("</lng>");
            
            status = xml.substring(statusInicio + 8, statusFim);
            latitude = xml.substring(latInicio + 5, latFim);            
            longitude = xml.substring(lngInicio  + 5, lngFim);
		
		}
         
		empresa.getEndereco().setLatitude(latitude);
		empresa.getEndereco().setLongitude(longitude);
		
	}
	
	public void validarCoordenadas() throws Exception{
		
		String sCoord = empresa.getEndereco().getLatitude() + " " + empresa.getEndereco().getLongitude();
		sCoord = java.net.URLEncoder.encode(sCoord, "UTF-8");
		String sURL = "http://maps.google.com.br/maps?q=" + sCoord;
		
		
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		URI uri = null;
		
		uri = new URI(sURL);
		desktop.browse(uri);
		
	}
	
	
	
	@Factory
	public List<Empresa> getEmpresas() throws Exception{		
		return empresaDAO.buscarPorUsuarioLogado(usuarioLogado);
	}
	
	@Factory
	public List<StatusEmpresa> getStatusEmpresas() throws Exception{		
		return statusEmpresaDAO.buscarTodos();
	}
	
	
	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}
	
	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
