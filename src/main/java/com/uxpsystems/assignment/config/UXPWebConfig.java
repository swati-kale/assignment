package com.uxpsystems.assignment.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configurable
@EnableWebMvc
@ComponentScan(basePackages = { "com.uxpsystems.assignment.controller" })
public class UXPWebConfig extends WebMvcConfigurerAdapter {


}
