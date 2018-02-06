package com.yundang.mingtian.shopping.adapter;

import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.yundang.mingtian.R;
import com.yundang.mingtian.common.AppNetConfig;

import com.yundang.mingtian.shopping.bean.ResultBeanData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018-01-30.
 */

public class ShoppingFragmentAdapter extends RecyclerView.Adapter {

    //广告条幅
    public static final int BANNER = 0;//使用Banner控件
    //商品分类
    public static final int CHANNEL = 1;//使用Grid控件
    //团购
    public static final int ACT = 2; //使用RecyclerView控件
    //旅游
    public static final int TRAVEL = 3; //使用RecyclerView控件
    //优品
    public static final int SP = 4; //使用GridView控件
    //康养
    public static final int KANGHAVE = 5; //使用RecyclerView控件
    //积分
    public static final int INTEGRATION = 6; //使用GridView控件
    //养生
    public static final int HEALTHCARE = 7; //使用GridView控件


    //当前类型
    private int currentType = BANNER;
    private LayoutInflater mLayoutInflater;//初始化布局 与View.inflate()一样
    Context mContext;
    ResultBeanData.ResultBean resultBean;


    /**
     * 模块一幻灯片
     ***/
    //实体化Banner(幻灯片)
    class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;
        public Context mContext;


        public BannerViewHolder(View itemView, Context context) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            this.mContext = context;

        }

        public void setDate(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
            //得到图片地址集合
            List<String> imagersUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagersUrl.add(imageUrl);
            }

            //设置banner手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            //设置循环指示器
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片glide
                    String s = AppNetConfig.BASE_URL_IMG+ url;
                    System.out.println(s);
                    Glide.with(mContext).load(s).into(view);
                }
            });
            //设置banner的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (position - 1 < banner_info.size()) {
                        int id = banner_info.get(position - 1).getGoodId();
                        System.out.println(id);
                    }
                }


            });
        }
    }


    /**************************
     * 子模块二商品栏目分类的实现
     ***********************************************************/
