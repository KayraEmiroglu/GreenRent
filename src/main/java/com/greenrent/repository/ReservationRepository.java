package com.greenrent.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenrent.domain.Reservation;
import com.greenrent.domain.User;
import com.greenrent.domain.enums.ReservationStatus;
import com.greenrent.dto.ReservationDTO;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	
	
//	@Query("SELECT r FROM Reservation r "
//			+ " JOIN FETCH Car cd on r.carId=cd.id WHERE "
//			+ " cd.id=:carId and (r.status not in :status) and :pickUpTime BETWEEN r.pickUpTime and r. dropOffTime "
//			+ " or "
//			+ " cd.id=:carId and (r.status not in :status) and :dropOffTime BETWEEN r.pickUpTime and r. dropOffTime "
//			+ " cd.id=:carId and (r.status not in :status) and (r.pickUpTime BETWEEN :pickUpTime and :dropOffTime)")
	@Query("SELECT r FROM Reservation r "
			+ "JOIN FETCH Car c on r.carId=c.id WHERE "
			+ "c.id=:carId and (r.status not in :status) and :pickUpTime BETWEEN r.pickUpTime and r.dropOffTime "
			+ "or "
			+ "c.id=:carId and (r.status not in :status) and :dropOffTime BETWEEN r.pickUpTime and r.dropOffTime "
			+ "or "
			+ "c.id=:carId and (r.status not in :status) and (r.pickUpTime BETWEEN :pickUpTime and :dropOffTime)")
	List<Reservation> checkCarStatus(@Param("carId") Long carId, 
			@Param("pickUpTime") LocalDateTime pickUpTime, 
			@Param("dropOffTime") LocalDateTime dropOffTime, 
			@Param("status") ReservationStatus[] status);
	
	
	Optional<ReservationDTO> findByIdAndUserId(Long id,User user);
	
	List<ReservationDTO> findAllBy();
	
	Optional<ReservationDTO> findDTOById(Long id);
	
	List<ReservationDTO> findAllByUserId(User userId);
	
	
}
