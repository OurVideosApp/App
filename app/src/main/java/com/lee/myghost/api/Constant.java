package com.lee.myghost.api;

/**
 * @author Lee
 * @create_time 2018/5/16 20:24
 * @description 存放路径
 */
public class Constant {
    public static       int    requestNum = 10;
    public static       int    pageNum    = 1;
    //HOST1
    public static final String BASE_URL1  = "http://api.svipmovie.com/front/";

    //首页
    public static final String HOME_PAGE_URL = "homePageApi/homePage.do";

    //影片详情
    public static final String VIDEO_DETAIL_URL = "videoDetailApi/videoDetail.do";

    //影片分类列表
    public static final String VIDEO_CATEGORY_URL = "videoDetailApi/videoDetail.do";

    //影片搜索
    public static final String SEARCH_VIDEO_URL = "videoDetailApi/videoDetail.do";

    //获取评论列表
    public static final String GET_COMMENT_URL = "videoDetailApi/videoDetail.do";


    //HOST2   福利
    public static final String BASE_URL2             = "http://gank.io/api/";
    //福利列表
    public static final String MATERIAL_BENEFITS_URL = "data/福利/" + requestNum + "/" + pageNum;

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * •数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * •请求个数： 数字，大于0
     * •第几页：数字，大于0
     * <p>
     * 例：
     *     •http://gank.io/api/data/Android/10/1
     *     •http://gank.io/api/data/福利/10/1
     *     •http://gank.io/api/data/iOS/20/2
     *     •http://gank.io/api/data/all/20/2
     * <p>
     * 每日数据： http://gank.io/api/day/年/月/日
     * <p>
     * 例：
     * •http://gank.io/api/day/2015/08/06
     * <p>
     * 随机数据：http://gank.io/api/random/data/分类/个数
     * •数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     * •个数： 数字，大于0
     * <p>
     * 例：•http://gank.io/api/random/data/Android/20
     *
     * http://www.finalshares.com/read-608
     */


}
