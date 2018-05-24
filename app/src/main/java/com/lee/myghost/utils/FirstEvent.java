package com.lee.myghost.utils;

import com.lee.myghost.mvp.model.bean.VideoListBean;

import java.util.List;

public class FirstEvent {

    private List<VideoListBean.RetBean.ListBean.ChildListBean> childList;

    public FirstEvent(List<VideoListBean.RetBean.ListBean.ChildListBean> childList) {
        this.childList = childList;
    }

    public List<VideoListBean.RetBean.ListBean.ChildListBean> getChildList() {
        return childList;
    }

    public void setChildList(List<VideoListBean.RetBean.ListBean.ChildListBean> childList) {
        this.childList = childList;
    }

    public static class ChildListBean {
        /**
         * airTime : 0
         * duration : 01:31:25
         * loadtype : video
         * score : 0
         * angleIcon : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/05/09/1494296586400001011.png
         * dataId : a11f88f705d64bf2bf2409a213e5316c
         * description : @TV香港大片：神探马如龙由陈嘉上执导的喜剧片，郑丹瑞、关之琳、李子雄、利智参加演出。讲述了一个无名小卒马如龙的人生逆袭之路。
         * loadURL : http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=a11f88f705d64bf2bf2409a213e5316c
         * shareURL : http://h5.svipmovie.com/xgdp/a11f88f705d64bf2bf2409a213e5316c.shtml?fromTo=shoujimovie
         * pic : http://phonemovie.ks3-cn-beijing.ksyun.com/image/2017/08/31/1504172437255072265.jpg
         * title : 神探马如龙
         */

        private int airTime;
        private String duration;
        private String loadtype;
        private int score;
        private String angleIcon;
        private String dataId;
        private String description;
        private String loadURL;
        private String shareURL;
        private String pic;
        private String title;

        public int getAirTime() {
            return airTime;
        }

        public void setAirTime(int airTime) {
            this.airTime = airTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLoadtype() {
            return loadtype;
        }

        public void setLoadtype(String loadtype) {
            this.loadtype = loadtype;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAngleIcon() {
            return angleIcon;
        }

        public void setAngleIcon(String angleIcon) {
            this.angleIcon = angleIcon;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLoadURL() {
            return loadURL;
        }

        public void setLoadURL(String loadURL) {
            this.loadURL = loadURL;
        }

        public String getShareURL() {
            return shareURL;
        }

        public void setShareURL(String shareURL) {
            this.shareURL = shareURL;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}  