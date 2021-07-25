package com.examly.BloodBank.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BloodBankAppInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class configFiles[] = {BloodBankAppConfig.class};
		return configFiles;
	}

	@Override
	protected String[] getServletMappings() {
		String mappings[] = {"/"};
		return null;
	}

}
