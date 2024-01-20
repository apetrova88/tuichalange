package constants;

import tuichalange.ConfigProperties;

import static tuichalange.ConfigProperties.BASE_URL_PROP;

public class ApiConstants {

    public static final String API_BASE_URL = ConfigProperties.getProperty(BASE_URL_PROP).concat("/petclinic/api");

    //pettypes-controller
    public static final String PETTYPES = "/pettypes";
    public static final String PETTYPES_PETTYPE_ID = "/pettypes/{petTypeId}";

    //owners-controller
    public static final String OWNERS = "/owners";
    public static final String OWNERS_OWNER_ID = "/owners/{ownerId}";

    //pets-controller
    public static final String PETS = "/pets";
    public static final String PETS_PET_ID = "/pets/{petId}";
    public static final String OWNERS_OWNER_ID_PETS_PET_ID = "/owners/{ownerId}/pets/{petId}";
    public static final String OWNERS_OWNER_ID_PETS = "/owners/{ownerId}/pets";


}
