package builders;

import models.OwnerDetailsDto;
import models.OwnerDto;
import models.PetsDetailsDto;

import java.util.List;

public class OwnerDetailsDtoBuilder {

    public static OwnerDetailsDto createOwnerDetailsDto(OwnerDto ownerDto, Integer id, List<PetsDetailsDto> pets) {
        return OwnerDetailsDto.builder()
                .id(id)
                .firstName(ownerDto.getFirstName())
                .lastName(ownerDto.getLastName())
                .address(ownerDto.getAddress())
                .city(ownerDto.getCity())
                .telephone(ownerDto.getTelephone())
                .pets(pets)
                .build();
    }
}
