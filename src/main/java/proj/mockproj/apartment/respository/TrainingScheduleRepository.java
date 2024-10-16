package proj.mockproj.apartment.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import proj.mockproj.apartment.model.TrainingSchedule;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Long> {
	
}

