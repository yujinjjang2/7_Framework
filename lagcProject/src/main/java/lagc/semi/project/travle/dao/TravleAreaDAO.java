package lagc.semi.project.travle.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lagc.semi.project.travle.dto.TravleArea;

@Repository // DB 연결 의미 + bean 으로 등록
public class TravleAreaDAO {

	@Autowired // bean 중에서 타입이 같은 객체를 DI(의존성 주입)
	private SqlSessionTemplate sqlSession;

	public List<TravleArea> selectTravleArea(String area) {
		return sqlSession.selectList("travleAreaMapper.selectTravleArea", area);
	}

}
