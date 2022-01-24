package lambda.service;

import common.vo.PeopleVO;

public interface PeopleConstruct {
    PeopleVO createPeople(String name, int age, int sexy);
}
