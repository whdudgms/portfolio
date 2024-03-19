package com.lhs.rest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

	@GetMapping("/boards/files/{fileIdx}")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(@PathVariable int fileIdx, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 첨부파일 정보 조회.
		HashMap<String, Object> fileInfo = attFileService.readAttFileByPk(fileIdx);
		if (fileInfo == null) {
			// 파일 정보가 없는 경우, 적절한 HTTP 상태 코드 반환
			return null;
		}

		byte[] fileContent = fileUtil.readFile(fileInfo);
	    String fileName = URLEncoder.encode((String) fileInfo.get("file_name"), "UTF-8").replaceAll("\\+", "%20");
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType((String) fileInfo.get("file_type")))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	            .body(fileContent);
	}
	
	
	
	@PostMapping("/boards")
	public ResponseEntity<?> write(
	        String memberId,
	        String memberNick,
	        String title,
	        String content, 
	         MultipartHttpServletRequest mReq, 
	        HttpSession session) {
		
		BoardDto boardDto = new BoardDto();
		boardDto.setMemberId(memberId);
		boardDto.setMemberNick(memberNick);
		boardDto.setTitle(title);
		boardDto.setContent(content);

		if (Objects.isNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}

		   // 나머지 비즈니스 로직 처리
	    int cnt = bService.write(boardDto, mReq.getFiles("attFiles"));
	    if(cnt == 1) {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 삽입 완료!!!", "nextPage", "/board/list.do"));
	    } else {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 삽입 실패!!!", "nextPage", "/board/list.do"));
	    }
	}

//	int cnt = bService.delete(params);
//	HashMap<String, Object> map = new HashMap<String, Object>();
//
//	map.put("cnt", cnt);
//	map.put("msg", cnt==1?"게시물 업데이트 완료!!!":"게시물 업데이트 실패!!!");
//	map.put("nextPage", cnt==1?"/board/ .do" : "/board/list.do");

	@PostMapping("/board/{boardSeq}")
	@ResponseBody // !!!!!!!!!!!! 비동기 응답
	public ResponseEntity<?>  update(
			//BoardDto boardDto,
			@PathVariable Integer	boardSeq,
	        String memberId,
	        String memberNick,
	        String title,
	        String content, 
			String hasFile,
			
	         MultipartHttpServletRequest mReq) {

		BoardDto boardDto = new BoardDto();
		boardDto.setBoardSeq(boardSeq);
		boardDto.setMemberId(memberId);
		boardDto.setMemberNick(memberNick);
		boardDto.setTitle(title);
		boardDto.setContent(content);
		boardDto.setHasFile(hasFile);
		
		System.out.println("update method  파라미터 체크.:" +boardDto);
		
		if (Objects.isNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}
		System.out.println("update method :" +boardDto);
		
		int cnt = bService.update(boardDto, mReq.getFiles("attFiles"));
		
		System.out.println("int cnt = bService.update(boardDto, mReq.getFiles(\"attFiles\")):" +cnt);
		
		if(cnt >= 1) {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 업데이트 완료!!!", "nextPage", "/board/list.do"));
	    } else {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 업데이트 실패!!!", "nextPage", "/board/list.do"));
	    }
	}
	
	
	

	@DeleteMapping("/board/{boardSeq}")
	@ResponseBody
	public ResponseEntity<?> delete(
			@PathVariable Integer boardSeq,
			String hasFile,
			Integer currentPage, HttpSession session) {
		
		
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("boardSeq", boardSeq);
		params.put("hasFile", hasFile);
		params.put("currentPage", currentPage);

		if (!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		int cnt = bService.delete(params);
		if(cnt >= 1) {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 삭제 완료!!!", "nextPage", "/board/list.do"));
	    } else {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 삭제 실패!!!", "nextPage", "/board/list.do"));
	    }
	}

	@DeleteMapping("/attFile/{boardSeq}/{fileIdx}")
	@ResponseBody
	public ResponseEntity<?>  deleteAttFile(@PathVariable Integer boardSeq, @PathVariable Integer fileIdx ) {
		
		
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("boardSeq", boardSeq);
		params.put("fileIdx", fileIdx);

		if (!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		boolean cnt = bService.deleteAttFile(params);

		if(cnt) {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 첨부파일 삭제 완료!!!", "nextPage", "/board/list.do"));
	    } else {
	        return ResponseEntity.ok().body(Map.of("message", "게시물 첨부파일 삭제 실패!!!", "nextPage", "/board/list.do"));
	    }
	}
}
