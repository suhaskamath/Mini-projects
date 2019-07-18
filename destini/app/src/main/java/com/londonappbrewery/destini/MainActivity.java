package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:

    private Button option1;
    private Button option2;
    private TextView disp;
    private int i;
    int[] tstory={R.string.T1_Story,R.string.T2_Story,R.string.T3_Story,R.string.T4_End,R.string.T5_End,R.string.T6_End};
    int[] ans={R.string.T1_Ans1,R.string.T1_Ans2,R.string.T2_Ans1,R.string.T1_Ans2,R.string.T3_Ans1,R.string.T3_Ans2};
   destiny root=new destiny(tstory[0],ans[0],ans[1]);
   destiny temp=root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        option1=findViewById(R.id.button);
        option2=findViewById(R.id.button2);
        disp=findViewById(R.id.story);
        root.ques1=new destiny(tstory[2],ans[4],ans[5]);
        root.ques2=new destiny(tstory[1],ans[2],ans[3]);
        root.ques1.ques1=new destiny(tstory[4],0,0);
        root.ques1.ques2=new destiny(tstory[5],0,0);
        root.ques2.ques1=new destiny(tstory[2],ans[4],ans[5]);
        root.ques2.ques1.ques1=new destiny(tstory[4],0,0);
        root.ques2.ques1.ques1=new destiny(tstory[5],0,0);
        root.ques2.ques2=new destiny(tstory[3],0,0);



        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","Button 1 Pressed");
                    update(1);
            }
        });
        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","Button @ pressed");
                update(2);
            }
        });



    }
    public void update(int j)
    {
        if(j==1) {
            temp = temp.ques1;
        }
        else {temp=temp.ques2;
        }
            disp.setText(temp.getDstory());
            if(temp.getRespone1()!=0) {
                option1.setText(temp.getRespone1());
                option2.setText(temp.getRespone2());
            }
        else
        {
            ViewGroup layout = (ViewGroup) option1.getParent();
            layout.removeView(option1);
            layout.removeView(option2);
            android.app.AlertDialog.Builder alert=new android.app.AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(true);
            alert.setMessage("Done");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }

}
