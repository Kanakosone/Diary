package com.example.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.diary.model.DiaryEntry;
import com.example.diary.repository.DiaryEntryRepository;

@Service
public class DiaryService {

	@Autowired
	private DiaryEntryRepository diaryEntryRepository;

	public DiaryEntry createEntry(DiaryEntry entry) {
		// カレンダー連携の処理も将来ここに追加できる！
		if (entry.getDate() == null) //日付が入ってるかチェック
		{
			throw new IllegalArgumentException("日付は必須です。");
		}
		if (!StringUtils.hasText(entry.getActivity())) //アクティビティが空文字やスペースだけの場合もNGにできる
		{
			throw new IllegalArgumentException("活動内容は必須です。");
		}
		return diaryEntryRepository.save(entry);
	}

	public List<DiaryEntry> getAllEntries() {
		return diaryEntryRepository.findAll();
	}

	public boolean isDuplicate(DiaryEntry newEntry) {
		if (newEntry.getDate() == null || newEntry.getActivity() == null) {
			return false; // 新しいエントリー自体が不完全なら重複しようがない
		}
		return diaryEntryRepository.existsByDateAndActivity(newEntry.getDate(), newEntry.getActivity());
	}

	//    public boolean isDuplicate(DiaryEntry newEntry) {
	//        List<DiaryEntry> allEntries = diaryEntryRepository.findAll();  // すべての日記を取得
	//        return allEntries.stream().anyMatch(entry ->
	//        entry.getDate() != null &&
	//        entry.getActivity() != null &&
	//        entry.getDate().equals(newEntry.getDate()) &&
	//        entry.getActivity().equals(newEntry.getActivity())
	//);
	//}

}
