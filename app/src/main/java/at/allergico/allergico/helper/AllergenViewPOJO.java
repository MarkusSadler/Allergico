package at.allergico.allergico.helper;

import at.allergico.allergico.database.POJO.AllergenPOJO;

/**
 * Created by Michael on 04/06/2015.
 */
public class AllergenViewPOJO {
    private AllergenPOJO _allergen;
    private boolean _selected;

    public AllergenViewPOJO(AllergenPOJO _allergen, boolean _selected) {
        this._allergen = _allergen;
        this._selected = _selected;
    }

    public AllergenPOJO get_allergen() {
        return _allergen;
    }

    public void set_allergen(AllergenPOJO _allergen) {
        this._allergen = _allergen;
    }

    public boolean is_selected() {
        return _selected;
    }

    public void set_selected(boolean _selected) {
        this._selected = _selected;
    }
}
