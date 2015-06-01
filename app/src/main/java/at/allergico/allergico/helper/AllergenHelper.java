package at.allergico.allergico.helper;

import at.allergico.allergico.R;

/**
 * Created by Michael on 01/06/2015.
 */
public class AllergenHelper {

    public static int getAllergenImage(char allergenAbbreviation) {
        int iconID = -1;

        switch (allergenAbbreviation) {
            case 'A': iconID = R.mipmap.allergen_a;
                break;
            case 'B': iconID = R.mipmap.allergen_b;
                break;
            case 'C': iconID = R.mipmap.allergen_c;
                break;
            case 'D': iconID = R.mipmap.allergen_d;
                break;
            case 'E': iconID = R.mipmap.allergen_e;
                break;
            case 'F': iconID = R.mipmap.allergen_f;
                break;
            case 'G': iconID = R.mipmap.allergen_g;
                break;
            case 'H': iconID = R.mipmap.allergen_h;
                break;
            case 'L': iconID = R.mipmap.allergen_l;
                break;
            case 'M': iconID = R.mipmap.allergen_m;
                break;
            case 'N': iconID = R.mipmap.allergen_n;
                break;
            case 'O': iconID = R.mipmap.allergen_o;
                break;
            case 'P': iconID = R.mipmap.allergen_p;
                break;
            case 'R': iconID = R.mipmap.allergen_r;
                break;
            default: iconID = R.drawable.launcher_icon;
                break;
        }

        return iconID;
    }
}
