package com.zouzhe.walkingapp.main_fragment;

import android.app.Activity;
import android.view.View;

import com.zouzhe.walkingapp.R;

/**
 * Created by Administrator on 2015/3/2.
 */
public class NowCarpooling_fragment extends BaseFragment {
    
	public NowCarpooling_fragment(Activity activity) {
		super(activity);
	}

	@Override
    public View initview() {
        View view=View.inflate(mcontext, R.layout.nowcarpooling_fragment,null);
        return view;
    }

    @Override
    public void initdata() {

    }
}
