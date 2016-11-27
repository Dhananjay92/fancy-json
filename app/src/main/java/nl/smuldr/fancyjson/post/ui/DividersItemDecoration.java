package nl.smuldr.fancyjson.post.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import nl.smuldr.fancyjson.R;


/**
 * Draws dividers between RecyclerView items
 */
public class DividersItemDecoration extends RecyclerView.ItemDecoration {

    private final int inset;
    private final int height;
    private final Drawable divider;

    public DividersItemDecoration(final Context context) {
        final Resources resources = context.getResources();
        inset = resources.getDimensionPixelSize(R.dimen.divider_inset);
        divider = ContextCompat.getDrawable(context, R.drawable.divider);
        height = divider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(final Canvas c, final RecyclerView parent, final RecyclerView.State state) {

        int left = parent.getPaddingLeft() + inset;
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + height;
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent, final RecyclerView.State state) {
        outRect.set(0, 0, 0, height);
    }
}
