package com.source.plusutil.menu.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.source.plusutil.menu.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryDSLMenuRepositoryImpl implements QueryDSLMenuRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NavDto> findNavList() {
        QNavDto qNavDto = QNavDto.navDto;
        return jpaQueryFactory.selectFrom(qNavDto).fetch();
    }
    @Override
    public List<HeadDto> findHeadList() {
        QHeadDto qHeadDto = QHeadDto.headDto;
        return jpaQueryFactory.selectFrom(qHeadDto).fetch();
    }
    @Override
    public List<MenuDto> findMenuList() {
        QMenuDto qMenuDto = QMenuDto.menuDto;
        return jpaQueryFactory.selectFrom(qMenuDto).fetch();
    }

    @Override
    public List<HeadDto> findAllHeadMenus() {
        QHeadDto qHeadDto = QHeadDto.headDto;
        return jpaQueryFactory.selectFrom(qHeadDto).fetch();
    }

    @Override
    public List<NavInfoDto> findAllMenuList() {
        QNavDto qNavDto = QNavDto.navDto;
        QHeadDto qHeadDto = QHeadDto.headDto;
        QMenuDto  qMenuDto = QMenuDto.menuDto;

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                NavInfoDto.class,
                                qNavDto.navNo,
                                qNavDto.navName,
                                qHeadDto.headNo,
                                qHeadDto.headName,
                                qMenuDto.menuNo,
                                qMenuDto.menuName,
                                qMenuDto.url
                        )
                )
                .from(qNavDto)
                .leftJoin(qHeadDto)
                .on(qNavDto.navNo.eq(qHeadDto.navNo))
                .leftJoin(qMenuDto)
                .on(qHeadDto.headNo.eq(qMenuDto.headNo))
                .fetch();
    }

}