//实例化GridView(频道)
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(View itemView, Context context) {
            super(itemView);
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
            this.mContext = context;
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据设置GridView适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
            //设置item点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    /**************************
     * 子模块三明天团购的实现
     ***********************************************************/
    private TextView tv_time_seckill;
    private long dt = 0;//秒杀相差时间(单位:毫秒)
    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tv_time_seckill.setText(sd.format(new Date(dt)));
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt <= 0) {
                    tv_time_seckill.setText("00:00:00");
                    handler.removeMessages(0);
                }
            }
        }
    };


    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView recyclerView;

        public SeckillViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean data) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, data);
            recyclerView.setAdapter(adapter);
            //秒杀倒计时(单位毫秒)
            dt = Integer.valueOf(data.getEnd_time()) - Integer.valueOf(data.getStart_time());
            handler.sendEmptyMessageAtTime(0, 1000);
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onClick(int position) {
                    System.out.println(position);
                }
            });


        }

    }


    /**************************
     * 子模块四旅游专区
     ***********************************************************/


    class TravelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView recyclerView;

        public TravelViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_travel);

        }

        public void setData(final ResultBeanData.ResultBean.TravelInfoBean data) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            TravelRecyclerViewAdapter adapter = new TravelRecyclerViewAdapter(mContext, data);
            recyclerView.setAdapter(adapter);
        }

    }

    /**************************
     * 子模块五优品专区
     ***********************************************************/
    class SPViewHolder  extends RecyclerView.ViewHolder{

        private Context mContext;
        private GridView sp;

        public SPViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            sp = (GridView) itemView.findViewById(R.id.gv_sp_list);
        }


        public void setData(ResultBeanData.ResultBean.SPInfoBean data) {
            SPGridViewAdapter adapter = new SPGridViewAdapter(mContext, data);
            sp.setAdapter(adapter);

            //点击事件
            sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
    /**************************
     * 子模块六康养专区
     ***********************************************************/
    class KangHaveViewHolder  extends RecyclerView.ViewHolder{

        private Context mContext;
        private RecyclerView recyclerView;
        public KangHaveViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_kanghave);
        }
        public void setData(final ResultBeanData.ResultBean.KanghavelInfoBean data) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            KangHaveViewAdapter adapter = new KangHaveViewAdapter(mContext, data);
            recyclerView.setAdapter(adapter);
            adapter.setOnKangHaveRecyclerView(new KangHaveViewAdapter.OnKangHaveRecyclerView() {
                @Override
                public void onClick(int position) {
                    Toast.makeText(mContext,"Postion:"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**************************
     * 子模块七积分商城
     ***********************************************************/
    class IntegrationViewHolder  extends RecyclerView.ViewHolder{

        private Context mContext;
        private GridView sp;

        public IntegrationViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            sp = (GridView) itemView.findViewById(R.id.gv_sp_list);
        }


        public void setData(ResultBeanData.ResultBean.IntegrationBean data) {
            IntegrationGridViewAdapter adapter = new IntegrationGridViewAdapter(mContext, data);
            sp.setAdapter(adapter);

            //点击事件
            sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
    /**************************
     * 子模块八养生地产
     ***********************************************************/
    class HealthcareViewHolder  extends RecyclerView.ViewHolder{

        private Context mContext;
        private RecyclerView recyclerView;


        public HealthcareViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_healthcare);
        }


        public void setData(final ResultBeanData.ResultBean.HealthcareInfoBean data) {



            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            HealthcareRecyclerViewAdapter adapter = new HealthcareRecyclerViewAdapter(mContext, data);
            recyclerView.setAdapter(adapter);
            adapter.setOnHealthcareRecyclerView(new HealthcareRecyclerViewAdapter.OnHealthcareRecyclerView() {
                @Override
                public void onClick(int position) {
                    Toast.makeText(mContext,"Postion:"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }











    public ShoppingFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }









    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext);
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, null), mContext);
        } else if (viewType == ACT) {
            return new SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, null), mContext);
        } else if (viewType == TRAVEL) {
            return new TravelViewHolder(mLayoutInflater.inflate(R.layout.travel_item, null), mContext);
        }else if (viewType == SP) {
            return new SPViewHolder(mLayoutInflater.inflate(R.layout.sp_item, null), mContext);
        }else if (viewType == KANGHAVE) {
            return new KangHaveViewHolder(mLayoutInflater.inflate(R.layout.kanghave_item, null), mContext);
        }else if (viewType == INTEGRATION) {
            return new IntegrationViewHolder(mLayoutInflater.inflate(R.layout.integration_item, null), mContext);
        }else if (viewType == HEALTHCARE) {
            return new HealthcareViewHolder(mLayoutInflater.inflate(R.layout.healthcare_item, null), mContext);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setDate(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(position) == TRAVEL) {
            TravelViewHolder travelViewHolder = (TravelViewHolder) holder;
            travelViewHolder.setData(resultBean.getTravel_info());
        } else if (getItemViewType(position) == SP) {
            SPViewHolder spViewHolder = (SPViewHolder) holder;
            spViewHolder.setData(resultBean.getSp_info());
        }else if (getItemViewType(position) == KANGHAVE) {
            KangHaveViewHolder kangHaveViewHolder = (KangHaveViewHolder) holder;
            kangHaveViewHolder.setData(resultBean.getKanghavel_info());
        }else if (getItemViewType(position) == INTEGRATION) {
            IntegrationViewHolder integrationViewHolder = (IntegrationViewHolder) holder;
            integrationViewHolder.setData(resultBean.getIntegration_info());
        }else if (getItemViewType(position) == HEALTHCARE) {
            HealthcareViewHolder healthcareViewHolder = (HealthcareViewHolder) holder;
            healthcareViewHolder.setData(resultBean.getHealthcare_info());
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case TRAVEL:
                currentType = TRAVEL;
                break;
            case SP:
                currentType = SP;
                break;
           case KANGHAVE:
                currentType = KANGHAVE;
                break;
            case INTEGRATION:
                currentType = INTEGRATION;
                break;
            case HEALTHCARE:
                currentType = HEALTHCARE;
                break;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
