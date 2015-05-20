package faber.drew.cis46300.cityguide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyCustomArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final int[] images;

    public MyCustomArrayAdapter(Context context, int fromLayout, int toLayout, String[] values, int[] images)
    {
        super(context, fromLayout, toLayout, values);
        this.context = context;
        this.values = values;
        this.images = images;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.main, null);

        TextView textView = (TextView) rowView.findViewById(R.id.travel);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(values[position]);
        imageView.setImageResource(images[position]);

        return rowView;
    }
}
