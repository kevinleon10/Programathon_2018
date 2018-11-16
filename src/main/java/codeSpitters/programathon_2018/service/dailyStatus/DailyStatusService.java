package codeSpitters.programathon_2018.service.dailyStatus;

import codeSpitters.programathon_2018.model.Antecedent;
import codeSpitters.programathon_2018.model.DailyPhysicalStatus;

import java.util.List;

public interface DailyStatusService {

    void createDailyStatus(DailyPhysicalStatus dailyPhysicalStatus);

    DailyPhysicalStatus getDailyStatus(String id);

    void updateDailyStatus(DailyPhysicalStatus dailyPhysicalStatus);

    List getDailyStatuses(String id);

}
