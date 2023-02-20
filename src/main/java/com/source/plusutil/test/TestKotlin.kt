package com.source.plusutil.test

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TestKotlin {

    @Id
    val id :Long?=null;
    val num :Int = 0;

    fun numPlusOne(): Long? {
        return this.id;
    }
}