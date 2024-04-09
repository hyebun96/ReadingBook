package com.reading.readingBook.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.reading.readingBook.dto.MonthChartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.reading.bookshelf.domain.QBookShelf.bookShelf;
import static com.reading.report.domain.QBookReport.bookReport;

@Repository
@RequiredArgsConstructor
public class ReadingBookRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public Map<String, Object> findAllByMonthChartData(Long id) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        List<MonthChartResponseDto> monthDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            LocalDateTime dateTime = now.minusMonths(i);
            LocalDateTime from = dateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
            LocalDateTime to = from.withDayOfMonth(from.getMonth().maxLength()).withHour(23).withMinute(59).withSecond(59);

            MonthChartResponseDto dto = Optional.ofNullable(jpaQueryFactory.select(
                                    Projections.constructor(MonthChartResponseDto.class,
                                            bookReport.count()))
                            .from(bookReport)
                            .innerJoin(bookShelf)
                            .on(bookReport.id.eq(bookShelf.bookReport.id))
                            .where(bookReport.regDate.between(from, to), bookShelf.member.id.eq(id)) // memberId랑 book_report_id 조건에 넣어야함 bookShelf.member.id.eq(id)
                            .groupBy(bookReport.regDate.yearMonth())
                            .fetchOne())
                    .orElseGet(MonthChartResponseDto::new);

            dto.setMonth(from.format(DateTimeFormatter.ofPattern("yyyy-MM")));
            monthDataList.add(dto);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("monthData", monthDataList);

        return data;
    }
}
