package br.com.geostore.controller;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import br.com.geostore.dao.TipoUsuarioDAO;
import br.com.geostore.entity.TipoUsuario;

@Name("tipoUsuarioController")
public class TipoUsuarioController {
	
	@In(create=true) private TipoUsuarioDAO tipoUsuarioDAO;
	
	@Factory(value="tiposUsuario", scope=ScopeType.APPLICATION)
	public List<TipoUsuario> populaTiposUsuario() throws Exception{		
		return tipoUsuarioDAO.buscarTodos();
	}
	
	
	
}
