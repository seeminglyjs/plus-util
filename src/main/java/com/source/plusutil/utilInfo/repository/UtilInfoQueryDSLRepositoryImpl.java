package com.source.plusutil.utilInfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.source.plusutil.utilInfo.dto.entity.QUtilInfoDto;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UtilInfoQueryDSLRepositoryImpl implements UtilInfoQueryDSLRepository {
    private final JPAQueryFactory jpaQueryFactory;
    public List<UtilInfoDto> getTopFiveUtilInfo() {
        QUtilInfoDto qUtilInfo = QUtilInfoDto.utilInfoDto;
        return jpaQueryFactory
                .selectFrom(qUtilInfo)
                .orderBy(qUtilInfo.utilLikes.desc(), qUtilInfo.utilViews.desc(), qUtilInfo.utilName.asc())
                .limit(5)
                .fetch();
    }

}
