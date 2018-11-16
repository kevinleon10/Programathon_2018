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
public class ChildrenTableController implements Serializable {

    private List<Children> children;
    private String status;

    @Autowired
    ResponsibleService responsibleService;

    @Autowired
    ChildrenService childrenService;

    @Autowired
    DailyStatusService dailyStatusService;

    private final HttpSession httpSession;

    public ChildrenTableController() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    public void init() {
        String userSsn = SecurityUtils.getLoggedInUser().getSsn();
        Responsible responsible = responsibleService.getResponsible(userSsn);
        children = childrenService.getChildrenOfResponsible(responsible);
    }

    public void queryChild(Children child) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "READ");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", child);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children-information.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryStatuses(Children child) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "READ");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", child);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/status/children-statuses.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAntecedent(Children child) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "ADD");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", child);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/antecedent/new-antecedent.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStatus(Children child) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "ADD");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", child);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/status/new-status.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStatus(Children children) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Calendar.HOUR-7, Calendar.MINUTE+30, Calendar.SECOND);
        DailyPhysicalStatus dailyPhysicalStatus = new DailyPhysicalStatus(children, calendar.getTime(), status);
        dailyStatusService.createDailyStatus(dailyPhysicalStatus);
    }

    public void doReturn(ActionEvent actionEvent) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/responsible/responsible-dashboard.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doReturnChildrenStatus(ActionEvent actionEvent){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children-status.html");
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
