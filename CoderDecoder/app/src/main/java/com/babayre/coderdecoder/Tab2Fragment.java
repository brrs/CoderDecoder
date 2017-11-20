package com.babayre.coderdecoder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.babayre.coderdecoder.cryptho.Caesar;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private String s;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        final EditText editText1 = (EditText) view.findViewById(R.id.editText1);


        Button button1 = (Button) view.findViewById(R.id.button4);
        Button button2 = (Button) view.findViewById(R.id.button5);
        Button button3 = (Button) view.findViewById(R.id.button6);



        View.OnClickListener button1OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = editText1.getText().toString();
                Caesar decifr = new Caesar(3);
                String x = s;
                if (!(s== "")) {
                    editText1.setText(decifr.toEncodingText(s));
                } else {
                    ClipboardManager myClipboard;
                    myClipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

                    ClipData myClip = myClipboard.getPrimaryClip();
                    ClipData.Item item = myClip.getItemAt(0);
                    String text = item.getText().toString();

                    x = decifr.toEncodingText(text);
                    editText1.setText(x);
                }

                editText1.setSelection(x.length());
            }
        };

        View.OnClickListener button2OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s = editText1.getText().toString();
                editText1.setText("");

            }
        };

        View.OnClickListener button3OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setText(s);
                editText1.setSelection(s.length());
            }
        };


        button1.setOnClickListener(button1OnClick);
        button2.setOnClickListener(button2OnClick);
        button3.setOnClickListener(button3OnClick);

        return view;
    }
}
