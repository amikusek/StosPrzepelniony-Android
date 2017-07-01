package pl.sggw.stosprzepelniony.util.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.sggw.stosprzepelniony.R;

public class ChipsView extends LinearLayout {

    TextView textView;

    public ChipsView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chips_view, this, true);
        textView = ((TextView) findViewById(R.id.chipsText));
    }

    public ChipsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChipsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String text) {
        textView.setText(text);
        invalidate();
        requestLayout();
    }
}
