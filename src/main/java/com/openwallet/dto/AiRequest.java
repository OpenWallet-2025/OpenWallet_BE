package com.openwallet.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data; // Lombok을 사용한다고 가정 (Getter/Setter 자동 생성)

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AiRequest {

    // 1. OCR 영수증 분석 요청 (파일 업로드)
    // ==========================================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ocr {
        // 프론트엔드에서 'file'이라는 키값으로 이미지를 보냄
        private MultipartFile file;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotalSpend {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("start_date")
        private String startDate; // YYYY-MM-DD

        @JsonProperty("end_date")
        private String endDate;   // YYYY-MM-DD

        // 선택 항목 (필수가 아님, Null일 수 있음)
        @JsonProperty("category_ids")
        private List<Integer> categoryIds; // int[] -> List<Integer>

        @JsonProperty("merchant_ids")
        private List<Integer> merchantIds; // int[] -> List<Integer>
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopMerchants {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("start_date")
        private String startDate;

        @JsonProperty("end_date")
        private String endDate;

        // 선택 항목 (기본값 5)
        // int 대신 Integer를 쓰면 값이 없을 때 null로 보내져서
        // FastAPI의 기본값(default=5)이 적용되도록 할 수 있음
        @JsonProperty("limit")
        private Integer limit;

        // 카테고리 필터 (선택)
        @JsonProperty("category_ids")
        private List<Integer> categoryIds;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Trend {

        @JsonProperty("user_id")
        private String userId;

        // "monthly" 또는 "weekly" (기본값 monthly)
        // 값이 없으면 null로 보내서 서버 기본값 사용
        @JsonProperty("period")
        private String period;

        // 조회 기간 (기본 6)
        @JsonProperty("months")
        private Integer months;

        // 카테고리 필터
        @JsonProperty("category_ids")
        private List<Integer> categoryIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {

        // 검색 키워드 리스트 (필수)
        private List<String> keywords;

        // 최근 N일 (기본 7)
        private Integer days;

        // 최대 기사 수 (기본 30)
        @JsonProperty("max_articles")
        private Integer maxArticles;

        // 사용 LLM 모델 이름 (선택)
        private String model;

        // 캐시용 DB 경로 (선택)
        @JsonProperty("db_path")
        private String dbPath;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Report {

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("start_date")
        private String startDate; // YYYY-MM-DD

        @JsonProperty("end_date")
        private String endDate;   // YYYY-MM-DD

        // 사용자가 LLM에게 던지는 질문 (예: "내 소비 습관의 문제점을 분석해줘")
        private String question;
    }

}