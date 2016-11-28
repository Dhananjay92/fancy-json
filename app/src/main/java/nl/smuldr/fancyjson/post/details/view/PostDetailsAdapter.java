package nl.smuldr.fancyjson.post.details.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import nl.smuldr.fancyjson.R;
import nl.smuldr.fancyjson.shared.model.Comment;
import nl.smuldr.fancyjson.shared.model.Post;


public final class PostDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int POST_DETAILS_TYPE = 1;
    private static final int COMMENT_TYPE = 2;

    private final List<Object> data;

    public PostDetailsAdapter(final List<Object> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        final Object item = data.get(position);
        if (item instanceof Post) {
            return POST_DETAILS_TYPE;
        } else if (item instanceof Comment) {
            return COMMENT_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case POST_DETAILS_TYPE:
                final PostDetailsView postDetailsView = (PostDetailsView) inflater.inflate(R.layout.post_details, parent, false);
                return new PostDetailsViewHolder(postDetailsView);
            case COMMENT_TYPE:
                final CommentView commentView = (CommentView) inflater.inflate(R.layout.comment, parent, false);
                return new CommentViewHolder(commentView);
            default:
                throw new IllegalArgumentException("Invalid view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Object item = data.get(position);
        if (item instanceof Post) {
            ((PostDetailsViewHolder) holder).setData((Post) item);
        } else if (item instanceof Comment) {
            ((CommentViewHolder) holder).setData((Comment) item);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class PostDetailsViewHolder extends RecyclerView.ViewHolder {

        PostDetailsViewHolder(final PostDetailsView view) {
            super(view);
        }

        public void setData(final Post post) {
            ((PostDetailsView) itemView).setData(post);
        }
    }

    private static class CommentViewHolder extends RecyclerView.ViewHolder {

        CommentViewHolder(final CommentView view) {
            super(view);
        }

        public void setData(final Comment comment) {
            ((CommentView) itemView).setData(comment);
        }
    }
}
