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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class PhysicalStatusController {

    @Autowired
    ResponsibleService responsibleService;

    @Autowired
    ChildrenService childrenService;

    @Autowired
    DailyStatusService dailyStatusService;

    private final HttpSession httpSession;

    private Children child;

    private Integer status;

    private String childName;

    public PhysicalStatusController() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.status = new Integer(0);
    }

    @PostConstruct
    public void init() {
        if (httpSession.getAttribute("CHILD_TRACKING_MOD_OJB") == null) {
            this.returnToDashboard(null);
        } else {
            this.setChild((Children) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB"));
            this.childName = child.getFullName();
            String userSsn = SecurityUtils.getLoggedInUser().getSsn();
            Responsible responsible = responsibleService.getResponsible(userSsn);
        }
    }

    public void redirectToChildren() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void saveRating(ActionEvent actionEvent) {
        if (this.status == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor seleccione un valor para el estado físico", ""));
            return;
        }

        this.createStatus(this.child);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children-status.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStatus(Children children) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), Calendar.HOUR-7, Calendar.MINUTE+30, Calendar.SECOND);
        DailyPhysicalStatus dailyPhysicalStatus = new DailyPhysicalStatus(children, calendar.getTime(), "" + status);
        dailyStatusService.createDailyStatus(dailyPhysicalStatus);
    }

    public void returnToDashboard(ActionEvent actionEvent) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/children/children-status.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Children getChild() {
        return child;
    }

    public void setChild(Children child) {
        this.child = child;
    }
}
