package com.example.android.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MemberAdapter.ListItemClickListener {

    EditText mEditTextMemberName;

    EditText mEditTextMemberAge;
    EditText mEditTextMemberGender;
    EditText mEditTextMemberEmail;


    Button mButtonAddMember;


    private MemberAdapter mMemberAdapter;
    private RecyclerView mMemberList;
    static int SEQUNCE_NUMBER=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextMemberName = (EditText) findViewById(R.id.ed_member_name);
        mEditTextMemberAge = (EditText) findViewById(R.id.ed_member_age);
        mEditTextMemberGender = (EditText) findViewById(R.id.ed_member_gender);
        mEditTextMemberEmail = (EditText) findViewById(R.id.ed_member_email);

        mButtonAddMember = (Button) findViewById(R.id.bt_add_member);
        mButtonAddMember.setOnClickListener(this);

        mMemberList = (RecyclerView) findViewById(R.id.rv_memberList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMemberList.setLayoutManager(layoutManager);
        mMemberList.setHasFixedSize(true);


        final Context context = this.getApplicationContext();
        long dbSize = 0;
        MemberOpenDataHelper memberOpenDataHelper = OpenHelperManager.getHelper(this, MemberOpenDataHelper.class);
        try {
            Dao<Member, Long> memberDao = memberOpenDataHelper.getDao();
            dbSize = memberDao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        mMemberAdapter = new MemberAdapter((int) dbSize, context, this);
        mMemberList.setAdapter(mMemberAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_add_member) {

            final Context context = this.getApplicationContext();
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            MemberOpenDataHelper memberOpenDataHelper = OpenHelperManager.getHelper(this, MemberOpenDataHelper.class);
            Dao<Member, Long> memberDao = null;
            try {
                memberDao = memberOpenDataHelper.getDao();
                Member member = new Member();
                SEQUNCE_NUMBER += 1;

                member.setMember_id(SEQUNCE_NUMBER);
                member.setMember_name(mEditTextMemberName.getText().toString());
                member.setMember_age(Integer.parseInt(mEditTextMemberAge.getText().toString()));
                member.setMember_gender(mEditTextMemberGender.getText().toString());
                member.setMember_email(mEditTextMemberEmail.getText().toString());
                member.setMember_regdata(date.toString());

                memberDao.createOrUpdate(member);


                Toast.makeText(context, "memberSize : " + (memberDao.countOf()+1), Toast.LENGTH_SHORT).show();
                mEditTextMemberName.setText("");
                mEditTextMemberAge.setText("");
                mEditTextMemberGender.setText("");
                mEditTextMemberEmail.setText("");


                mMemberAdapter.setItem((int)memberDao.countOf());
                mMemberAdapter.notifyDataSetChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

        Context context=this.getApplicationContext();

        final int itemNum = clickedItemIndex+1;
        final MemberOpenDataHelper memberOpenDataHelper = OpenHelperManager.getHelper(context, MemberOpenDataHelper.class);

        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        alert_confirm.setMessage("No."+itemNum+" 데이터를 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dao<Member, Long> memberDao = null;
                        try {
                            memberDao = memberOpenDataHelper.getDao();
                            memberDao.deleteById((long)itemNum);
                            mMemberAdapter.setItem((int)memberDao.countOf());
                            mMemberAdapter.notifyDataSetChanged();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();
    }


}

