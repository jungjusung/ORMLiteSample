package com.example.android.ormlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jusung on 2017. 1. 18..
 */

public class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mMemberId;
    private TextView mMemberName;
    private TextView mMemberAge;
    private TextView mMemberGender;
    private TextView mMemberEmail;


    private TextView mMemberRegdate;
    private Context context;


    private MemberAdapter.ListItemClickListener mOnClickListener;

    public MemberViewHolder(View itemView, Context context, MemberAdapter.ListItemClickListener mOnClickListener) {
        super(itemView);
        this.context = context;
        this.mOnClickListener = mOnClickListener;
        itemView.setOnClickListener(this);

        mMemberId = (TextView) itemView.findViewById(R.id.tv_member_id);
        mMemberName = (TextView) itemView.findViewById(R.id.tv_member_name);
        mMemberAge = (TextView) itemView.findViewById(R.id.tv_member_age);
        mMemberGender = (TextView) itemView.findViewById(R.id.tv_member_gender);
        mMemberEmail = (TextView) itemView.findViewById(R.id.tv_member_email);
        mMemberRegdate = (TextView) itemView.findViewById(R.id.tv_member_regdate);
    }

    void bind(int listIndex) {
        MemberOpenDataHelper memberOpenDataHelper = OpenHelperManager.getHelper(context, MemberOpenDataHelper.class);

        try {
            Dao<Member, Long> memberDao = memberOpenDataHelper.getDao();
            List<Member> list = memberDao.queryBuilder().query();
            Member member=list.get(listIndex);
            mMemberId.setText(String.valueOf(member.getMember_id()));
            mMemberName.setText(member.getMember_name());
            mMemberAge.setText(String.valueOf(member.getMember_age()));
            mMemberGender.setText(member.getMember_gender());
            mMemberEmail.setText(member.getMember_email());
            mMemberRegdate.setText(member.getMember_regdata());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        final int clickedPosition = getAdapterPosition();
        mOnClickListener.onListItemClick(clickedPosition);


    }
}
