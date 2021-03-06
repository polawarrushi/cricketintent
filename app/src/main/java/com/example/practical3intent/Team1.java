package com.example.practical3intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Team1 extends Activity {
String[] player={"Virat Kohli","Jasprit Bumrah", "MS Dhoni"," Bhuvneshwar Kumar", "Hardik Pandya", "KL Rahul","Rohit Sharma"};
    ArrayList<Integer> list=new ArrayList<>();
    ArrayList<Integer> selected=new ArrayList<>();
ArrayList<String> India=new ArrayList<>();
int count;

    public SparseBooleanArray checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team1);
        Intent i=getIntent();
        count=i.getIntExtra("count",0);
        TextView tv=findViewById(R.id.tv);
            list=i.getIntegerArrayListExtra("checked");
        final ListView lv=findViewById(R.id.lv);
        ArrayAdapter<String> arr=new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,player);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        selected.addAll(list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                if (lv.isItemChecked(position)) {
                    if(count<11) {
                        selected.add(position);
                        count++;
                    }
                    else
                    { lv.setItemChecked(position,false);
                    }
                }
                else {

                    count--;
                    lv.setItemChecked(position,false);
                }
            }
        });

         lv.setAdapter(arr);

     for(int j=0;j<list.size();j++){
         lv.setItemChecked(list.get(j),true);
     }

        final Button s1=findViewById(R.id.t1b);
         s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                   checked=lv.getCheckedItemPositions();

                   for(int k=0;k<checked.size();k++){
                       if(checked.valueAt(k)) {
                           int l;
                           l=checked.keyAt(k);
                               list.add(l);
                               India.add(player[l]);
                       }
                   }


               Intent i=new Intent(getApplicationContext(),MainActivity.class);

                i.putIntegerArrayListExtra("checked",list);
                i.putStringArrayListExtra("india",India);
                i.putExtra("count",count);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

}
