package com.example.masud.stackoverflow;

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