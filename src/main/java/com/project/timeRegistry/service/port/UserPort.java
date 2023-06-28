package com.project.timeRegistry.service.port;

import com.project.timeRegistry.model.domain.User;

/**
 * <p>
 *     Contract that API must implements to query, create, update or delete a user.
 *     <ol>
 *         <li>getAll() - method to get a list of users.</li>
 *         <li>getById() - method to get a user by id.</li>
 *         <li>create() - method to create a user.</li>
 *         <li>update() - method to update a user registry.</li>
 *         <li>delete() - method to delete a user registry.</li>
 *     </ol>
 * </p>
 */
public interface UserPort {

    /**
     * Get a User by its identifier
     * @param id    Identifier of User.
     * @return {@link User}
     */
    User getById(Long id);
}
