package codeSpitters.programathon_2018.service.antecedent;

import codeSpitters.programathon_2018.model.Antecedent;

import java.util.List;

public interface AntecedentService {

    void createAntecedent(Antecedent antecedent);

    Antecedent getAntecedent(String antecedentId);

    void updateAntecedent(Antecedent antecedent);

    List getAntecedents(String childSsn);

}
