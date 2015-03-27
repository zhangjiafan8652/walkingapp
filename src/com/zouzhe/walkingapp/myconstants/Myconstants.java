package com.zouzhe.walkingapp.myconstants;

/**
 * Created by Administrator on 2015/3/3.
 */
public class Myconstants {
    public static String driver_id="12345";


    // sp中登陆状态的字段LOGINSTATE
    public static int NEVERLOGIN = 0;
    public static int HADLOGIN = 1;

    //请求本地ip
    public static String url="http://192.168.1.119/zouzhe/";
    //我的服务器
    public static String myurl="http://dongmanrikan.duapp.com/";
    //请求订单url
    public static String orderurl=url+"servlet/zouzhelvxingceshi";
    //请求司机路线url
    public static String routeurl=url+"servlet/zouzheceshisijixianlu";
    //请求地点集合
    public static String location=url+"servlet/zouzhehuoqudidian";



    // 友盟的状态UMENGSTATE,sp中的是umengstatus
    public static int UMENGCLOSE = 0;
    public static int UMENGOPEN = 1;

    // 域名
    public static String URL = "http://api.zouzhe.com";
   // public static String URL ="http://api.66hao.com";
    // 1.请求发送验证码url
    public static String SENDverify_codeURL = URL
            + "/wechat/api/driver/valid_code";

    // 2. 提注册交验证码url
    public static String SUBMITVERIFYURLZHUCE = URL
            + "/wechat/api/driver/register";
    // SUBMITVERIFYURL="http://10.0.3.2/zouzhe1/servlet/LoginServlet";

    // 3.提交司机资料的Url
    public static String SIJIZILIAOURL = URL
            + "/wechat/api/driver/complete_driver_file";

    // 4.和7. 提交图片的url
    public static String SUBMITTUPIANURL = URL
            + "/wechat/api/driver/save_single_picture";

    // 12.提交推荐人
    public static String JIESHAOREN = URL
            + "/wechat/api/driver/driver_recommend";

    // 5.提登陆交验证码url/wechat/api/driver/driver_login
    public static String SUBMITVERIFYURLLONGIN = URL
            + "/wechat/api/driver/driver_login";

    // 6 接收司机详情资料post
    public static String RECIVERZILIAO = URL + "/wechat/api/driver/driver_info";

    // 8.提交司机修改详情资料
    public static String SUMITSIJIZILIAO = URL
            + "/wechat/api/driver/driver_edit";

    // /wechat/api/driver/upload_vehicles_image
    // 9 修改司机资料中修改车辆图片的url
    public static String CHELIANGTUPIAN = URL
            + "/wechat/api/driver/upload_vehicles_image";

    // 10.司机订单url
    public static String ORDERURL = URL
            + "/wechat/api/driver/driver_order_list";

    // http://zhangjiafan.oss-cn-shenzhen.aliyuncs.com/zouzhelvxing/nihao.json
    // /wechat/api/driver/driver_recommend

    // 13. 司机个人中心
    public static String PERSONZILIAO = URL
            + "/wechat/api/driver/driver_center";

    // 14.获取司机档期
    public static String DATE = URL + "/wechat/api/driver/driver_schedules";

    // 15上传保存司机档期
    public static String SAVESCHURL = URL
            + "/wechat/api/driver/driver_schedule_edit";

    // 16获取小车对应的id
    public static String CARTYPEURL = URL + "/wechat/api/common/vehicle";

    // 17获取小车对应的id
    public static String LOCATIONURL = URL
            + "/wechat/api/driver/driver_position";

    // 百度云司机详情的json文件地址
    public static String CESHIALIYUN = "http://zhangjiafan.oss-cn-shenzhen.aliyuncs.com/zouzhelvxing/nihao.json";

    // get
    // http://192.168.1.132/wechat/api/driver/driver_schedules?driver_id=54aa6e63c508d715afd721d8
    public static String SIJIZILIAOURLGET = URL
            + "/wechat/api/driver/driver_info?driver_id=";
    public static String RECIVEDATE = URL
            + "/wechat/api/driver/driver_schedules";

    // 百度云司机订单的json文件地址上拉刷新
    public static String CESHIDINGDAN = "http://zhangjiafan.oss-cn-shenzhen.aliyuncs.com/zouzhelvxing/nihao.json";
    // 下拉加载
    public static String CESHIALIYUNXIA = URL
            + "/wechat/api/driver/driver_order_list";

    public static String EXIT_ACTION = "ex";

}
