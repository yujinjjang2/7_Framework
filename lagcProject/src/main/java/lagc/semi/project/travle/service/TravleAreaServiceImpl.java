package lagc.semi.project.travle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lagc.semi.project.travle.dao.TravleAreaDAO;
import lagc.semi.project.travle.dto.TravleArea;

@Service
public class TravleAreaServiceImpl implements TravleAreaService{

	@Autowired
	private  TravleAreaDAO dao;

	@Override
	public List<TravleArea> selectTravleArea(String area) {
		return dao.selectTravleArea(area);
	}

}
