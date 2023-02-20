package com.source.plusutil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.plusutil.dto.TestInfo;

public interface TestInfoRepository extends JpaRepository<TestInfo, String> {

	//[참고 키워드]
	//	And => findByLastnameAndFirstname (EX. where x.lastname = ?1 and x.firstname = ?2)
	//	Or => findByLastnameOrFirstname (EX. where x.lastname = ?1 or x.firstname = ?2)
	//	Is, Equals => findByName,findByNameIs,findByNameEquals (EX. where x.name = 1?)
	//	Between => findBySalBetween(EX. where x.sal between 1? and ?2)

	// findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
	public Optional<TestInfo> findById(String id);

	//like검색도 가능
	//    public List<MemberVo> findByNameLike(String keyword);
}
