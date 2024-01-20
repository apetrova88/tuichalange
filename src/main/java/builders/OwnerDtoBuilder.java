package builders;

import models.OwnerDetailsDto;
import models.OwnerDto;
import org.apache.commons.lang3.RandomStringUtils;

public class OwnerDtoBuilder {

    public static OwnerDto createDefaultOwnerDto() {
        return OwnerDto.builder()
                .firstName("FN".concat(RandomStringUtils.randomAlphabetic(5)))
                .lastName("LN".concat(RandomStringUtils.randomAlphabetic(5)))
                .address("Address: ".concat(RandomStringUtils.randomAlphanumeric(5)))
                .city("City: ".concat(RandomStringUtils.randomAlphanumeric(5)))
                .telephone(RandomStringUtils.randomNumeric(8))
                .build();
    }

    public static OwnerDto createOwnerDtoFromDetails(OwnerDetailsDto ownerDetailsDto) {
        return OwnerDto.builder()
                .firstName(ownerDetailsDto.getFirstName())
                .lastName(ownerDetailsDto.getLastName())
                .address(ownerDetailsDto.getAddress())
                .city(ownerDetailsDto.getCity())
                .telephone(ownerDetailsDto.getTelephone())
                .build();
    }
}
