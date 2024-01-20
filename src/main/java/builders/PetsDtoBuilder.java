package builders;

import models.PetsDto;
import org.apache.commons.lang3.RandomStringUtils;

public class PetsDtoBuilder {

    public static PetsDto createDefaultPetsDto() {
        return PetsDto.builder()
                .name(RandomStringUtils.randomAlphabetic(5))
                .birthDate("2022-09-09")
                .type(PetTypeDtoBuilder.createPetTypeDto(1, "cat"))
                .build();
    }
}
