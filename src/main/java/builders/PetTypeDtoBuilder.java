package builders;

import models.PetTypeDto;

public class PetTypeDtoBuilder {

    public static PetTypeDto createPetTypeDto(Integer id, String name) {
        return PetTypeDto.builder()
                .id(id)
                .name(name)
                .build();
    }

}
