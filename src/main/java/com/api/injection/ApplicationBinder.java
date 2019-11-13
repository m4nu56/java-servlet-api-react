package com.api.injection;

import com.api.business.UserDao;
import com.api.business.UserSvc;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class ApplicationBinder extends AbstractBinder {
	@Override
	protected void configure() {
		bind(UserSvc.class).to(UserSvc.class);
		bind(UserDao.class).to(UserDao.class);
		//		bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
		bind(CurrentUserInjectionResolver.class)
			.to(new TypeLiteral<InjectionResolver<CurrentUser>>() {})
			.in(Singleton.class);
	}
}
