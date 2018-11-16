package codeSpitters.programathon_2018.service.dailyStatus.impl;

import codeSpitters.programathon_2018.model.*;
import codeSpitters.programathon_2018.service.childrenService.ChildrenService;
import codeSpitters.programathon_2018.service.dailyStatus.DailyStatusService;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("dailyStatusServiceImpl")
public class DailyStatusServiceImpl extends AbstractService<DailyPhysicalStatus> implements DailyStatusService {

    public DailyStatusServiceImpl() {
        super(DailyPhysicalStatus.class);
    }

    public void createDailyStatus(DailyPhysicalStatus dailyPhysicalStatus) {
        super.create(dailyPhysicalStatus);
    }

    public DailyPhysicalStatus getDailyStatus(String id) {
        Query q = this.getCurrentSession().createQuery("from DailyPhysicalStatus where id = :id");
        q.setParameter("id", id);
        return (DailyPhysicalStatus) q.uniqueResult();
    }

    public void updateDailyStatus(DailyPhysicalStatus dailyPhysicalStatus) {
        super.update(dailyPhysicalStatus);
    }

    public List getDailyStatuses(String id) {
        Query q = this.getCurrentSession().createQuery("from DailyPhysicalStatus where children.ssn = :id");
        q.setParameter("id", id);
        return (List) q.list();
    }
}