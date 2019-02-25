# A basic implementation of RecyclerView having a header and a footer

Let us first declare an adapter for adding a header and a footer to the `RecyclerView`. Please check the comments to have a better understanding. 

Hence the `RecyclerViewWithHeaderFooterAdapter.java` is as follows. 

```java
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerViewWithHeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FOOTER_VIEW = 1;
    private static final int HEADER_VIEW = 2;
    private ArrayList<String> data; // Take any list that matches your requirement.
    private Context context;

    // Define a constructor
    public RecyclerViewWithHeaderFooterAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    // Define a ViewHolder for Header view
    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    // Define a ViewHolder for Footer view
    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });
        }
    }

    // Now define the ViewHolder for Normal list item
    public class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the normal items
                }
            });
        }
    }

    // And now in onCreateViewHolder you have to pass the correct view
    // while populating the list item.

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_footer, parent, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        } else if (viewType == HEADER_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.list_item_header, parent, false);
            HeaderViewHolder vh = new HeaderViewHolder(v);
            return vh;
        }

        // Otherwise populate normal views
        v = LayoutInflater.from(context).inflate(R.layout.list_item_normal, parent, false);
        NormalViewHolder vh = new NormalViewHolder(v);

        return vh;
    }

    // Now bind the ViewHolder in onBindViewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder instanceof NormalViewHolder) {
                NormalViewHolder vh = (NormalViewHolder) holder;

                vh.bindView(position);
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;
            } else if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder vh = (HeaderViewHolder) holder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Now the critical part. You have return the exact item count of your list
    // I've only one footer. So I returned data.size() + 1
    // If you've multiple headers and footers, you've to return total count
    // like, headers.size() + data.size() + footers.size()

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }

        if (data.size() == 0) {
            // Return 1 here to show nothing
            return 1;
        }

        // Add extra view to show the header view
        // Add another extra view to show the footer view
        // So there are two extra views need to be populated
        return data.size() + 2;
    }

    // Now define getItemViewType of your own.
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            // This is where we'll add the header.
            return HEADER_VIEW;
        } else if (position == data.size() + 1) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    // So you're done with adding a footer and its action on onClick.
    // Now set the default ViewHolder for NormalViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define elements of a row here
        public ViewHolder(View itemView) {
            super(itemView);
            // Find view by ID and initialize here
        }

        public void bindView(int position) {
            // bindView() method to implement actions
        }
    }
}
```
Now let us define the layouts one by one. Here is the `list_item_normal.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/normal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:text="This is a text to be displayed in each item in the RecyclerView"
        android:textSize="16sp"
        android:textStyle="normal" />

</LinearLayout>
```

And the `list_item_footer.xml` should look like the following.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    android:orientation="vertical">

    <RadioGroup
        android:id="@+id/myRadioGroup"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="10dp"
        android:checkedButton="@+id/sound">

        <RadioButton
            android:id="@+id/sound"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sound" />

        <RadioButton
            android:id="@+id/vibration"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Vibration" />

        <RadioButton
            android:id="@+id/silent"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Silent" />

    </RadioGroup>

    <EditText
        android:id="@+id/notes"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Notes" />

    <LinearLayout
        android:id="@+id/buttons"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="10dp">

        <TextView
            android:id="@+id/cancel_button"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="center"
            android:text="Cancel" />

        <TextView
            android:id="@+id/submit_button"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:gravity="center"
            android:text="Submit" />
    </LinearLayout>
</LinearLayout>
```
Finally, the `list_item_header.xml` should have the following.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/sub_headline_literal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:padding="10dp"
        android:text="Some long texts having a long size so that it takes multiple lines in the view to replicate the real-life app use case. This is important to have 3-4 lines this textview so that we can see if the views are being populated correctly. Hope this sentence is long enough to replicate the real-life scenario of this TextView content. Thank you."
        android:textSize="16sp"
        android:textStyle="normal" />

</LinearLayout>
```

Now the container of the `RecyclerView` might have a layout like the following. Let us take the name of the layout as `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:id="@+id/headline_literal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:padding="10dp"
        android:text="Heading"
        android:textSize="20sp"
        android:textStyle="bold" />

    <View
        android:id="@+id/divider"
        android:layout_width="fill_parent"
        android:layout_height="2dp"
        android:layout_marginTop="5dp"
        android:background="#c0c0c0" />

    <android.support.v7.widget.RecyclerView
        android:id="@+id/dummy_rv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:padding="10dp" />
</LinearLayout>
```

Hence, I am sharing one sample Activity (`MainActivity.java`) to run this code which will show the overall implementation.

```java
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private RecyclerViewWithHeaderFooterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.dummy_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewWithHeaderFooterAdapter(this, data);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        for (int i = 0; i < 10; i++) data.add("Position :" + i);
    }
}
```
