package at.allergico.allergico.database.POJO;

/**
 * Created by Markus on 25.05.2015.
 */
public class UserHasAllergenPOJO {
    private int UserID;
    private int AllergenID;



    public UserHasAllergenPOJO(int userID, int allergenID) {
        UserID = userID;
        AllergenID = allergenID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAllergenID() {
        return AllergenID;
    }

    public void setAllergenID(int allergenID) {
        AllergenID = allergenID;
    }
}
