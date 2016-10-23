package com.lazy.gank.network;

import com.lazy.gank.entity.ContentResult;
import com.lazy.gank.entity.DayGankResults;
import com.lazy.gank.entity.GankResult;
import com.lazy.gank.entity.GankResults;
import com.lazy.gank.entity.GankSearchResults;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lazy on 16/8/14.
 */
public interface GankService {
	/**
	 * 搜索 API
	 * <p>
	 * http://gank.io/api/search/query/listview/category/Android/count/10/page/1
	 * 注：
	 * category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
	 * count 最大 50
	 *
	 * @return
	 */
	@GET("api/search/query/{keywords}/category/{category}/count/{count}/page/{page}")
	Observable<GankSearchResults> search(
			@Path("keywords") String keywords,
			@Path("category") String category,
			@Path("count") int count,
			@Path("page") int page
	);

	/**
	 * 获取某几日干货网站数据:
	 * <p>
	 * http://gank.io/api/history/content/2/1
	 * <p>
	 * 注： 2 代表 2 个数据，1 代表：取第一页数据
	 *
	 * @param count
	 * @param page
	 * @return
	 */
	@GET("api/history/content/count/{count}/page/{page}")
	Observable<ContentResult> getHistoty(
			@Path("count") int count,
			@Path("page") int page
	);

	/**
	 * 获取特定日期网站数据:
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@GET("api/history/content/day/{year}/{month}/{day}")
	Observable<ContentResult> getHistotyByDay(
			@Path("year") String year,
			@Path("month") String month,
			@Path("day") String day
	);


	/**
	 * 获取发过干货日期接口:
	 *
	 * @return
	 */
	@GET("api/day/history")
	Observable<GankResults<String>> getDayHistoty();

	/**
	 * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
	 * <p>
	 * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
	 * 请求个数： 数字，大于0
	 * 第几页：数字，大于0
	 *
	 * @param category
	 * @param count
	 * @param page
	 * @return
	 */
	@GET("api/data/{category}/{count}/{page}")
	Observable<GankResults<GankResult>> getCategoryData(
			@Path("category") String category,
			@Path("count") int count,
			@Path("page") int page
	);

	/**
	 * 获取特定日期的gank
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@GET("api/day/{year}/{month}/{day}")
	Observable<DayGankResults> getGankByDate(
			@Path("year") String year,
			@Path("month") String month,
			@Path("day") String day);
}
