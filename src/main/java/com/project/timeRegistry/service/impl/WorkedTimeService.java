package com.project.timeRegistry.service.impl;


import com.project.timeRegistry.model.domain.WorkedTime;
import com.project.timeRegistry.repository.WorkedTimeRepository;
import com.project.timeRegistry.service.port.WorkedTimePort;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkedTimeService implements WorkedTimePort {

    private WorkedTimeRepository workedTimePortRepository;

    @Override
    public Page<WorkedTime> getAll(Pageable pageable) {
        return workedTimePortRepository.findAll(pageable);
    }

    @Override
    public WorkedTime getById(Long id) {
        return workedTimePortRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "workedTime id"));
    }

    @Override
    public WorkedTime create(WorkedTime workedTime) {
        return workedTimePortRepository.save(workedTime);
    }

    @Override
    public WorkedTime update(Long id, WorkedTime workedTime) {
        WorkedTime workedTimeFound = getById(id);

        //check if time is valid (doenst conflict with anothers)
        checkIfWorkedTimeisValid(workedTimeFound, workedTime);

        WorkedTime updatedWorkedTime = updateWorkedTime(workedTimeFound, workedTime);

        return workedTimePortRepository.save(updatedWorkedTime);
    }

    @Override
    public void delete(Long id) {
        workedTimePortRepository.deleteById(id);
    }


    private WorkedTime checkIfWorkedTimeisValid(WorkedTime workedTimeFound, WorkedTime workedTime) {
        return null;
    }


    private WorkedTime updateWorkedTime(WorkedTime workedTimeFound, WorkedTime workedTime) {
        workedTimeFound.setStart(workedTime.getStart());
        workedTimeFound.setFinish(workedTime.getFinish());
        workedTimeFound.setDuration(workedTimeFound.calcDuration(workedTime.getStart(), workedTime.getFinish()));
        workedTimeFound.setActivityDescription(workedTime.getActivityDescription());
        return workedTimeFound;
    }
}

