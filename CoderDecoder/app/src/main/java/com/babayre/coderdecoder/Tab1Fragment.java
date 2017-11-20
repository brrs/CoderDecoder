package com.babayre.coderdecoder;

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
import android.content.ClipData;
import com.babayre.coderdecoder.cryptho.Caesar;
import java.util.ArrayList;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private String s;
    private ArrayList<String> history;
    private int indx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        final EditText editText1 = (EditText) view.findViewById(R.id.editText1);
        Button buttonGo = (Button) view.findViewById(R.id.button1);
        Button buttonBack = (Button) view.findViewById(R.id.button2);
        Button buttonClear = (Button) view.findViewById(R.id.button3);
        s = "";
        indx = 0;


        View.OnClickListener button1OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = editText1.getText().toString();
                int sizeHistory = history.size();
                if (sizeHistory == 10) {
                    history.remove(0);
                    history.add(s);
                    indx = sizeHistory;
                } else {
                    history.add(s);
                    indx = sizeHistory;
                }
                String x = s;

                Caesar ciprg= new Caesar(3);
                x = ciprg.toCodingText(x);

                editText1.setText(x);
                editText1.setSelection(x.length());

                String copiedText = x;
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text",copiedText);
                clipboard.setPrimaryClip(clip);

            }
        };

        View.OnClickListener button2OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //editText1.setText(s);

                editText1.setText(history.get(indx));
                indx--;
                editText1.setSelection(s.length());

            }
        };

        View.OnClickListener button3OnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = editText1.toString();
                editText1.setText("");
            }
        };
        buttonGo.setOnClickListener(button1OnClick);
        buttonBack.setOnClickListener(button2OnClick);
        buttonClear.setOnClickListener(button3OnClick);

        return view;
    }
}
