package edu;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.codegen.maven.example.tables.MoviesReview;
import org.jooq.codegen.maven.example.tables.records.MoviesRecord;
import org.jooq.codegen.maven.example.tables.records.MoviesReviewRecord;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Consumer;

import static org.jooq.codegen.maven.example.tables.Movies.MOVIES;
import static org.jooq.codegen.maven.example.tables.MoviesReview.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryTest {

    @Autowired
    private DSLContext dslContext;

    @Test
    public void testSelect() {
        System.out.println(dslContext.selectFrom(MOVIES).fetch());

        dslContext.insertInto(MOVIES).set(new MoviesRecord(8, "Terminator", LocalDate.of(2000, 1,1),
                "")).execute();

        System.out.println(dslContext.selectFrom(MOVIES).fetch());

        dslContext.update(MOVIES).set(MOVIES.DIRECTOR, "La La").where(MOVIES.NAME.eq("Terminator")).execute();

        System.out.println(dslContext.selectFrom(MOVIES).fetch());

        dslContext.delete(MOVIES)
                .where(MOVIES.DIRECTOR.eq("La La"))
                .execute();

        System.out.println(dslContext.selectFrom(MOVIES).fetch());
        Result<Record2<String, BigDecimal>> fetch = dslContext.select(MOVIES.NAME, DSL.avg(MOVIES_REVIEW.SCORE))
                .from(MOVIES.join(MOVIES_REVIEW).on(MOVIES.ID.eq(MOVIES_REVIEW.MOVIE)))
                .groupBy(MOVIES.NAME).fetch();

        fetch.forEach(System.out::println);
    }

}
