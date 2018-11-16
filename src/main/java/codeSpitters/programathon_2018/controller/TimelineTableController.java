package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.user.responsible.ResponsibleService;
import codeSpitters.programathon_2018.support.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class TimelineTableController implements Serializable {

    private List<Children> children;

    @Autowired
    ChildrenService childrenService;

    private final HttpSession httpSession;

    public TimelineTableController() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void init() {
        children = childrenService.getAllChildren();
    }

    public void redirectToTimeline(Children child) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "READ");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", child);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/timeline/timeline.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doReturn() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/administrator/admin-dashboard.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
}
