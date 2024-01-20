package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
}
