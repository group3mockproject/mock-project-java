package proj.mockproj.apartment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.mockproj.apartment.model.TrainingSchedule;
import proj.mockproj.apartment.respository.TrainingScheduleRepository;

@Service
public class TrainingScheduleService {

    @Autowired
    private TrainingScheduleRepository trainingScheduleRepository;

    // Lấy tất cả lịch đào tạo
    public List<TrainingSchedule> getAllTrainingSchedules() {
        return trainingScheduleRepository.findAll();
    }

    // Lấy một lịch đào tạo theo ID
    public TrainingSchedule getTrainingScheduleById(Long id) {
        return trainingScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training schedule not found: " + id));
    }

    // Tạo mới lịch đào tạo
    public TrainingSchedule createTrainingSchedule(TrainingSchedule trainingSchedule) {
        return trainingScheduleRepository.save(trainingSchedule);
    }

    // Cập nhật lịch đào tạo
    public TrainingSchedule updateTrainingSchedule(Long id, TrainingSchedule trainingSchedule) {
        TrainingSchedule existingSchedule = getTrainingScheduleById(id);
        existingSchedule.setTrainingDate(trainingSchedule.getTrainingDate());
        existingSchedule.setStartTime(trainingSchedule.getStartTime());
        existingSchedule.setEndTime(trainingSchedule.getEndTime());
        existingSchedule.setTrainingTopic(trainingSchedule.getTrainingTopic());
        existingSchedule.setTrainer(trainingSchedule.getTrainer());
        existingSchedule.setFormat(trainingSchedule.getFormat());
        existingSchedule.setLocation(trainingSchedule.getLocation());
        existingSchedule.setStatus(trainingSchedule.getStatus());
        return trainingScheduleRepository.save(existingSchedule);
    }

    // Xóa lịch đào tạo
    public void deleteTrainingSchedule(Long id) {
        trainingScheduleRepository.deleteById(id);
    }
}
