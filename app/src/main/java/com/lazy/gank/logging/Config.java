package com.lazy.gank.logging;

class Config {
	// 存放日志文件的目录全路径
	public String logSavePath = "";
	public Character logCatLogLevel;
	public Character fileLogLevel;

	public Config(Builder builder) {
		this.logSavePath = builder.logSavePath;
		this.logCatLogLevel = builder.logCatLogLevel;
		this.fileLogLevel = builder.fileLogLevel;
	}


}