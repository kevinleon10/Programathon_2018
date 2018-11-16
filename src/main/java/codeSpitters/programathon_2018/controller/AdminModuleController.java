/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.service.module.Module;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible dashboard controller
 *
 * @author JorgeRemon
 */
@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class AdminModuleController implements Serializable {

    public AdminModuleController() { }

    public List<Module> getModules() {
        ArrayList<Module> modulesList = new ArrayList<>();
        Module module = new Module("Consulta de perfiles de niños", "color1", "Este módulo permite al administrador consultar la información de los perfiles de los niños ingresados en el sistema.", "Ir al módulo", "/Programathon_2018/administrator/children.html");
        modulesList.add(module);
        return modulesList;
    }

}