package com.project.timeRegistry.service.port;


import com.project.timeRegistry.model.domain.DailyReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *     Contract that API must implements to query, create, update or delete a dailyReport.
 *     <ol>
 *         <li>getAll() - method to get a page of dailyReports.</li>
 *         <li>getById() - method to get a dailyReport by id.</li>
 *         <li>create() - method to create a dailyReport.</li>
 *         <li>update() - method to update a dailyReport registry.</li>
 *         <li>deactivate() - method to deactivate a dailyReport registry.</li>
 *         <li>activate() - method to activate a dailyReport registry.</li>
 *     </ol>
 * </p>
 */
public interface DailyReportPort {

    /**
     * Get a Page of DailyReports by a pageable
     * @param pageable    Input parameters of a paging request.
     * @return {@link Page} of {@link DailyReport}
     */
    Page<DailyReport> getAll(Pageable pageable);

    /**
     * Get a DailyReport by its identifier
     * @param id    Identifier of DailyReport.
     * @return {@link DailyReport}
     */
    DailyReport getById(Long id);

    /**
     * Registry a new DailyReport from a DailyReportRequest
     * @param dailyReport    The DailyReport data.
     * @return {@link DailyReport}
     */
    DailyReport create(DailyReport dailyReport);

    /**
     * Update a DailyReport registry by its identifier and a DailyReportRequest
     * @param id            Identifier of DailyReport.
     * @param dailyReport          The DailyReport data.
     * @return {@link DailyReport}
     */
    DailyReport update(Long id, DailyReport dailyReport);

    /**
     * Set the of DailyReport to inactivate by its identifier
     * @param id    Identifier of DailyReport.
     * @return {@link DailyReport}
     */
    DailyReport deactivate(Long id);

    /**
     * Set the of DailyReport to activate by its identifier
     * @param id    Identifier of DailyReport.
     * @return {@link DailyReport}
     */
    DailyReport activate(Long id);

}
