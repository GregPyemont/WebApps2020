package com.grwp20.WebCoursework.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T05:32:15")
@StaticMetamodel(TransactionRecords.class)
public class TransactionRecords_ { 

    public static volatile SingularAttribute<TransactionRecords, String> amount;
    public static volatile SingularAttribute<TransactionRecords, String> receiver;
    public static volatile SingularAttribute<TransactionRecords, String> sender;
    public static volatile SingularAttribute<TransactionRecords, Boolean> accepted;
    public static volatile SingularAttribute<TransactionRecords, Long> id;
    public static volatile SingularAttribute<TransactionRecords, Boolean> completed;

}