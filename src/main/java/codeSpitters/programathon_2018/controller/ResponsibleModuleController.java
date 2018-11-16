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
public class ResponsibleModuleController implements Serializable {

    public ResponsibleModuleController() { }

    public List<Module> getModules() {
        ArrayList<Module> modulesList = new ArrayList<>();
        Module module = new Module("Agregar niño", "color1", "Este módulo permite agregar un máximo de cinco niños para darles seguimiento día a día, sobre los que usted es el reponsable.", "Ir al módulo", "/Programathon_2018/children/add-children.html");
        modulesList.add(module);
        module = new Module("Seguimiento a niños", "color2", "Este módulo permite al usuario, dar seguimiento a la información del niño, permitiendole consultar, editar y eliminar información personal y antecedentes.", "Ir al módulo", "/Programathon_2018/children/children.html");
        modulesList.add(module);
        module = new Module("Calificador de estados", "color2", "Este módulo permite al usuario, dar seguimiento a la información del niño y agregar una escala de dolor diaria del niño.", "Ir al módulo", "/Programathon_2018/children/children-status.html");
        modulesList.add(module);
        module = new Module("Progresión de estados", "color1", "Este módulo permite al usuario, dar seguimiento a la información del niño agregada de manera diaria.", "Ir al módulo", "/Programathon_2018/timeline/timelineTable.html");
        modulesList.add(module);
        return modulesList;
    }

}