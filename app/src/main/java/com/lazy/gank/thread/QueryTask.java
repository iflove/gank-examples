package com.lazy.gank.thread;


import com.lazy.gank.util.DeviceUtil;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueryTask {
	public static final String httpHeader = "Dalvik/1.6.0 ("
			+ DeviceUtil.getPkgName() + " " + DeviceUtil.getVersionName()
			+ "; U; Android "
			+ android.os.Build.VERSION.RELEASE
			+ ";"
			+ android.os.Build.MODEL
			+ " Build/"
			+ android.os.Build.ID + ")";

	private static Map<String, String> headers = null;

	//获取当前系统的CPU 数目 ,ExecutorService通常根据系统资源情况灵活定义线程池大小
//	private final static SynchronousQueue<Runnable> queue = new SynchronousQueue<Runnable>() ;
//	private final static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100) ;

	//	public final static RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
//	public final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS, queue,handler);
	public final static ExecutorService executorService = Executors.newCachedThreadPool();

	/**
	 * 创建一个单线程的线程池 适合高并发
	 */
//	private static final ExecutorService workerThread = Executors.newSingleThreadExecutor();

}