package CustomUI;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import Pojo.ProblemType;
import yellocard.irestoreapp.com.yellowcard.R;

/**
 * Created by MALBEL on 12-12-2017.
 */

public class ProblemTypeAdapter extends ArrayAdapter<ProblemType> implements View.OnClickListener{
    private ArrayList<ProblemType> dataSet;
    Context mContext;
    ListView listView;
    public static HashMap<Integer, String> myList = new HashMap<Integer, String>();

    // View lookup cache
    private static class ViewHolder {
        TextView inspectionName;
        ImageView inspectionImage;
    }

    public ProblemTypeAdapter(ArrayList<ProblemType> data, Context context/*, ListView listView*/) {
        super(context, R.layout.inspection_data_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        Typeface typeFace = Typeface.createFromAsset(mContext.getAssets(), "AvenirLTStd-Book.otf");

        final View result;

        final int pos = position;
        HashMap<String, String> hashMap = new HashMap<String, String>();

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.inspection_data_item, parent, false);
            viewHolder.inspectionName = (TextView) convertView.findViewById(R.id.probleTypeName);
            viewHolder.inspectionImage = (ImageView) convertView.findViewById(R.id.problemTypeImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProblemType inspectionMetaData = (ProblemType) getItem(position);
        viewHolder.inspectionName.setText(inspectionMetaData.getDisplayName());
        viewHolder.inspectionName.setTypeface(typeFace);
        if(inspectionMetaData.getSelected() != null && !inspectionMetaData.getDisplayName().isEmpty()
                && inspectionMetaData.getDisplayName() != null ) {
            viewHolder.inspectionImage.setVisibility(View.VISIBLE);
            if(inspectionMetaData.getSelected())
                Picasso.with(mContext).load(inspectionMetaData.getTickedImageUrl()).placeholder(R.mipmap.ic_launcher).noFade()
                        .into(viewHolder.inspectionImage);
            else
                Picasso.with(mContext).load(inspectionMetaData.getDefaultImageUrl()).placeholder(R.mipmap.ic_launcher).noFade()
                        .into(viewHolder.inspectionImage);
        } else {
            viewHolder.inspectionImage.setVisibility(View.GONE);
        }

        return convertView;
    }
}
