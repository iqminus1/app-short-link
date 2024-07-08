package uz.pdp.appshortlink.enums;

import java.util.List;

public enum PermissionEnum {
    //FOR USER
    CREATE_URL,
    VIEW_OWN_URL,
    EDIT_OWN_URL,
    DELETE_OWN_URL,



    //FOR ADMIN
    VIEW_ANY_URL,
    EDIT_ANY_URL,
    DELETE_ANY_URL,


    //ROLE
    VIEW_ROLE,
    CREATE_ROLE,
    EDIT_ROLE,
    DELETE_ROLE,

    //USER
    BLOCK_USER,
    VIEW_USER,
    ;

    public static List<PermissionEnum> permissionUser() {
        return List.of(
                PermissionEnum.CREATE_URL,
                PermissionEnum.VIEW_OWN_URL,
                PermissionEnum.EDIT_OWN_URL,
                PermissionEnum.DELETE_OWN_URL
        );
    }
}
