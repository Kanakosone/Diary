//package com.example.diary.service;
//import java.io.InputStream;
//import java.util.Calendar;
//
////import java.awt.Event;
////import java.io.InputStream;
////import java.util.Calendar;
////import java.util.Collections;
////
//import org.springframework.stereotype.Service;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.calendar.model.EventDateTime;
//
//
//
//@Service
//public class GoogleCalendarService {
//
//	public Calendar getCalendarService() throws Exception {
//	    // credentials.jsonの読み込み
//	    InputStream in = getClass().getClassLoader().getResourceAsStream("credentials.json");
//	    if (in == null) {
//	        throw new RuntimeException("credentials.json が見つかりません");
//	    }
//
//	    HttpTransport httpTransport = new NetHttpTransport();
//	    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//
//	    // Google認証の設定
//	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//	            httpTransport, jsonFactory, "YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET", Collections.singleton(CalendarScopes.CALENDAR))
//	            .setAccessType("offline")
//	            .build();
//
//	    Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//
//	    // Calendarサービスの作成
//	    return new Calendar.Builder(httpTransport, jsonFactory, credential)
//	            .setApplicationName(APPLICATION_NAME)
//	            .build();
//	}
//
//
//	public void insertSampleEvent() throws Exception {
//		Calendar service = getCalendarService();
//
//		Event event = new Event()
//				.setSummary("サンプルイベント")
//				.setDescription("これはGoogle Calendar APIとの連携テストです");
//
//		EventDateTime start = new EventDateTime()
//				.setDateTime(new com.google.api.client.util.DateTime("2025-04-20T10:00:00+09:00"))
//				.setTimeZone("Asia/Tokyo");
//		event.setStart(start);
//
//		EventDateTime end = new EventDateTime()
//				.setDateTime(new com.google.api.client.util.DateTime("2025-04-20T11:00:00+09:00"))
//				.setTimeZone("Asia/Tokyo");
//		event.setEnd(end);
//
//		service.events().insert("primary", event).execute();
//	}
//}
