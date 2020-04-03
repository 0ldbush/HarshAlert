package com.alnt.platform.application.logger.service;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.base.request.RequestDetails;

public class LoggerImpl implements Logger {

	AppLogService appLogService;
	private org.slf4j.Logger log;

	@Inject
	public LoggerImpl(AppLogService appLogService, String classz) {
		super();
		this.appLogService = appLogService;
		this.log = LoggerFactory.getLogger(classz);
	}

	@Override
	public void db(RequestDetails requestDetails, AppLogDTO appLog) {
//		System.out.println("in db");
		if(StringUtils.isBlank(appLog.getBusObjCat()))
		appLog.setBusObjCat(log.getName());
		appLogService.save(requestDetails, appLog);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return log.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return log.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		log.trace(msg);

	}

	@Override
	public void trace(String format, Object arg) {
		log.trace(format, arg);

	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		log.trace(format, arg1, arg2);

	}

	@Override
	public void trace(String format, Object... arguments) {
		log.trace(format, arguments);

	}

	@Override
	public void trace(String msg, Throwable t) {
		log.trace(msg, t);

	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void debug(String msg) {
		log.debug(msg);

	}

	@Override
	public void debug(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void debug(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void info(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void info(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(String msg) {
		log.error(msg);

	}

	@Override
	public void error(String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(Marker marker, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		// TODO Auto-generated method stub

	}

}
