package com.zouzhe.walkingapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zouzhe.walkingapp.javabean.Driver_routebean;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.utils.IntentUtils;


import java.util.List;


public class Route_Activity extends ActionBarActivity {

    ListView lv_myrout_listview;


    private List<Driver_routebean>  route_list;

    private Activity mactivty;
	private TextView tv_route_edroute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_);
        mactivty=this;
        inintView();
    }

    private void inintView() {
        lv_myrout_listview = (ListView) findViewById(R.id.lv_myroute_listview);
        ImageView iv_route_pre=(ImageView) findViewById(R.id.iv_route_pre);
        iv_route_pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        tv_route_edroute = (TextView) findViewById(R.id.tv_route_edroute);
        tv_route_edroute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentUtils.startActivity(mactivty, NewRoute_step1_Activity.class);
				
			}
		});
        
        initdata();
    }

    private void initdata() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("driver_id", Myconstants.driver_id);
        httpUtils.send(HttpRequest.HttpMethod.POST,Myconstants.routeurl,params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                processData(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    private void processData(String result) {

        Log.e("得到的路线",""+result);
        try {
            route_list = JSON.parseArray(result.toString(), Driver_routebean.class);
            Route_ListviewAdapter route_listviewAdapter = new Route_ListviewAdapter();
            lv_myrout_listview.setAdapter(route_listviewAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv_myrout_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    class Route_ListviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return  route_list.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        DriverrouteHolder driverrouteHolder;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView= View.inflate(getApplicationContext(), R.layout.route_listitem, null);
                driverrouteHolder = new DriverrouteHolder();
                driverrouteHolder.tv_myrouteitem_title= (TextView) convertView.findViewById(R.id.tv_myrouteitem_title);
                driverrouteHolder.tv_myrouteitem_route= (TextView) convertView.findViewById(R.id.tv_myrouteitem_route);
                driverrouteHolder.tv_orderlistitem_days= (TextView) convertView.findViewById(R.id.tv_myrouteitem_days);
                driverrouteHolder.tv_myrouteitem_money= (TextView) convertView.findViewById(R.id.tv_myrouteitem_money);

                convertView.setTag(driverrouteHolder);
            }else{
                driverrouteHolder= (DriverrouteHolder) convertView.getTag();
            }
            driverrouteHolder.tv_myrouteitem_title.setText(route_list.get(position).getRoute_title()+"  "+route_list.get(position).getRoute_titledescrible());
            driverrouteHolder.tv_myrouteitem_route.setText("途径："+route_list.get(position).getRoute());
            driverrouteHolder.tv_myrouteitem_money.setText("￥"+route_list.get(position).getPrice()+"/每人");
            driverrouteHolder.tv_orderlistitem_days.setText("共"+route_list.get(position).getPrice()+"天");

            return convertView;
        }
    }

    class DriverrouteHolder {
        TextView tv_myrouteitem_title;
        TextView tv_myrouteitem_route;
        TextView tv_myrouteitem_money;
        TextView tv_orderlistitem_days;
    }

}
