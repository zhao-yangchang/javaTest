package zyc.javaTest.learn.lambda;

import zyc.javaTest.learn.common.vo.AnimalVO;
import zyc.javaTest.learn.common.vo.PeopleVO;
import zyc.javaTest.learn.lambda.service.PeopleConstruct;
import zyc.javaTest.learn.lambda.service.TConstruct;

public class VOContructTest {

    public static void main(String[] args) {
        PeopleConstruct construct = (name, age, sexy) -> new PeopleVO(name, age, sexy);
        PeopleVO peopleVO = construct.createPeople("zhaosi", 15, 1);
        System.out.println("people: " + peopleVO.toString());

        PeopleConstruct construct1 = PeopleVO::new;
        PeopleVO peopleVO1 = construct1.createPeople("liliu", 15, 1);
        System.out.println(peopleVO1);

        PeopleConstruct construct2 = VOContructTest::getPeopleVO;
        PeopleVO peopleVO3 = construct2.createPeople("liliu", 15, 1);

        TConstruct<PeopleVO> tConstruct = () -> new PeopleVO("wangwu", 15, 2);
        PeopleVO peopleVO2 = tConstruct.create();
        System.out.println("people: " + peopleVO2.toString());

        TConstruct<AnimalVO> animalVOTConstruct = AnimalVO::new;
        AnimalVO animalVO = animalVOTConstruct.create();
        System.out.println(animalVO);


    }

    public static PeopleVO getPeopleVO(String name, int age, int sexy) {
        return new PeopleVO(name, age, sexy);
    }

}
