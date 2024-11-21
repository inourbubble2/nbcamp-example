package com.navi.nbcampauthenticationfilterexample.authenticated;

import com.navi.nbcampauthenticationfilterexample.entity.UserRole;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authenticated {

    UserRole role();

}
