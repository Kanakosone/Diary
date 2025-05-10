// src/main/java/com/example/diary/model/DiaryEntry.java

package com.example.diary.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "diary_entry")
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // dateフィールドはnullで初期化

    private String whoWith;
    private String location;
    private String activity;

    private LocalDateTime createdAt;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getWhoWith() { return whoWith; }
    public void setWhoWith(String whoWith) { this.whoWith = whoWith; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    // @PrePersistで初期化
    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDate.now(); // dateがnullの場合は現在の日付を設定
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // createdAtがnullの場合は現在の時刻を設定
        }
   
	}
}


//package com.example.diary.model;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//@Entity
//@Table(name = "diary_entry")
//public class DiaryEntry {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date;
//
//    private String whoWith;
//    private String location;
//    private String activity;
//
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    // Getter & Setter
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public LocalDate getDate() { return date; }
//    public void setDate(LocalDate date) { this.date = date; }
//
//    public String getWhoWith() { return whoWith; }
//    public void setWhoWith(String whoWith) { this.whoWith = whoWith; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public String getActivity() { return activity; }
//    public void setActivity(String activity) { this.activity = activity; }
//
//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
//}
