package builders;

import models.PetsDetailsDto;
import models.PetsDto;
import models.VisitDto;

import java.util.List;

public class PetsDetailsDtoBuilder {

    public static PetsDetailsDto createPetsDetailsDto(PetsDto petsDto, Integer id, Integer ownerId) {
        return PetsDetailsDto.builder()
                .name(petsDto.getName())
                .birthDate(petsDto.getBirthDate())
                .type(petsDto.getType())
                .id(id)
                .ownerId(ownerId)
                .visits(List.of())
                .build();
    }
}
