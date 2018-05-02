package kushpatel.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kushpatel.foodme.adapters.RecyclerViewAdapter;
import kushpatel.foodme.model.Item;

public class ItemActivity extends AppCompatActivity {


    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public RecyclerViewAdapter recyclerViewAdapter;
    public ArrayList<Item> itemArrayList = new ArrayList<>();
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this,itemArrayList);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("FoodDetails");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Item items = data.getValue(Item.class);
//                    StudentDetails studentDetails = dataSnapshot.getValue(StudentDetails.class);

                    itemArrayList.add(items);
                }

                recyclerViewAdapter = new RecyclerViewAdapter(ItemActivity.this,itemArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
