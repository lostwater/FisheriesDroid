package com.xyxd.fisher.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.xyxd.fisher.Activity.AVSingleChatActivity;
import com.xyxd.fisher.R;
import com.xyxd.fisher.adapter.MembersAdapter;
import com.xyxd.fisher.lean.Constants;

import butterknife.Bind;

/**
 * Created by wli on 15/8/14.
 */
public class MemberHolder extends AVCommonViewHolder {

  @Bind(R.id.member_item_name)
  public TextView mTextView;

  public MemberHolder(Context context, ViewGroup root) {
    super(context, root, R.layout.activity_member_item);
  }

  @Override
  public void bindData(Object o) {
    final MembersAdapter.MemberItem item = (MembersAdapter.MemberItem) o;
    mTextView.setText(item.content);
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Activity host = (Activity) itemView.getContext();
        Intent intent = new Intent(host, AVSingleChatActivity.class);
        intent.putExtra(Constants.MEMBER_ID, item.content);
        host.startActivity(intent);
      }
    });
  }
}