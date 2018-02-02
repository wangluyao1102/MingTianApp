package com.yundang.mingtian.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yundang.mingtian.R;
import com.yundang.mingtian.util.UIUtils;


/**
 * Created by Administrator on 2017-12-30.
 */
public class CartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View view = View.inflate(getActivity(), R.layout.fragment_more, null);
        View view = UIUtils.getView(R.layout.fragment_cart);
        return view;
    }
}
