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
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.LojaDAO;
import br.com.geostore.dao.StatusLojaDAO;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.StatusLoja;
import br.com.geostore.entity.UnidadeFederacao;
import br.com.geostore.validator.DocumentoValidator;
import br.com.geostore.validator.EmailValidator;
import br.com.geostore.validator.NomeValidator;
import br.com.geostore.validator.NumeroValidator;

@Name("lojaController")
@Scope(ScopeType.CONVERSATION)
public class LojaController {

	@In(create=true) private LojaDAO lojaDAO;
	@In(create=true) private StatusLojaDAO statusLojaDAO;
	
	@In private FacesMessages facesMessages;
	
	private Loja loja = new Loja();
	private UnidadeFederacao unidadeFederacao = new UnidadeFederacao();	
	private Long idLoja;
	
			
	public String nova(){
		this.loja = new Loja();
		this.unidadeFederacao = new UnidadeFederacao();	
		
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.loja = lojaDAO.buscarPorId(idLoja);
		this.unidadeFederacao = this.loja.getEndereco().getCidade().getUnidadeFederacao();
		return "EDITAR";
	}


	public String cancelar(){		
		return "CANCELAR";
	}
	
	
	public String salvar() throws Exception{
		
		try{			
			validar();
					
			lojaDAO.salvar(loja);		
			return "SALVAR";
			
		}catch (Exception e) {
			facesMessages.add(e.getMessage());
			return null;
		}		

	}
	
	public void validar() throws Exception{              

        NomeValidator nomeValidator = new NomeValidator();
        EmailValidator emailValidator = new EmailValidator();
        NumeroValidator numertoValidator = new NumeroValidator();    
        
        //if(loja.getEmpresaSuperior().getDocumento()==null || loja.getEmpresaSuperior().getDocumento().isEmpty())
          // throw new RuntimeException("É necessário selecionar a empresa superior!");  
        
        if(loja.getStatusLoja()==null || loja.getStatusLoja().getDescricao().isEmpty())
            throw new RuntimeException("É necessário selecionar o status!"); 
        
        if(loja.getDocumento().isEmpty())
                throw new RuntimeException("É necessário preencher o CNPJ!");        

        if(!DocumentoValidator.validarCNPJCPF(loja.getDocumento()))
                throw new RuntimeException("CNPJ digitado é inválido!");               

        if (lojaDAO.buscarPorCNPJ(loja))
                throw new RuntimeException("CNPJ digitado já existe!");       

        if(loja.getRazaoSocial().isEmpty())
                throw new RuntimeException("É necessário preencher a razão social!");       

        if(loja.getNomeFantasia().isEmpty())
                throw new RuntimeException("É necessário preencher o nome fantasia!");       

        if(loja.getInscricaoEstadual().isEmpty())
                throw new RuntimeException("É necessário preencher a inscrição estadual!");       

        if(loja.getContato().isEmpty())
                throw new RuntimeException("É necessário preencher o contato!");       

        if(!nomeValidator.validarNome(loja.getContato()))
                throw new RuntimeException("Nome do contato inválido!");       

        if(loja.getTelefone().isEmpty())
                throw new RuntimeException("É necessário preencher o telefone!");       

        if(loja.getEmail().isEmpty())
                throw new RuntimeException("É necessário preencher o email!");       

        if(!emailValidator.validarEmail(loja.getEmail()))
                throw new RuntimeException("Email inválido!");       

        if(loja.getEndereco().getCEP().isEmpty())
                throw new RuntimeException("É necessário preencher o cep!");       

        if(loja.getEndereco().getLogradouro().isEmpty())
                throw new RuntimeException("É necessário preencher o nome da rua!");       

        if(loja.getEndereco().getNumeroLogradouro().isEmpty())
                throw new RuntimeException("É necessário preencher o número da rua!");       

        if(!numertoValidator.validarNumeroLogradouro(loja.getEndereco().getNumeroLogradouro()))
                throw new RuntimeException("Número da rua inválido!");       

        if(loja.getEndereco().getBairro().isEmpty())
                throw new RuntimeException("É necessário preencher o bairro!");       

        if(!nomeValidator.validarNome(loja.getEndereco().getBairro()))
                throw new RuntimeException("Nome do bairro inválido!");       

        if(loja.getEndereco().getCidade()==null || loja.getEndereco().getCidade().getDescricao().isEmpty())
                throw new RuntimeException("Selecione uma Cidade!");
  
        if(loja.getEndereco().getLatitude().isEmpty() || loja.getEndereco().getLongitude().isEmpty())
                throw new RuntimeException("É necessário buscar as coordenadas!");              
                              
}      	

	public String remover() throws Exception{		
		
		lojaDAO.excluir(loja);
		return "EXCLUIR";
	}
	
public void buscarCoordenadas() throws Exception{
		
		String status = "";
        String latitude = "";           
        String longitude = "";
         
        String endereco = "";
        String sURL = "";
 
        if(loja.getEndereco().getLogradouro()!=null) endereco+= loja.getEndereco().getLogradouro();
        if(loja.getEndereco().getNumeroLogradouro()!=null) endereco += " " + loja.getEndereco().getNumeroLogradouro();
        if(loja.getEndereco().getBairro() != null) endereco += " " + loja.getEndereco().getBairro()  ;
        if(loja.getEndereco().getCEP() != null)	endereco += " " + loja.getEndereco().getCEP();
        if(loja.getEndereco().getCidade() != null )	endereco += " " + loja.getEndereco().getCidade().getDescricao() + " " + loja.getEndereco().getCidade().getUnidadeFederacao().getDescricao();

        
        
        
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
         
		loja.getEndereco().setLatitude(latitude);
		loja.getEndereco().setLongitude(longitude);
		
	}
	
	public void validarCoordenadas() throws Exception{
		
		String sCoord = loja.getEndereco().getLatitude() + " " + loja.getEndereco().getLongitude();
		sCoord = java.net.URLEncoder.encode(sCoord, "UTF-8");
		String sURL = "http://maps.google.com.br/maps?q=" + sCoord;
		
		
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		URI uri = null;
		
		uri = new URI(sURL);
		desktop.browse(uri);
		
	}
	
	@Factory
	public List<Loja> getLojas() throws Exception{		
		return lojaDAO.buscarTodos();
	}
	
	@Factory
	public List<StatusLoja> getStatusLojas() throws Exception{		
		return statusLojaDAO.buscarTodos();
	}
	
	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}
	
	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}
	
	public Loja getLoja() {
		return loja;
	}


	public void setLoja(Loja loja) {
		this.loja = loja;
	}


	public Long getIdLoja() {
		return idLoja;
	}


	public void setIdLoja(Long idLoja) {
		this.idLoja = idLoja;
	}
		
	
}
