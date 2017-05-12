package sushantkumarjha.gtithubapi_task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sushant kumar jha on 12-05-2017.
 */

public class AdapterHolder extends RecyclerView.Adapter<AdapterHolder.MyHolder>{
    private List<String>title;

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textid);

        }
    }


    public AdapterHolder(List<String>title) {
        this.title = title;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder.MyHolder holder, int position) {
        holder.textView.setText(title.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }
}
