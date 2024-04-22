package com.reading.readingBook.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.reading.readingBook.dto.MonthChartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.reading.bookshelf.domain.QBookShelf.bookShelf;
import static com.reading.report.domain.QBookReport.bookReport;

@Repository
@RequiredArgsConstructor
public class ReadingBookRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public Map<String, Object> findAllByMonthChartData(Long id) {
        LocalDateTime now = LocalDateTime.now();

        List<MonthChartResponseDto> monthDataList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            LocalDate dateTime = now.toLocalDate().minusMonths(i);
            LocalDate from = dateTime.withDayOfMonth(1);
            LocalDate to = from.withDayOfMonth(from.lengthOfMonth());

            LocalDateTime fromDateTime = LocalDateTime.of(from, LocalTime.MIN);
            LocalDateTime toDateTime = LocalDateTime.of(to, LocalTime.MAX);

            MonthChartResponseDto dto = Optional.ofNullable(jpaQueryFactory
                            .select(Projections.constructor(MonthChartResponseDto.class, bookReport.count()))
                            .from(bookReport)
                            .innerJoin(bookShelf).on(bookReport.id.eq(bookShelf.bookReport.id))
                            .where(bookReport.regDate.between(fromDateTime, toDateTime), bookShelf.member.id.eq(id))
                            .groupBy(bookReport.regDate.yearMonth())
                            .fetchOne())
                    .orElseGet(MonthChartResponseDto::new);

            dto.setMonth(from.format(DateTimeFormatter.ofPattern("yyyy-MM")));
            monthDataList.add(dto);
        }

        // monthDataList 역순으로 정렬
        Collections.reverse(monthDataList);

        Map<String, Object> data = new HashMap<>();
        data.put("monthData", monthDataList);

        return data;
    }
}
