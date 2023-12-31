package com.example.amirul.cafefoodorderingserver.Common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.amirul.cafefoodorderingserver.Model.Request;
import com.example.amirul.cafefoodorderingserver.Model.User;
import com.example.amirul.cafefoodorderingserver.Remote.APIService;
import com.example.amirul.cafefoodorderingserver.Remote.FCMRetrofitClient;
import com.example.amirul.cafefoodorderingserver.Remote.IGeoCoordinates;
import com.example.amirul.cafefoodorderingserver.Remote.RetrofitClient;

import java.util.Calendar;
import java.util.Locale;

public class Common {

    public static final String SHIPPER_TABLE ="Shippers" ;
    public static final String ORDER_NEED_SHIP_TABLE = "OrdersNeedShip";
    public static final String Staff_TABLE ="User" ;


    public static User currentUser;
    public static Request currentRequest;

    public static String DISTANCE= "";
    public static String DURATION= "";
    public static String ESTIMATED_TIME = "";


    public static String topicName = "News";

    public static String PHONE_TEXT = "userPhone";

    public static final String INTENT_ACCOUNT = "Account";
    public static final String INTENT_FOOD_ID = "FoodId";


    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final String REMOVE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String baseURL = "https://maps.googleapis.com/";
    public static final String fcmURL =  "https://fcm.googleapis.com/";


    public static String convertCodeToStatus(String code){

        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "Preparing Orders";
        else if(code.equals("2"))
            return "Shipping";
        else
            return "Delivered";
    }

    public static String convertRole(String code){
        if (code.equals("true"))
            return "Role:Staff";
        else if (code.equals("false"))
            return "Role:User";
        else if (code.equals("yes"))
            return "Role:Admin";
        else if (code.equals("no"))
            return "Role:Not Admin";
        else if(code.equals(null))
            return "Role:User";
        else
            return "Role:User";
    }

    public static APIService getFCMClient(){
        return FCMRetrofitClient.getClient(fcmURL).create(APIService.class);
    }

    public static IGeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseURL).create(IGeoCoordinates.class);
    }

    public static boolean isConnectedToInternet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null){

                for(int i=0; i<info.length; i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight){

        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth,newHeight,Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float)bitmap.getWidth();
        float scaleY = newHeight / (float)bitmap.getHeight();
        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    public static String getDate(long time)
    {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        StringBuilder date = new StringBuilder(android.text.format.DateFormat.format("dd-MM-yyyy HH:mm"
                , calendar).toString());
        return date.toString();
    }
}
