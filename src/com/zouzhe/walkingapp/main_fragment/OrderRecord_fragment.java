package com.zouzhe.walkingapp.main_fragment;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zouzhe.walkingapp.R;
import com.zouzhe.walkingapp.javabean.Orderrecode_javabean;
import com.zouzhe.walkingapp.myconstants.Myconstants;
import com.zouzhe.walkingapp.myview.RefreshListView;

/**
 * Created by Administrator on 2015/3/2.
 */
public class OrderRecord_fragment extends BaseFragment{


    RefreshListView rlv_orderrecord;
    ProgressBar pb_orderrecord;
    private  List<Orderrecode_javabean> order_lists;
    private int page=1;
    MyListviewAdapter myListviewAdapter;
	private int hADINITFROMLOCAL=0;
	private LinearLayout ll_orderrecord_nofound;

    public OrderRecord_fragment(Activity activity) {
        super(activity);
    }

    @Override
    public View initview() {
    	
        View view = View.inflate(mcontext, R.layout.orderrecord_fragment, null);
        rlv_orderrecord = (RefreshListView) view.findViewById(R.id.rlv_orderrecord);
        pb_orderrecord = (ProgressBar) view.findViewById(R.id.pb_orderrecord_progress);
        ll_orderrecord_nofound = (LinearLayout) view.findViewById(R.id.ll_orderrecord_nofound);
        
        rlv_orderrecord.isEnabledPullDownRefresh(true);
        rlv_orderrecord.isEnabledLoadingMore(true);
        rlv_orderrecord.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onPullDownRefresh() {
                initfromInternet();
            }

            @Override
            public void onLoadingMore() {
                    initdataOnloadmore();
            }
        });
        
    	//TextView view=new TextView(mcontext);
    	///view.setText("我的订单");
        return view;
    }

    @Override
    public void initdata() {
    	
    	initfromLocal();
    	
        initfromInternetfirst();
        
        
    }

    private void initfromInternet() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("driver_id", Myconstants.driver_id);
        params.addBodyParameter("page", 0+"");
        params.addBodyParameter("limit", "6");
        Log.e("看看地址", "nihaoya" + Myconstants.orderurl);
        httpUtils.send(HttpRequest.HttpMethod.POST, Myconstants.orderurl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                Log.e("测试测到的数据", "" + objectResponseInfo.result);
                dealData(objectResponseInfo.result);
                page=1;
                rlv_orderrecord.onRefreshFinish();;
            }

            @Override
            public void onFailure(HttpException e, String s) {
                rlv_orderrecord.onRefreshFinish();
                Log.e("失败了哦", "e" + e);
            }
        });
    }
    

    private void initfromInternetfirst() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("driver_id", Myconstants.driver_id);
        params.addBodyParameter("page", 0+"");
        params.addBodyParameter("limit", "6");
        Log.e("看看地址", "nihaoya" + Myconstants.orderurl);
        httpUtils.send(HttpRequest.HttpMethod.POST, Myconstants.orderurl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                Log.e("测试测到的数据", "" + objectResponseInfo.result);
                dealData(objectResponseInfo.result);
                page=1;
               // rlv_orderrecord.onRefreshFinish();;
            }

            @Override
            public void onFailure(HttpException e, String s) {
               // rlv_orderrecord.onRefreshFinish();
            	if(hADINITFROMLOCAL==0){
            		rlv_orderrecord.setVisibility(View.GONE);
                	ll_orderrecord_nofound.setVisibility(View.VISIBLE);
                    Log.e("失败了哦", "e" + e);
            	}
            	
            }
        });
    }

    private void dealloadmoreData(String result) {
        try {
            List<Orderrecode_javabean> listtemps = JSON.parseArray(result.toString(), Orderrecode_javabean.class);
            order_lists.addAll(listtemps);
            myListviewAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initdataOnloadmore() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("driver_id", Myconstants.driver_id);
        params.addBodyParameter("page", ""+page);
        params.addBodyParameter("limit", "6");
        Log.e("看看地址", "nihaoya" + Myconstants.orderurl);
        httpUtils.send(HttpRequest.HttpMethod.POST, Myconstants.orderurl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                Log.e("测试测到的数据", "" + objectResponseInfo.result);
                dealloadmoreData(objectResponseInfo.result);
                page=page+1;
                rlv_orderrecord.onRefreshFinish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                rlv_orderrecord.onRefreshFinish();
                Log.e("失败了哦", "e" + e);
            }
        });
    }

    private void dealData(String result) {
        Log.e("得到的数据",result);

        try {
            order_lists = JSON.parseArray(result.toString(), Orderrecode_javabean.class);
            saveOrder(order_lists);
        } catch (Exception e) {
            e.printStackTrace();
        }


        startListview();


    }

    private void startListview(){
        myListviewAdapter = new MyListviewAdapter();
        rlv_orderrecord.setAdapter(myListviewAdapter);
        pb_orderrecord.setVisibility(View.GONE);
        ll_orderrecord_nofound.setVisibility(View.GONE);
        rlv_orderrecord.setVisibility(View.VISIBLE);
    }

    private void saveOrder(final List<Orderrecode_javabean> order_lists) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                DbUtils dbUtils = DbUtils.create(mcontext);
                try {
                    dbUtils.deleteAll(Orderrecode_javabean.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<order_lists.size();i++){
                    try {
                        dbUtils.save(order_lists.get(i));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initfromLocal() {

        try {
          DbUtils dbUtils = DbUtils.create(mactivity);
          order_lists = dbUtils.findAll(Orderrecode_javabean.class);
          Log.e("开始进去得到的数目",""+order_lists.size());
          if(order_lists.size()>0){
        	  startListview();
        	  //是否从本地加载成功
        	  hADINITFROMLOCAL = 1;
          }
          
        } catch (Exception e) {
           // Log.e("nihao",""+e);
            e.printStackTrace();
            return;
        }


    }

    class MyListviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return  order_lists.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        OrderRecordHolder orderRecordHolder;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=View.inflate(mcontext,R.layout.orderrecord_listitem,null);
                orderRecordHolder = new OrderRecordHolder();
                orderRecordHolder.tv_orderlistitem_title= (TextView) convertView.findViewById(R.id.tv_orderlistitem_title);
                orderRecordHolder.tv_orderlistitem_route= (TextView) convertView.findViewById(R.id.tv_orderlistitem_route);
                orderRecordHolder.tv_orderlistitem_daytime= (TextView) convertView.findViewById(R.id.tv_orderlistitem_daytime);
                orderRecordHolder.tv_orderlistitem_days= (TextView) convertView.findViewById(R.id.tv_orderlistitem_days);
                orderRecordHolder.tv_orderlistitem_passengers= (TextView) convertView.findViewById(R.id.tv_orderlistitem_passengers);
                orderRecordHolder.tv_orderlistitem_cartype= (TextView) convertView.findViewById(R.id.tv_orderlistitem_cartype);
                orderRecordHolder.tv_orderlistitem_ordermoney= (TextView) convertView.findViewById(R.id.tv_orderlistitem_ordermoney);
                orderRecordHolder.tv_orderlistitem_customer_phone= (TextView) convertView.findViewById(R.id.tv_orderlistitem_customer_phone);
                orderRecordHolder.tv_orderlistitem_customer= (TextView) convertView.findViewById(R.id.tv_orderlistitem_customer);
                orderRecordHolder.but_orderlistitem_callphone= (Button) convertView.findViewById(R.id.but_orderlistitem_callphone);
                convertView.setTag(orderRecordHolder);
            }else{
                orderRecordHolder= (OrderRecordHolder) convertView.getTag();
            }
            orderRecordHolder.tv_orderlistitem_title.setText(order_lists.get(position).getTitle()+"  "+order_lists.get(position).getTitledescrible());
            orderRecordHolder.tv_orderlistitem_route.setText("途径："+order_lists.get(position).getRoute());
            orderRecordHolder.tv_orderlistitem_daytime.setText(order_lists.get(position).getStart_date()+"-"+order_lists.get(position).getEnd_date());
            orderRecordHolder.tv_orderlistitem_days.setText("共"+order_lists.get(position).getDays()+"天");
            orderRecordHolder.tv_orderlistitem_passengers.setText("乘客数： "+order_lists.get(position).getPassengers()+"人");
            orderRecordHolder.tv_orderlistitem_ordermoney.setText("￥"+order_lists.get(position).getTotal_price());
            orderRecordHolder.tv_orderlistitem_cartype.setText("("+order_lists.get(position).getCar_type()+")");
            orderRecordHolder.tv_orderlistitem_customer_phone.setText(order_lists.get(position).getCustomer_phone());
            orderRecordHolder.tv_orderlistitem_customer.setText(""+order_lists.get(position).getCustomer());
            orderRecordHolder.but_orderlistitem_callphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //用intent启动拨打电话
                    String telphone="tel:"+orderRecordHolder.tv_orderlistitem_customer_phone.getText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(telphone));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
            });

            return convertView;
        }
    }

    class OrderRecordHolder {
        TextView tv_orderlistitem_title;
        TextView tv_orderlistitem_route;
        TextView tv_orderlistitem_days;
        TextView tv_orderlistitem_daytime;
        TextView tv_orderlistitem_passengers;
        TextView tv_orderlistitem_ordermoney;
        TextView tv_orderlistitem_cartype;
        TextView tv_orderlistitem_customer;
        TextView tv_orderlistitem_customer_phone;
        Button but_orderlistitem_callphone;
    }




}
