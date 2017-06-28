/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pe.edu.upeu.model.Persona;
import pe.edu.upeu.service.PersonaServis;

/**
 *
 * @author David
 */
@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	PersonaServis service;	
	@Autowired
	MessageSource messageSource;    
    
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView principalPagina(ModelMap model) {                      
            return new ModelAndView("login");
	}    
	@RequestMapping(value = {"/validar" }, method = RequestMethod.POST)
	public ModelAndView principalPaginaValidar(ModelMap model, HttpServletRequest r) {
            Persona lista=null;
            String usuario=r.getParameter("usuario");
            String clave=r.getParameter("clave");
            lista=service.listarPorNombre(usuario, clave);
            Map<String, Object> modelo=new HashMap<String, Object> ();
            modelo.put("Persona", lista);       
            if(lista!=null){
            return new ModelAndView("main", modelo);
            }else{
            return new ModelAndView("login", modelo);
            }                       
	}    
}
