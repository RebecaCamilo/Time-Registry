package com.project.timeRegistry.service.port;


import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *     Contract that API must implements to query, create, update or delete a user.
 *     <ol>
 *         <li>getAll() - method to get a page of users.</li>
 *         <li>getById() - method to get a user by id.</li>
 *         <li>create() - method to create a user.</li>
 *         <li>update() - method to update a user registry.</li>
 *         <li>deactivate() - method to deactivate a user registry.</li>
 *         <li>activate() - method to activate a user registry.</li>
 *     </ol>
 * </p>
 */
public interface UserPort {

    /**
     * Get a Page of Users by a pageable
     * @param pageable    Input parameters of a paging request.
     * @return {@link Page} of {@link User}
     */
    Page<User> getAll(Pageable pageable);

    /**
     * Get a User by its identifier
     * @param id    Identifier of User.
     * @return {@link User}
     */
    User getById(Long id);

    /**
     * Registry a new User from a UserRequest
     * @param user    The User data.
     * @return {@link User}
     */
    User create(User user);

    /**
     * Update a User registry by its identifier and a UserRequest
     * @param id            Identifier of User.
     * @param user          The User data.
     * @return {@link User}
     */
    User update(Long id, User user);

    /**
     * Set the of User to inactivate by its identifier
     * @param id    Identifier of User.
     * @return {@link User}
     */
    User deactivate(Long id);

    /**
     * Set the of User to activate by its identifier
     * @param id    Identifier of User.
     * @return {@link User}
     */
    User activate(Long id);

}
