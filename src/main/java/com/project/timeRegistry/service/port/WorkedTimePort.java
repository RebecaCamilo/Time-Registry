package com.project.timeRegistry.service.port;


import com.project.timeRegistry.model.domain.WorkedTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *     Contract that API must implements to query, create, update or delete a WorkedTime.
 *     <ol>
 *         <li>getAll() - method to get a page of workedTime.</li>
 *         <li>getById() - method to get a workedTime by id.</li>
 *         <li>create() - method to create a workedTime.</li>
 *         <li>update() - method to update a workedTime registry.</li>
 *         <li>delete() - method to delete a workedTime registry.</li>
 *     </ol>
 * </p>
 */
public interface WorkedTimePort {

    /**
     * Get a Page of WorkedTimes by a pageable
     * @param pageable    Input parameters of a paging request.
     * @return {@link Page} of {@link WorkedTime}
     */
    Page<WorkedTime> getAll(Pageable pageable);

    /**
     * Get a WorkedTime by its identifier
     * @param id    Identifier of WorkedTime.
     * @return {@link WorkedTime}
     */
    WorkedTime getById(Long id);

    /**
     * Registry a new WorkedTime from a WorkedTimeRequest
     * @param workedTime    The WorkedTime data.
     * @return {@link WorkedTime}
     */
    WorkedTime create(WorkedTime workedTime);

    /**
     * Update a WorkedTime registry by its identifier and a WorkedTimeRequest
     * @param id            Identifier of WorkedTime.
     * @param workedTime          The WorkedTime data.
     * @return {@link WorkedTime}
     */
    WorkedTime update(Long id, WorkedTime workedTime);

    /**
     * Delete a WorkedTime by its identifier
     * @param id    Identifier of WorkedTime.
     */
    void delete(Long id);

}
