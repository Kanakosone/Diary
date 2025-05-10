package com.example.diary.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.diary.model.DiaryEntry;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    // 特別なメソッドが必要ならここに追加できます（今はそのままでOK）
	boolean existsByDateAndActivity(LocalDate date, String activity);

}
