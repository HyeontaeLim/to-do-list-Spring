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
        String sql = "insert into memo (memo, created, dTime) values (?, ?, ?)";
        template.update(sql, memo.getMemo(), memo.getCreated(), memo.getDTime());
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

    @Override
    public void deleteById(Long id) {
        String sql = "delete from memo where id = ?";
        template.update(sql, id);
    }

    @Override
    public Memo updateById(Long id, Memo memo) {
        String sql = "update memo set memo = ?, created = ?, dTime = ? where id = ?";
        template.update(sql, memo.getMemo(),LocalDateTime.now(), memo.getDTime(), id);
        return memo;
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
