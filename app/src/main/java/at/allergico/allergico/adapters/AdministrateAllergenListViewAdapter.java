package at.allergico.allergico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView allergenName = (TextView) convertView.findViewById(R.id.allergenName);
        TextView allergenDescription = (TextView) convertView.findViewById(R.id.allergenDescription);
        ImageView allergenImage = (ImageView) convertView.findViewById(R.id.allergenImage);

        char allergenAbbreviation = this._data.get(position).getAbbreviation();
        int iconID = -1;
        allergenName.setText(this._data.get(position).getAbbreviation());
        allergenDescription.setText(this._data.get(position).getDescription());

        switch (allergenAbbreviation) {
            case 'A':
                break;
            case 'B':
                break;
            case 'C':
                break;
            case 'D':
                break;
            case 'E':
                break;
            case 'F':
                break;
            case 'G':
                break;
            case 'H':
                break;
            case 'L':
                break;
            case 'M':
                break;
            case 'N':
                break;
            case 'O':
                break;
            case 'P':
                break;
            case 'R':
                break;
            default:
                break;
        }
        allergenImage.setImageResource(iconID);

        return rowView;
    }
}
