package codeSpitters.programathon_2018.service.childrenService.impl;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.MedicalCase;
import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.model.Symptom;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service("childrenService")
public class ChildrenServiceImpl extends AbstractService<Children> implements ChildrenService {

    public ChildrenServiceImpl() {
        super(Children.class);
    }

    public List<Children> getChildrenOfResponsible(Responsible responsible) {
        Query q = this.getCurrentSession().createQuery("from Children where responsible = :responsible");
        q.setParameter("responsible", responsible);
        return q.getResultList();
    }

    public Children getChild(String childSsn) {
        Query q = this.getCurrentSession().createQuery("from Children where ssn = :childSsn");
        q.setParameter("childSsn", childSsn);
        return (Children) q.uniqueResult();
    }

    public void createChild(Responsible responsible, String ssn, String name, int age, String relation, String ethnicGroup, String gender) {
        Children child = new Children();
        child.setResponsible(responsible);
        child.setSsn(ssn);
        child.setFullName(name);
        child.setAge(age);
        child.setEthnicGroup(ethnicGroup);
        child.setGender(gender);
        child.setResponsibleRelation(relation);
        this.create(child);
    }

    public List<Children> getAllChildren() {
        Query q = this.getCurrentSession().createQuery("from Children");
        return q.getResultList();
    }

    public List<MedicalCase> getMedicalCases(Children children) {
        String childSsn = children.getSsn();
        Query q = this.getCurrentSession().createQuery("from MedicalCase where children.ssn = :childSsn");
        q.setParameter("childSsn", childSsn);
        return (List<MedicalCase>) q.list();
    }

    public List<Symptom> getSymptoms(Children children) {
        Query q = this.getCurrentSession().createQuery("SELECT S FROM MedicalCase MC JOIN Symptom S ON MC.id = S.medicalCase.id where MC.children.ssn = :childSsn");
        q.setParameter("childSsn", children.getSsn());
        return (List<Symptom>) q.list();
    }

    public Symptom getSymptom(Date date) {
        Query q = this.getCurrentSession().createQuery("from Symptom where date = :date");
        q.setParameter("date", date);
        return (Symptom) q.uniqueResult();
    }

    public MedicalCase getMedicalCasesById(int id) {
        Query q = this.getCurrentSession().createQuery("from MedicalCase where id = :id");
        q.setParameter("id", id);
        return (MedicalCase) q.uniqueResult();
    }


}