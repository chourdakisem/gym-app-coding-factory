package gr.aueb.cf.gymapp.controllers;

import gr.aueb.cf.gymapp.dto.trainer.TrainerReadOnlyDTO;
import gr.aueb.cf.gymapp.mappers.TrainerMapper;
import gr.aueb.cf.gymapp.model.Trainer;
import gr.aueb.cf.gymapp.services.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final ITrainerService trainerService;

    @Autowired
    public TrainerController(ITrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/trainer/{trainerLastname}")
    public ResponseEntity<List<TrainerReadOnlyDTO>> getTrainerByLastname(@PathVariable("trainerLastname") String lastname) {
        List<Trainer> trainers = trainerService.findTrainerByLastname(lastname);
        List<TrainerReadOnlyDTO> readOnlyDTOS = trainers.stream()
                .map(trainer -> TrainerMapper.mapToDto(trainer))
                .collect(Collectors.toList());
        return ResponseEntity.ok(readOnlyDTOS);
    }

    @GetMapping("/{trainerId}")
    public ResponseEntity<TrainerReadOnlyDTO> getTrainerById(@PathVariable("trainerId") Long id) {
        Trainer trainer = trainerService.findTrainerById(id);
        TrainerReadOnlyDTO readOnlyDTO = TrainerMapper.mapToDto(trainer);
        return ResponseEntity.ok(readOnlyDTO);
    }
}
