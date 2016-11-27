package nl.smuldr.fancyjson.post.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.shared.model.Post;


public final class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> data;

    public PostAdapter(final List<Post> data) {
        this.data = data;
    }

    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final PostSummaryView postSummaryView = (PostSummaryView) inflater.inflate(R.layout.post_summary, parent, false);
        return new PostViewHolder(postSummaryView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        final PostSummaryView postSummaryView = (PostSummaryView) holder.itemView;
        final Post post = data.get(position);
        postSummaryView.setData(post);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        PostViewHolder(final PostSummaryView view) {
            super(view);
        }
    }
}
