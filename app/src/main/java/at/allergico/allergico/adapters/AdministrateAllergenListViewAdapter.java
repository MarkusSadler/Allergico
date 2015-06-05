package at.allergico.allergico.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import at.allergico.allergico.R;
import at.allergico.allergico.database.POJO.AllergenPOJO;
import at.allergico.allergico.database.POJO.UserPOJO;
import at.allergico.allergico.helper.AllergenHelper;
import at.allergico.allergico.helper.AllergenViewPOJO;
import at.allergico.allergico.helper.CurrentUser;

/**
 * Created by Michael on 31/05/2015.
 */
public class AdministrateAllergenListViewAdapter extends ArrayAdapter<AllergenPOJO> {
    private final Activity _parent;
    private final ArrayList<AllergenViewPOJO> _data;

    private boolean _showCheckboxes = false;

    static class ViewHolder {
        protected ImageView _image;
        protected TextView _text;
        protected CheckBox _checkBox;
    }


    public AdministrateAllergenListViewAdapter(Activity context, List<AllergenPOJO> values) {
        super(context, R.layout.activity_administrate_allergen_listrow, values);
        this._parent = context;
        this._data = new ArrayList<AllergenViewPOJO>();
        boolean userHasAllergen;

        List<Integer> userAllergenIDs = new ArrayList<Integer>();
        List<AllergenPOJO> userAllergens = CurrentUser.getLogedInUser().getAllergene();

        for(int i = 0; i < userAllergens.size(); i++) {
            userAllergenIDs.add(userAllergens.get(i).getAllergenID());
        }

        for(int i = 0; i < values.size(); i++) {
            userHasAllergen = false;
            if(userAllergenIDs.contains(values.get(i).getAllergenID())) {
                userHasAllergen = true;
            }
            this._data.add(new AllergenViewPOJO(values.get(i), userHasAllergen));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = this._parent.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_administrate_allergen_listrow, null);
            final ViewHolder viewHolder = new ViewHolder();

            viewHolder._image = (ImageView) view.findViewById(R.id.listRowAllergenImage);
            viewHolder._text = (TextView) view.findViewById(R.id.listRowAllergenDescription);

            viewHolder._checkBox = (CheckBox) view.findViewById(R.id.administrateAllergenCheckbox);
            viewHolder._checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    AllergenViewPOJO element = (AllergenViewPOJO) viewHolder._checkBox.getTag();
                    element.set_selected(buttonView.isChecked());
                }
            });

            if(this._showCheckboxes == true) {
                viewHolder._checkBox.setVisibility(View.VISIBLE);
            } else {
                viewHolder._checkBox.setVisibility(View.GONE);
            }

            view.setTag(viewHolder);
            viewHolder._checkBox.setTag(this._data.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag())._checkBox.setTag(this._data.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder._text.setText(this._data.get(position).get_allergen().getDescription());
        holder._image.setImageResource(AllergenHelper.getAllergenImage(this._data.get(position).get_allergen().getAbbreviation()));
        holder._checkBox.setChecked(this._data.get(position).is_selected());
        return view;
    }

    public AllergenViewPOJO getAllergenAtPosition(int position) {
        return this._data.get(position);
    }

    public List<AllergenPOJO> getAllSelectedAllergens() {
        List<AllergenPOJO> result = new ArrayList<AllergenPOJO>();

        for(AllergenViewPOJO item : this._data) {
            if(item.is_selected()) {
                result.add(new AllergenPOJO(item.get_allergen().getAllergenID(), item.get_allergen().getDescription(), item.get_allergen().getAbbreviation()));
            }
        }

        return result;
    }


    public void setCheckboxView(boolean showCheckboxes) {
        this._showCheckboxes = showCheckboxes;
    }
}
