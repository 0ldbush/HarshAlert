package com.alnt.platform.application;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.alnt.policyengine.service.RuleService;
import com.google.inject.Singleton;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

@Singleton
public class Startup {
	private final ActorSystem actorSystem;
	private final ExecutionContext executionContext;
	
	
	private RuleService ruleService;
	@Inject
	public Startup(ActorSystem actorSystem, ExecutionContext executionContext,RuleService ruleService) {
		super();
		this.actorSystem = actorSystem;
	    this.executionContext = executionContext;
	    this.ruleService=ruleService;
		this.init();
	}
	
	private void init() {
		this.actorSystem.scheduler().scheduleOnce(Duration.create(5, TimeUnit.SECONDS), // delay
				() -> {
					ruleService.loadCache();

				}, this.executionContext);
	}

}
