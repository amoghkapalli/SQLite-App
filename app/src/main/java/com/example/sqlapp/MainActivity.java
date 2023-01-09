package com.example.sqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button_viewAll, button_add;
    EditText et_customerName, et_age;
    Switch switch_active;
    ListView lv_customerList;
    ArrayAdapter customArrayAdapter;
    CustomerDB customerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_viewAll=findViewById(R.id.button_viewAll);
        button_add=findViewById(R.id.button_add);
        et_age=findViewById(R.id.et_age);
        et_customerName=findViewById(R.id.et_customerName);
        switch_active=findViewById(R.id.switch_active);
        lv_customerList=findViewById(R.id.lv_customerList);
        customerdb=new CustomerDB(MainActivity.this);
        ShowCustomerOnListView(customerdb);

        button_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, allCustomers.toString(), Toast.LENGTH_SHORT).show();
                ShowCustomerOnListView(customerdb);

            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customermodel;
                try{
                    customermodel =new CustomerModel(-1, et_customerName.getText().toString(), Integer.parseInt(et_age.getText().toString()), switch_active.isChecked());
                    Toast.makeText(MainActivity.this, customermodel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Incorrect information inputted", Toast.LENGTH_SHORT).show();
                    customermodel =new CustomerModel(-1, "error", 0, false);
                }
                CustomerDB customers=new CustomerDB(MainActivity.this);
                boolean success=customers.addOne(customermodel);
                /*
                if(success==true){
                    Toast.makeText(MainActivity.this, "the add function worked", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "the add function did not worked", Toast.LENGTH_SHORT).show();
                }
                 */
                ShowCustomerOnListView(customerdb);
            }
        });
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel clickedcustomer= (CustomerModel) adapterView.getItemAtPosition(i);
                customerdb.deleteOne(clickedcustomer);
                ShowCustomerOnListView(customerdb);
                Toast.makeText(MainActivity.this, "Deleted Customer " + clickedcustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowCustomerOnListView(CustomerDB customerdb) {
        customArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, customerdb.getAll());
        lv_customerList.setAdapter(customArrayAdapter);
    }
}