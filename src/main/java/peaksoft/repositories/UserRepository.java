package peaksoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.responses.ChefResponse;
import peaksoft.dto.responses.StatementResponse;
import peaksoft.dto.responses.WaiterResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select new peaksoft.dto.responses.ChefResponse(u.id," +
           " u.firstName, u.lastName, u.dateOfBrith, u.email," +
           " u.phoneNumber, u.experience) from User u where u.restaurant.id=?1 and u.role=?2")
    List<ChefResponse> findAllChefs(Long restId, Role role);

    @Query("select new peaksoft.dto.responses.ChefResponse(u.id," +
           " u.firstName, u.lastName, u.dateOfBrith, u.email," +
           " u.phoneNumber, u.experience) from User u where u.id=?1 and u.role = ?2")
    Optional<ChefResponse> findByChefId(Long chefId, Role role);


    @Query("select new peaksoft.dto.responses.WaiterResponse(u.id, u.firstName," +
           "u.lastName, u.dateOfBrith, u.email, u.phoneNumber, u.experience)" +
           " from User u where u.restaurant.id=?1 and u.role=?2")
    List<WaiterResponse> findAllWaiters(Long restId, Role role);

    @Query("select new peaksoft.dto.responses.WaiterResponse(u.id," +
           " u.firstName, u.lastName, u.dateOfBrith, u.email," +
           " u.phoneNumber, u.experience) from User u where u.id=?1 and u.role = ?2")
    Optional<WaiterResponse> findByWaiterId(Long waiterId, Role role);


    @Query("select new peaksoft.dto.responses.StatementResponse(u.id, u.firstName, u.lastName," +
           "u.dateOfBrith, u.email, u.phoneNumber,u.role, u.experience) from User u where u.restaurant.id=null")
    List<StatementResponse> findAllNewEmployees();




    Page<ChefResponse> findAllByRole(Role role, Pageable pageable);
    List<ChefResponse> findAllByRole(Role role,Sort sort);

    Page<WaiterResponse> getAllByRole(Role role, Pageable pageable);
    List<WaiterResponse> getAllByRole(Role role,Sort sort);
}