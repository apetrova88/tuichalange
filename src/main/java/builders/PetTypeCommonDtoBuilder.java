package builders;

import models.PetTypeDto;

public class PetTypeCommonDtoBuilder {

    public static PetTypeDto createPetTypeDto(String name) {
        return PetTypeDto.builder()
                .name(name)
                .build();
    }
}
