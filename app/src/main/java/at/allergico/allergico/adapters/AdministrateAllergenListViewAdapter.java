package at.allergico.allergico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.POJO.AllergenPOJO;

/**
 * Created by Michael on 31/05/2015.
 */
public class AdministrateAllergenListViewAdapter extends ArrayAdapter<AllergenPOJO> {
    private final Context _context;
    private final ArrayList<AllergenPOJO> _data;


    public AdministrateAllergenListViewAdapter(Context context, List<AllergenPOJO> values) {
        super(context, R.layout.activity_administrate_allergen_listrow, values);
        this._context = context;

        this._data = new ArrayList<AllergenPOJO>();

        for(int i = 0; i < values.size(); i++) {
            this._data.add(values.get(i));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_administrate_allergen_listrow, parent, false);

        TextView allergenDescription = (TextView) rowView.findViewById(R.id.listRowAllergenDescription);
        ImageView allergenImage = (ImageView) rowView.findViewById(R.id.listRowAllergenImage);

        char allergenAbbreviation = this._data.get(position).getAbbreviation();
        int iconID = -1;
        allergenDescription.setText(this._data.get(position).getDescription());

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
        allergenImage.setImageResource(iconID);
        rowView.setSelected(true);

        return rowView;
    }
}
