package com.vix.test;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileMoveWithCamel {
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {
				/*from("quartz://report?cron=10 * * * * ?&stateful=true").to("com.e6soft.vreport.processor.EmailProcessor");*/
				from("timer://timer1?period=1000").to("com.e6soft.vreport.processor.EmailProcessor");
			}
		});
		context.start();
	}
}