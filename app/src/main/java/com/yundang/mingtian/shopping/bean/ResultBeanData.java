package com.yundang.mingtian.shopping.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-11-16.
 */

public class ResultBeanData {

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {


        private SeckillInfoBean seckill_info;
        private TravelInfoBean travel_info;
        private List<ActInfoBean> act_info;
        private List<BannerInfoBean> banner_info;
        private List<ChannelInfoBean> channel_info;
        private KanghavelInfoBean kanghavel_info;
        private List<RecommendInfoBean> recommend_info;
        private SPInfoBean sp_info;
        private IntegrationBean integration_info;
        private HealthcareInfoBean healthcare_info;

        public HealthcareInfoBean getHealthcare_info() {
            return healthcare_info;
        }

        public void setHealthcare_info(HealthcareInfoBean healthcare_info) {
            this.healthcare_info = healthcare_info;
        }

        public IntegrationBean getIntegration_info() {
            return integration_info;
        }

        public void setIntegration_info(IntegrationBean integration_info) {
            this.integration_info = integration_info;
        }

        public SPInfoBean getSp_info() {
            return sp_info;
        }

        public KanghavelInfoBean getKanghavel_info() {
            return kanghavel_info;
        }

        public void setKanghavel_info(KanghavelInfoBean kanghavel_info) {
            this.kanghavel_info = kanghavel_info;
        }

        public void setSp_info(SPInfoBean sp_info) {
            this.sp_info = sp_info;
        }

        public TravelInfoBean getTravel_info() {
            return travel_info;
        }

        public void setTravel_info(TravelInfoBean travel_info) {
            this.travel_info = travel_info;
        }

        public SeckillInfoBean getSeckill_info() {
            return seckill_info;
        }

        public void setSeckill_info(SeckillInfoBean seckill_info) {
            this.seckill_info = seckill_info;
        }

        public List<ActInfoBean> getAct_info() {
            return act_info;
        }

        public void setAct_info(List<ActInfoBean> act_info) {
            this.act_info = act_info;
        }

        public List<BannerInfoBean> getBanner_info() {
            return banner_info;
        }

        public void setBanner_info(List<BannerInfoBean> banner_info) {
            this.banner_info = banner_info;
        }

        public List<ChannelInfoBean> getChannel_info() {
            return channel_info;
        }

        public void setChannel_info(List<ChannelInfoBean> channel_info) {
            this.channel_info = channel_info;
        }



        public List<RecommendInfoBean> getRecommend_info() {
            return recommend_info;
        }

        public void setRecommend_info(List<RecommendInfoBean> recommend_info) {
            this.recommend_info = recommend_info;
        }









        public static class IntegrationBean {
            private String Integration_product_list;
            private List<ListBean> list;

            public String getIntegration_product_list() {
                return Integration_product_list;
            }

            public void setIntegration_product_list(String integration_product_list) {
                Integration_product_list = integration_product_list;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String price;
                private String figure;
                private String name;
                private String product_id;
                private String remark;
                private String product_type;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

            }


        }







        public static class SeckillInfoBean {

            private String end_time;
            private String start_time;
            private String seckill_product_list;
            private List<ListBean> list;

            public String getSeckill_product_list() {
                return seckill_product_list;
            }

            public void setSeckill_product_list(String seckill_product_list) {
                this.seckill_product_list = seckill_product_list;
            }




            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {

                private String cover_price;
                private String figure;
                private String name;
                private String origin_price;
                private Integer product_id;
                private String remark;

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

                public Integer getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(Integer product_id) {
                    this.product_id = product_id;
                }

                private String product_type;

                public String getCover_price() {
                    return cover_price;
                }

                public void setCover_price(String cover_price) {
                    this.cover_price = cover_price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOrigin_price() {
                    return origin_price;
                }

                public void setOrigin_price(String origin_price) {
                    this.origin_price = origin_price;
                }

            }
        }



        public static class TravelInfoBean {
            private String travel_product_list;
            private List<ListBean> list;
            public String getTravel_product_list() {
                return travel_product_list;
            }

            public void setTravel_product_list(String travel_product_list) {
                this.travel_product_list = travel_product_list;
            }

            public List<ListBean> getList() {
                return list;
            }
            public void setList(List<ListBean> list) {
                this.list = list;
            }
            public static class ListBean {
                private String price;
                private String figure;
                private String name;
                private String product_type;
                private Integer product_id;
                private String remark;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

                public Integer getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(Integer product_id) {
                    this.product_id = product_id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }
        }

        public static class KanghavelInfoBean {
            private String kanghave_product_list;
            private List<ListBean> list;

            public String getKanghave_product_list() {
                return kanghave_product_list;
            }

            public void setKanghave_product_list(String kanghave_product_list) {
                this.kanghave_product_list = kanghave_product_list;
            }

            public List<ListBean> getList() {
                return list;
            }
            public void setList(List<ListBean> list) {
                this.list = list;
            }
            public static class ListBean {
                private String price;
                private String figure;
                private String name;
                private String product_type;
                private Integer product_id;
                private String remark;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

                public Integer getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(Integer product_id) {
                    this.product_id = product_id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }
        }







        public static class HealthcareInfoBean {
            private String healthcare_info;
            private List<ListBean> list;

            public String getHealthcare_info() {
                return healthcare_info;
            }

            public void setHealthcare_info(String healthcare_info) {
                this.healthcare_info = healthcare_info;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String price;
                private String figure;
                private String name;
                private String product_id;
                private String remark;
                private String product_type;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

            }


        }








        public static class ActInfoBean {


            private String icon_url;
            private String name;
            private String url;

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class BannerInfoBean {
            private String url;
            private Integer goodId;
            private String goodPrice;
            private String goodName;
            private String figure;
            private String spec;
            private String image;
            private String option;
            private int type;
            public Integer getGoodId() {
                return goodId;
            }

            public void setGoodId(Integer goodId) {
                this.goodId = goodId;
            }

            public String getGoodPrice() {
                return goodPrice;
            }

            public void setGoodPrice(String goodPrice) {
                this.goodPrice = goodPrice;
            }

            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChannelInfoBean {
            private Integer channel_id;
            private String channel_name;
            private String image;
            private String option;
            private int type;
            public Integer getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(Integer channel_id) {
                this.channel_id = channel_id;
            }

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

        }

        public static class SPInfoBean {
            private String sp_product_list;
            private List<ListBean> list;

            public String getSp_product_list() {
                return sp_product_list;
            }

            public void setSp_product_list(String sp_product_list) {
                this.sp_product_list = sp_product_list;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String price;
                private String figure;
                private String name;
                private String product_id;
                private String remark;
                private String product_type;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getProduct_type() {
                    return product_type;
                }

                public void setProduct_type(String product_type) {
                    this.product_type = product_type;
                }

            }


        }

        public static class RecommendInfoBean {

            private String cover_price;
            private String figure;
            private String name;
            private String product_id;

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
        }
    }
}
