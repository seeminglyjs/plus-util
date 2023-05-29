package com.source.plusutil.utilInfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UtilInfoQueryDSLRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

}
