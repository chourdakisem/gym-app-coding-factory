package gr.aueb.cf.gymapp.mappers;

import gr.aueb.cf.gymapp.dto.trainer.TrainerReadOnlyDTO;
import gr.aueb.cf.gymapp.model.Trainer;

public class TrainerMapper {

    private TrainerMapper() {}

    public static TrainerReadOnlyDTO mapToDto(Trainer trainer) {
        TrainerReadOnlyDTO dto = new TrainerReadOnlyDTO();
        dto.setId(trainer.getId());
        dto.setDuration(trainer.getDuration());
        dto.setFee(trainer.getFee());
        dto.setLocation(trainer.getLocation());
        dto.setUser(trainer.getUser());
        dto.setSessions(trainer.getSessions());
        dto.setStartingTime(trainer.getStartingTime());
        dto.setEndingTime(trainer.getEndingTime());
        return dto;
    }
}
