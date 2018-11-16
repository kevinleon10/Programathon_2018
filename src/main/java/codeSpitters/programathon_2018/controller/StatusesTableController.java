package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.DailyPhysicalStatus;
import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.dailyStatus.DailyStatusService;
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
import java.util.Calendar;
import java.util.List;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class StatusesTableController implements Serializable {

    private List<DailyPhysicalStatus> statuses;
    private Children children;
    private String childrenName;

    @Autowired
    DailyStatusService dailyStatusService;

    private final HttpSession session;

    public StatusesTableController() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void init() {
        children = (Children) session.getAttribute("CHILD_TRACKING_MOD_OJB");
        if (children == null) {
            doReturn(null);
        }
        else {
            childrenName = children.getFullName();
            statuses = dailyStatusService.getDailyStatuses(children.getSsn());
        }
    }

    public void doReturn(ActionEvent actionEvent) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children-statuses.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public List<DailyPhysicalStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<DailyPhysicalStatus> statuses) {
        this.statuses = statuses;
    }
}
