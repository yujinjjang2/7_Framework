package lagc.semi.project.travle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lagc.semi.project.travle.dto.TravleArea;
import lagc.semi.project.travle.service.TravleAreaService;

@Controller // 요청/응답 제어 + bean 등록
public class TravleAreaController {
	
	@Autowired
	private TravleAreaService service;
	
	@RequestMapping("/travleArea/detail")
	// @ResponseBody // 비동기 요청한곳으로 응답 + Java데이터 JSON, TEXT로 변환
	public String selectTravleArea(String area, Model model) {
		String path = "travleArea/";
		
		System.out.println("area:" + area); // 1=서울, 2=강원
		
		List<TravleArea> list = service.selectTravleArea(area);

		model.addAttribute("list",list);
		
		for(TravleArea ta : list)
		{
			System.out.println(ta.toString());
		}
		
		if(area.equals("SE")) {
			path += "seoul"; // travleArea/seoul(상대경로)
		}
		else if(area.equals("GW")) {
			path += "gangwon"; // travleArea/gangwon(상대경로)
		}

		System.out.println(path);
		
		return path;
	}
	
}
