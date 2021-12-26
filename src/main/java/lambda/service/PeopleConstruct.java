package lambda.service;

import common.VO.PeopleVO;

public interface PeopleConstruct {
    PeopleVO createPeople(String name, int age, int sexy);
}
