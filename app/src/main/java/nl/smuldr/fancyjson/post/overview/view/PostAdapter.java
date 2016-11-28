package nl.smuldr.fancyjson.post.overview.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.post.overview.PostListPresenter;
import nl.smuldr.fancyjson.shared.model.Post;


public final class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private final List<Post> data;
    private final PostListPresenter presenter;

    public PostAdapter(final List<Post> data, final PostListPresenter presenter) {
        this.data = data;
        this.presenter = presenter;
    }

    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final PostSummaryView postSummaryView = (PostSummaryView) inflater.inflate(R.layout.post_summary, parent, false);
        return new PostViewHolder(postSummaryView);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        final Post post = data.get(position);
        holder.setData(post);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                presenter.onPostClick(view.getContext(), post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        PostViewHolder(final PostSummaryView view) {
            super(view);
        }

        void setData(final Post post) {
            ((PostSummaryView) itemView).setData(post);
        }
    }
}
