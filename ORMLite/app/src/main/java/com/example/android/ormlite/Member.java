package com.example.android.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Jusung on 2017. 1. 17..
 */
//@DatabaseTable , @DatabaseField를 사용해서 테이블 생성
@DatabaseTable(tableName = "Member")
public class Member{

    //기본키 설정
    @DatabaseField(generatedId = true)
    private int member_id;

    @DatabaseField
    private String member_name;
    @DatabaseField
    private int member_age;
    @DatabaseField
    private String member_gender;
    @DatabaseField
    private String member_email;
    @DatabaseField
    private String member_regdata;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_age() {
        return member_age;
    }

    public void setMember_age(int member_age) {
        this.member_age = member_age;
    }

    public String getMember_gender() {
        return member_gender;
    }

    public void setMember_gender(String member_gender) {
        this.member_gender = member_gender;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_regdata() {
        return member_regdata;
    }

    public void setMember_regdata(String member_regdata) {
        this.member_regdata = member_regdata;
    }
}
