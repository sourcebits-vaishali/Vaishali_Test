package com.example.vaishaliarora.myapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vaishaliarora.myapplication.R;
import com.example.vaishaliarora.myapplication.model.DummyData;


import java.util.List;

/**
 * Created by vaishaliarora on 30/05/16.
 */
public class TranslatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translator, container, false);
        /*ListView list = (ListView)view.findViewById(R.id.list_translator);
        List<String> data = DummyData.getTranslatorList();*/
        return view;
    }

    class MyAdapter extends BaseAdapter{

        private List<String> mElements;
        public MyAdapter(List<String> data){
            mElements = data;
        }

        @Override
        public int getCount() {
            return mElements.size();
        }

        @Override
        public Object getItem(int position) {
            return mElements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if(null == convertView){
                convertView = View.inflate(getActivity() , R.layout.translator_custom_view , null);
                holder = new ViewHolder();
                holder.title = (TextView)convertView.findViewById(R.id.text);
                holder.translated = (TextView)convertView.findViewById(R.id.translate);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.title.setText(mElements.get(position));

            holder.title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    holder.translated.setVisibility(View.VISIBLE);
                    holder.translated.setText(getTranslatedText(mElements.get(position)));
                    return true;
                }
            });

            return convertView;
        }
        class ViewHolder{
            TextView title, translated;
        }
    }

    public String getTranslatedText(String inputText){
        String outputString = "";
        //TODO- we must need an api key to run this sample. Its a paid service.
        /*try {
            Translate.setHttpReferrer("http://android-er.blogspot.com/");

            outputString = Translate.execute(inputText,
                    Language.ENGLISH, Language.FRENCH);
        } catch (Exception ex) {
            ex.printStackTrace();
            outputString = "Error";
        }*/
        return outputString;
    }
}
