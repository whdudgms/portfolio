package com.lhs.rest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lhs.dto.BoardDto;
import com.lhs.service.AttFileService;
import com.lhs.service.BoardService;
import com.lhs.util.FileUtil;

@RestController
public class BoardRestController {

	@Autowired
	BoardService bService;
	@Autowired
	AttFileService attFileService;
	@Autowired
	FileUtil fileUtil;

	private String typeSeq = "2"; // 이코드가 꼭 필요할까??

	// 여기 지금 현재 BoardController에

	@RequestMapping("/board/download.do")
	@ResponseBody
	public byte[] downloadFile(@RequestParam int fileIdx, HttpServletResponse response)
			throws UnsupportedEncodingException {

		// fileIdx를 가지고 첨부파일 정보 가져오기
		System.out.println("파일 번호 값을 출력합니다 " + "fileIdx  =  ");
		System.out.println(fileIdx);

		// 첨부파일 정보 조회
		HashMap<String, Object> fileInfo = attFileService.readAttFileByPk(fileIdx);
		if (fileInfo == null) {
			// 파일 정보가 없는 경우, 적절한 HTTP 상태 코드 반환
			return null;
		}

		response.setContentType((String) fileInfo.get("file_type"));
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ URLEncoder.encode((String) fileInfo.get("file_name"), "UTF-8").replaceAll("\\+", "%20") + "\"");

		return fileUtil.readFile(fileInfo);
	}

	@RequestMapping("/board/write.do")
	@ResponseBody
	public HashMap<String, Object> write(BoardDto boardDto, MultipartHttpServletRequest mReq, HttpSession session) {

//		boardDto.put("memberId","whdudgms1234");
		System.out.println(mReq);
		if (Objects.nonNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}

		int cnt = bService.write(boardDto, mReq.getFiles("attFiles"));

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		map.put("msg", cnt == 1 ? "게시물 삽입 완료!!!" : "게시물 삽입 실패!!!");
		map.put("nextPage", cnt == 1 ? "/board/list.do" : "/board/list.do");
		return map;
	}

//	int cnt = bService.delete(params);
//	HashMap<String, Object> map = new HashMap<String, Object>();
//
//	map.put("cnt", cnt);
//	map.put("msg", cnt==1?"게시물 업데이트 완료!!!":"게시물 업데이트 실패!!!");
//	map.put("nextPage", cnt==1?"/board/ .do" : "/board/list.do");

	@RequestMapping("/board/update.do")
	@ResponseBody // !!!!!!!!!!!! 비동기 응답
	public HashMap<String, Object> update(BoardDto boardDto, MultipartHttpServletRequest mReq) {

		if (Objects.isNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}
		System.out.println("/board/update.do에서  전달받은 파라미터 출력  ");
		System.out.println("boardDto  :" + boardDto);
		int cnt = bService.update(boardDto, mReq.getFiles("attFiles"));
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("cnt", cnt);
		map.put("msg", cnt == 1 ? "게시물 업데이트 완료!!!" : "게시물 업데이트 실패!!!");
		map.put("nextPage", cnt == 1 ? "/board/list.do" : "/board/list.do");
		return map;
	}

	@GetMapping("/board/delete.do")
	@ResponseBody
	public HashMap<String, Object> delete(@RequestParam HashMap<String, Object> params, HttpSession session) {
		System.out.println("controller delete메서드.");
		System.out.println(params);

		if (!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		int cnt = bService.delete(params);
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("cnt", cnt);
		map.put("msg", cnt == 1 ? "게시물 삭제 완료!!!" : "게시물 삭제 실패!!!");
		map.put("nextPage", cnt == 1 ? "/board/list.do" : "/board/list.do");

		return map; // 비동기: map return
	}

	@PostMapping("/board/deleteAttFile.do")
	@ResponseBody
	public HashMap<String, Object> deleteAttFile(@RequestParam HashMap<String, Object> params) {

		if (!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		boolean cnt = bService.deleteAttFile(params);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("cnt", cnt);
		map.put("msg", cnt ? "첨부파일 완료!!!" : "첨부파일 실패!!!");
		map.put("nextPage", cnt ? "/board/list.do" : "/board/list.do");

		return map;
	}

}
