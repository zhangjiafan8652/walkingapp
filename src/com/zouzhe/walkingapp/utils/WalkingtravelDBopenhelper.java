package com.zouzhe.walkingapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WalkingtravelDBopenhelper extends SQLiteOpenHelper {

	public WalkingtravelDBopenhelper(Context context) {
		super(context, "walkingtravel.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table travelsiji (_id integer primary key autoincrement,driver_id varchar(30),passenger_number varchar(30), name varchar(20),avatar varchar(50),driving_years varchar(20),renzhengstatus varchar(20),identity_card_number varchar(30),identity_card_image varchar(30),province varchar(30),service_cities varchar(30),vehicle_model varchar(30),vehicle_age varchar(30),license_plate_number varchar(30),status varchar(30),recommended varchar(30),position varchar(30),nation varchar(30),contact varchar(30),trasportation_forced_insurance varchar(30),vehicle_image varchar(30),price varchar(30),age varchar(30),remark varchar(30),driver_license_image varchar(30),vehicle_driving_image varchar(30),user varchar(30),recommended_user varchar(30),main_cities varchar(30),approve varchar(30),source varchar(30),vehicle varchar(30))");
		db.execSQL("create table Orderbean_db (_id integer primary key autoincrement,total_price varchar(30), return_time varchar(20),spend_time varchar(20),order_no varchar(30), order_id varchar(20),traveller varchar(20),route_name varchar(30), traveller_mobile varchar(20),earnest_money varchar(20),depart_time varchar(30), pay_status varchar(20),driver_id varchar(50))");
		db.execSQL("create table Rushdaobean_db (_id integer primary key autoincrement,driver_id varchar(30),spend_time varchar(30), order_no varchar(20),earnest_money varchar(10),name varchar(20),mobile varchar(20),date_depart varchar(30),date_return varchar(30),route varchar(30),status varchar(30))");
		db.execSQL("create table Messagebean_db  (_id integer primary key autoincrement,driver_id varchar(30), messagetitle varchar(10),messagetext varchar(300),messagetype varchar(20),messagestatus varchar(20))");
		db.execSQL("create table schedule_db  (_id integer primary key autoincrement,driver_id varchar(30), date varchar(10),schedule_id varchar(300),schedule_status varchar(20))");
		System.out.println("我创建了数据库");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
