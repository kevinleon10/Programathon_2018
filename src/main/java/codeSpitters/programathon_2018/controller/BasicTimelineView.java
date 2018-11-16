package codeSpitters.programathon_2018.controller;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.Symptom;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
@Scope("request")
@ManagedBean
@RequestScoped
public class BasicTimelineView implements Serializable {

    @Autowired
    ChildrenService childrenService;

    private TimelineModel model;
    private String childrenId;
    private List<Symptom> symptoms;
    private String eventStyle = "box";
    private final HttpSession httpSession;

    public BasicTimelineView() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @PostConstruct
    protected void init() {
        model = new TimelineModel();
        if (httpSession.getAttribute("CHILD_TRACKING_MOD_OJB") != null) {
            this.childrenId = ((Children) httpSession.getAttribute("CHILD_TRACKING_MOD_OJB")).getSsn();
            this.symptoms = this.getSymptoms();
            this.generateEvents();
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/timeline/timelineTable.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void doReturn() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/timeline/timelineTable.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSelect(TimelineSelectEvent e) {
        httpSession.setAttribute("CHILD_TRACKING_MOD_ACT", "READ");
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB", (Date) e.getTimelineEvent().getData());
        httpSession.setAttribute("CHILD_TRACKING_MOD_OJB_CHILD", childrenService.getChild(this.childrenId));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Programathon_2018/timeline/timeEvent.html");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public TimelineModel getModel() {
        return model;
    }

    public List<Symptom> getSymptoms() {
        return childrenService.getSymptoms(childrenService.getChild(this.childrenId));
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public void generateEvents() {
        if (symptoms != null) {
            Iterator<Symptom> it = symptoms.iterator();
            while (it.hasNext()) {
                Symptom symptom = it.next();
                model.add(new TimelineEvent(symptom.getDate(), symptom.getDate()));
            }
        }
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
    }
}