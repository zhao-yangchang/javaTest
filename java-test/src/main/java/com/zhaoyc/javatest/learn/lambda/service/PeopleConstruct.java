package com.zhaoyc.javatest.learn.lambda.service;

import com.zhaoyc.javatest.learn.common.vo.PeopleVO;

public interface PeopleConstruct {
    PeopleVO createPeople(String name, int age, int sexy);
}
