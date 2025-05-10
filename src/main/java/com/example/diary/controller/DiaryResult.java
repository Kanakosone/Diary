package com.example.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.diary.model.DiaryEntry;
import com.example.diary.service.DiaryService;

@Controller
@RequestMapping("/diary")
public class DiaryResult {

	@Autowired
	private DiaryService diaryService;
	
//	
	@PostMapping("/form")
	public String saveDiary(@ModelAttribute DiaryEntry diary) {
	    diaryService.createEntry(diary);
	    return "redirect:/diary/top"; // ←ここをカレンダー画面に直接リダイレクト！
	}

//
//	@PostMapping("/result")
//	public String saveDiary(@ModelAttribute DiaryEntry diaryEntry, RedirectAttributes redirectAttributes) {
//		// ここでDBなどに保存
//		diaryService.createEntry(diaryEntry);
//		// 動的メッセージを作成
//		String message = diaryEntry.getDate() + " に " +
//				diaryEntry.getWhoWith() + " と " +
//				diaryEntry.getLocation() + " で " +
//				diaryEntry.getActivity() + " をした内容を保存しました。";
//
//		redirectAttributes.addFlashAttribute("message", message);
//		return "redirect:/diary/top";
//	}

	@PostMapping
	public DiaryEntry createEntry(@RequestBody DiaryEntry entry) {
		return diaryService.createEntry(entry);
	}

	@GetMapping("/result")
	public String showDiaryResult(Model model) {
		// ここでは flash attribute（message）を受け取って画面に表示する
		return "diary/result"; // ←このHTML（テンプレート）を作成して表示
	}

	
	
//	@GetMapping("/top")
//	public String showCalendar(Model model) {
//	    List<DiaryEntry> entries = diaryService.getAllEntries();
//
//	    List<Map<String, Object>> events = entries.stream().map(entry -> {
//	        Map<String, Object> event = new HashMap<>();
//	        event.put("title", entry.getActivity());
//	        
//
//	        // null チェックを追加
//	        if (entry.getDate() != null) {
//	            event.put("start", entry.getDate().toString()); // ISO形式
//	        } else {
//	            // 日付が null の場合の対処（例えば何も入れないか、"未定"等の文字列にする）
//	            event.put("start", "未定");
//	        }
//
//	        return event;
//	    }).collect(Collectors.toList());
//
//	    ObjectMapper mapper = new ObjectMapper();
//	    try {
//	        String jsonEvents = mapper.writeValueAsString(events);
//	        model.addAttribute("diaryEvents", jsonEvents);
//	    } catch (JsonProcessingException e) {
//	        e.printStackTrace();
//	        model.addAttribute("diaryEvents", events);
//	    }
//
//	    return "diary/top";
//	}

//	@GetMapping("/top")
//	public String showCalendar(Model model) {
//	    List<DiaryEntry> entries = diaryService.getAllEntries();
//
//	    List<Map<String, Object>> events = entries.stream().map(entry -> {
//	        Map<String, Object> event = new HashMap<>();
//	        event.put("title", entry.getActivity()); // カレンダー上に表示されるタイトル
//	        event.put("start", entry.getDate().toString()); // ISO形式
//	        return event;
//	    }).collect(Collectors.toList());
//
//	    model.addAttribute("diaryEvents", events);
//	    return "top"; // top.htmlに対応
//	}

}
