package codeSpitters.programathon_2018.service.childrenService;

import codeSpitters.programathon_2018.model.Children;
import codeSpitters.programathon_2018.model.MedicalCase;
import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.model.Symptom;
import codeSpitters.programathon_2018.support.service.Service;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public interface ChildrenService extends Service<Children> {

    public List<Children> getChildrenOfResponsible(Responsible responsible);

    public Children getChild(String childSsn);

    public void createChild(Responsible responsible, String ssn, String name, int age, String relation, String ethnicGroup, String gender);

    public List<Children> getAllChildren();

    public List<MedicalCase> getMedicalCases(Children children);

    public List<Symptom> getSymptoms(Children children);

    public Symptom getSymptom(Date date);

    public MedicalCase getMedicalCasesById(int id);

}
