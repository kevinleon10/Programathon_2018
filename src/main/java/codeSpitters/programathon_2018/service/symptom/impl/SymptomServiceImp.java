package codeSpitters.programathon_2018.service.symptom.impl;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.Symptom;
import codeSpitters.programathon_2018.service.symptom.SymptomService;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service("symptomService")
public class SymptomServiceImp extends AbstractService<Symptom> implements SymptomService {

    public SymptomServiceImp() {
        super(Symptom.class);
    }

    public List<Symptom> getSymptoms(Children children) {
        Query q = this.getCurrentSession().createQuery("SELECT S FROM MedicalCase MC JOIN Symptom S ON MC.id = S.medicalCase.id where MC.children.ssn = :childSsn");
        q.setParameter("childSsn", children.getSsn());
        return (List<Symptom>) q.list();
    }

    @Override
    public boolean hasAddedSymptoms(Date currentDate) {
        Query q = this.getCurrentSession().createQuery("SELECT S FROM Symptoms WHERE S.date = :date");
        q.setParameter("currentDate", currentDate);
        if(q.list().size()>0){
            return true;
        }
        return false;
    }
}