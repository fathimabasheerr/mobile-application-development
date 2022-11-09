package com.example.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,delete,view;
    DBHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            name=findViewById(R.id.e1);
            contact=findViewById(R.id.e2);
            dob=findViewById(R.id.e3);

            insert=findViewById(R.id.b1);
            update=findViewById(R.id.b2);
            delete=findViewById(R.id.b3);
            view=findViewById(R.id.b4);

            DB=new DBHelper(this);

            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nametxt= name.getText().toString();
                    String contacttxt= contact.getText().toString();
                    String dobtxt= dob.getText().toString();

                    boolean check=DB.insertuserdate(nametxt,contacttxt,dobtxt);
                    if(check){
                        Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nametxt= name.getText().toString();
                    String contacttxt= contact.getText().toString();
                    String dobtxt= dob.getText().toString();

                    boolean check=DB.updateuserdate(nametxt,contacttxt,dobtxt);
                    if(check==true){
                        Toast.makeText(MainActivity.this,"Entry Updated", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nametxt= name.getText().toString();
                    boolean check=DB.deletedate(nametxt);
                    if(check==true){
                        Toast.makeText(MainActivity.this,"Entry Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor res=DB.getdata();
                    if(res.getCount()==0){
                        Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer=new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Name :"+res.getString(0)+"\n");
                        buffer.append("Contact :"+res.getString(1)+"\n");
                        buffer.append("Dob :"+res.getString(2)+"\n\n");
                    }

                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Entries");
                    builder.setMessage(buffer.toString());
                    builder.show();

                }
            });

        }
    }
