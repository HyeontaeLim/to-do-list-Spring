package com.example.todolist.repository;

import com.example.todolist.domain.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcMemoRepository implements MemoRepository{

    private final JdbcTemplate template;

    public JdbcMemoRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Memo memoSave(Memo memo) {
        String sql = "insert into memo (memo, created, dTime) values (?, now(), now())";
        template.update(sql, memo.getMemo());
        return memo;
    }

    @Override
    public List<Memo> findMemoAll() {
        String sql = "select * from memo";
        return template.query(sql, memosRowMapper());
    }

    private RowMapper<Memo> memosRowMapper() {
        return (rs, rowNum) -> {
            Memo memo = new Memo();
            memo.setId(rs.getLong("id"));
            memo.setMemo(rs.getString("memo"));
            memo.setCreated(rs.getTimestamp("created").toLocalDateTime());
            memo.setDTime(rs.getTimestamp("dTime").toLocalDateTime());
            return memo;
        };
    }

    @Override
    public Optional<Memo> findById(Long id) {
        String sql = "select * from memo where id = ?";
        return template.queryForObject(sql, memoRowMapper(), id);
    }

    private RowMapper<Optional<Memo>> memoRowMapper() {
        return (rs, rowNum) -> {
            Memo memo = new Memo();
            memo.setId(rs.getLong("id"));
            memo.setMemo(rs.getString("memo"));
            memo.setCreated(rs.getTimestamp("created").toLocalDateTime());
            memo.setDTime(rs.getTimestamp("dTime").toLocalDateTime());
            return Optional.of(memo);
        };
    }
}