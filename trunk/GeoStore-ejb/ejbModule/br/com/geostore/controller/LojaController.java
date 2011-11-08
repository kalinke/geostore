package br.com.geostore.controller;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;

import br.com.geostore.dao.CidadeDAO;
import br.com.geostore.dao.LojaDAO;
import br.com.geostore.dao.StatusLojaDAO;
import br.com.geostore.entity.Cidade;
import br.com.geostore.entity.Loja;
import br.com.geostore.entity.StatusLoja;
import br.com.geostore.entity.UnidadeFederacao;
import br.com.geostore.entity.Usuario;
import br.com.geostore.validator.DocumentoValidator;
import br.com.geostore.validator.EmailValidator;
import br.com.geostore.validator.NomeValidator;
import br.com.geostore.validator.NumeroValidator;

@Name("lojaController")
@Scope(ScopeType.CONVERSATION)
public class LojaController {

	@In(create=true) private LojaDAO lojaDAO;
	@In(create=true) private StatusLojaDAO statusLojaDAO;
	@In(create=true) private CidadeDAO cidadeDAO;
	
	@In private FacesMessages facesMessages;
	private Usuario usuarioLogado;
	
	private Loja loja = new Loja();
	private UnidadeFederacao unidadeFederacao = new UnidadeFederacao();	
	private Long idLoja;
	
	String acao = null;
	
	public LojaController(){
		this.usuarioLogado = (Usuario) Contexts.getSessionContext().get("usuarioLogado");
	}
	
			
	public String nova(){
		this.loja = new Loja();
		this.unidadeFederacao = new UnidadeFederacao();	
		acao = "NOVA";
		return "ADICIONAR";
	}
	

	public String editar() throws Exception{
		this.loja = lojaDAO.buscarPorId(idLoja);
		this.unidadeFederacao = this.loja.getEndereco().getCidade().getUnidadeFederacao();
		acao = "EDITAR";
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
        
        if( loja.getEmpresaSuperior()==null)
            	throw new RuntimeException("É necessário selecionar a empresa superior!");  
        
        if(loja.getStatusLoja()==null || loja.getStatusLoja().getDescricao().isEmpty())
            	throw new RuntimeException("É necessário selecionar o status!"); 
        
        if(loja.getDocumento().isEmpty())
                throw new RuntimeException("É necessário preencher o CNPJ!");        

        if(!DocumentoValidator.validarCNPJCPF(loja.getDocumento()))
                throw new RuntimeException("CNPJ digitado é inválido!");  

        if (lojaDAO.buscarPorCNPJ(loja, acao))
                throw new RuntimeException("CNPJ digitado já existe!");       

        if(loja.getRazaoSocial().isEmpty())
                throw new RuntimeException("É necessário preencher a razão social!");       

        if(loja.getNomeFantasia().isEmpty())
                throw new RuntimeException("É necessário preencher o nome fantasia!");       

        if(loja.getInscricaoEstadual().isEmpty())
                throw new RuntimeException("É necessário preencher a inscrição estadual!");       

        //if(loja.getContato().isEmpty())
                //throw new RuntimeException("É necessário preencher o contato!");       

       // if(!nomeValidator.validarNome(loja.getContato()))
               // throw new RuntimeException("Nome do contato inválido!");       

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
  
        if(loja.getEndereco().getLatitude() == null || loja.getEndereco().getLongitude() == null)
            throw new RuntimeException("É necessário buscar as coordenadas!");    
    
        if(!numertoValidator.validarCoordenadas(loja.getEndereco().getLatitude()))
        	throw new RuntimeException("Latitude inválida!");   
    
        if(!numertoValidator.validarCoordenadas(loja.getEndereco().getLongitude()))
    		throw new RuntimeException("Longitude inválido!");          
                              
}      	

	public String remover() throws Exception{		
		
		lojaDAO.excluir(loja);
		return "EXCLUIR";
	}
	
public void populaEndereco() throws Exception{
		
		if(this.loja.getEndereco().getCEP()!=null || !this.loja.getEndereco().getCEP().isEmpty()){
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + this.loja.getEndereco().getCEP() + "&formato=xml");

			SAXReader reader = new SAXReader();
			
	        Document document = reader.read(url);
	        Element root = document.getRootElement();
	        
	        String tipoLogradouro ="";
	        String cidade ="";
	        String uf ="";
	        
	        for ( Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
	        	Element element = (Element) i.next();                
	                
	            if (element.getQualifiedName().equals("bairro"))
	            	this.loja.getEndereco().setBairro(element.getText());                              
	            
	            if (element.getQualifiedName().equals("tipo_logradouro"))
	            	tipoLogradouro = element.getText() + " ";
	            
	            if (element.getQualifiedName().equals("logradouro"))
	            	this.loja.getEndereco().setLogradouro(tipoLogradouro + element.getText());
	            
	            if (element.getQualifiedName().equals("cidade"))
	            	cidade = element.getText();
	            
	            if (element.getQualifiedName().equals("uf"))
	            	uf = element.getText();
	        
	        }	        
	            Cidade cidadeConsulta = cidadeDAO.buscarPorCidadeEstado(cidade, uf);        
	           
	            if(cidadeConsulta != null){
	            	this.unidadeFederacao = cidadeConsulta.getUnidadeFederacao();
		            this.loja.getEndereco().setCidade(cidadeConsulta);
	            }else{
	            	this.unidadeFederacao = null;
		            this.loja.getEndereco().setCidade(null);
	            }
	         
		}
	}
	
public void buscarCoordenadas() throws Exception{
		
		this.loja.getEndereco().setLatitude(null);
		this.loja.getEndereco().setLongitude(null);
		
		String status = "";         
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

		SAXReader reader = new SAXReader();		
        Document document = reader.read(url);
                       
        status = document.selectSingleNode("//GeocodeResponse/status").getText();
        
               
        if(status.equals("OK")){
        	loja.getEndereco().setLatitude(Double.valueOf(document.selectSingleNode("//GeocodeResponse/result/geometry/location/lat").getText()));				
        	loja.getEndereco().setLongitude(Double.valueOf(document.selectSingleNode("//GeocodeResponse/result/geometry/location/lng").getText()));
        }else if(status.equals("ZERO_RESULTS") || status.isEmpty() || status==null){
        	facesMessages.add("Nenhuma coordenada encontrada no endereço informado!");
        }
		
		
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
		return lojaDAO.buscarTodos(usuarioLogado);
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
