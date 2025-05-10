// src/main/java/com/example/diary/controller/DiaryController.java

package com.example.diary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.diary.model.DiaryEntry;
import com.example.diary.service.DiaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/diary")
public class DiaryController {

	@Autowired
	private DiaryService diaryService;

	@GetMapping("/top")
	public String showCalendar(Model model) {
		List<DiaryEntry> entries = diaryService.getAllEntries();

		List<Map<String, Object>> events = entries.stream()
				.filter(entry -> entry.getDate() != null) // 日付がnullじゃないものだけ
				.map(entry -> {
					Map<String, Object> event = new HashMap<>();
					event.put("title", entry.getActivity());
					event.put("start", entry.getDate().toString()); // 安心して日付セットできる
					
					 // ここを追加！
			        event.put("withWhom", entry.getWhoWith());
			        event.put("location", entry.getLocation());
			        event.put("activity", entry.getActivity());

					return event;
				})
				.collect(Collectors.toList());

		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonEvents = mapper.writeValueAsString(events);
			model.addAttribute("diaryEvents", jsonEvents);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			model.addAttribute("diaryEvents", events);
		}

		return "diary/top";
	}

	@GetMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("diaryEntry", new DiaryEntry());
		return "diary/form";
	}

	@PostMapping("/top")
	public String submitDiary(@ModelAttribute DiaryEntry diaryEntry, Model model) {
		if (!diaryService.isDuplicate(diaryEntry)) {
			diaryService.createEntry(diaryEntry);
			System.out.println("日記を保存しました: " + diaryEntry);
			return "redirect:/diary/top"; // 保存したあとリダイレクトとか
		} else {
			model.addAttribute("errorMessage", "同じ日付と内容の日記が既に存在しています。保存しませんでした。");
	        model.addAttribute("diaryEntry", diaryEntry); // 入力内容も戻す
//			bindingResult.rejectValue("id", "id", "同じ日付と内容の日記が既に存在しています。保存しませんでした。");
			return "diary/form";
		
	}

	}

}

//    @Autowired
//    private GoogleCalendarService googleCalendarService;
//
//    @GetMapping("/test-calendar")
//    public String testCalendarIntegration() {
//        try {
//            googleCalendarService.insertSampleEvent();
//            return "redirect:/diary/form";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error"; // エラー表示用ページ（なければ form に戻してもOK）
//        }
